package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.User;
import utils.DBUtils;


public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    private static final String GET_USER = "SELECT * FROM tbl_User "
            + "WHERE userID = ? AND password = ? COLLATE Latin1_General_CS_AS";

    private static final String GET_USERS = "SELECT * FROM tbl_User";

    private static final String SEARCH_USERS = "SELECT * FROM tbl_User "
            + "WHERE fullName LIKE ? COLLATE Latin1_General_CI_AS";

    private static final String DELETE_USER = "DELETE FROM tbl_User "
            + "WHERE userID = ? COLLATE Latin1_General_CS_AS";

    private static final String UPDATE_USER = "UPDATE tbl_User "
            + "SET role = ? WHERE userID = ? COLLATE Latin1_General_CS_AS";

    private static final String INSERT_USER = "INSERT INTO tbl_User "
            + "VALUES (?, ?, ?, ?)";

    public static final User getUser(String userID, String password) {
        if (userID == null || password == null) {
            LOGGER.log(Level.SEVERE, "Null parameters");
            return null;
        }

        try (
                Connection con = DBUtils.getConnection();
        ) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement getUserPst = con.prepareStatement(GET_USER,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            ) {
                getUserPst.setString(1, userID);
                getUserPst.setString(2, password);
                ResultSet result = getUserPst.executeQuery();
                if (result.next() && !result.isLast()) {
                    LOGGER.log(Level.SEVERE, "Multiple users");
                    return null;
                }

                return new User(result.getString("userID"),
                        result.getString("fullName"),
                        result.getString("role")
                );
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public static final List<User> getUsers() {
        try (
                Connection con = DBUtils.getConnection();
        ) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement getUsersPst = con.prepareStatement(GET_USERS);
            ) {
                ResultSet result = getUsersPst.executeQuery();
                List<User> users = new ArrayList<>();
                while (result.next()) {
                    users.add(new User(result.getString("userID"),
                            result.getString("fullName"),
                            result.getString("role")
                    ));
                }
                return users;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return new ArrayList<>();
    }

    public static final List<User> searchUsers(String fullName) {
        if (fullName == null) {
            LOGGER.log(Level.SEVERE, "Null parameters");
            return null;
        }

        try (
                Connection con = DBUtils.getConnection();
        ) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement searchUsersPst = con.prepareStatement(SEARCH_USERS);
            ) {
                String fullNameSearch = "%" + fullName + "%";
                searchUsersPst.setString(1, fullNameSearch);
                ResultSet result = searchUsersPst.executeQuery();
                List<User> users = new ArrayList<>();
                while (result.next()) {
                    users.add(new User(result.getString("userID"),
                            result.getString("fullName"),
                            result.getString("role")
                    ));
                }
                return users;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return new ArrayList<>();
    }

    public static final boolean deleteUser(String userID) {
        if (userID == null) {
            LOGGER.log(Level.SEVERE, "Null parameters");
            return false;
        }

        try (
                Connection con = DBUtils.getConnection();
        ) {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement deleteUserPst = con.prepareStatement(DELETE_USER);
            ) {
                deleteUserPst.setString(1, userID);
                if (deleteUserPst.executeUpdate() != 1) {
                    throw new SQLException("Can't delete user");
                }
                con.commit();
                return true;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                con.rollback();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }

    public static final boolean updateUser(String userID, String role) {
        if (userID == null || role == null) {
            LOGGER.log(Level.SEVERE, "Null parameters");
            return false;
        }

        if (!role.equals("AD") && !role.equals("US")) {
            LOGGER.log(Level.SEVERE, "Invalid role");
            return false;
        }

        try (
                Connection con = DBUtils.getConnection();
        ) {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement updateUserPst = con.prepareStatement(UPDATE_USER);
            ) {
                updateUserPst.setString(1, role);
                updateUserPst.setString(2, userID);
                if (updateUserPst.executeUpdate() != 1) {
                    throw new SQLException("Can't update user");
                }
                con.commit();
                return true;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                con.rollback();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }

    public static final boolean insertUser(String userID, String password, String fullName, String role) {
        if (userID == null || password == null || fullName == null || role == null) {
            LOGGER.log(Level.SEVERE, "Null parameters");
            return false;
        }

        if (!role.equals("AD") && !role.equals("US")) {
            LOGGER.log(Level.SEVERE, "Invalid role");
            return false;
        }

        try (
                Connection con = DBUtils.getConnection();
        ) {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement insertUserPst = con.prepareStatement(INSERT_USER);
            ) {
                insertUserPst.setString(1, userID);
                insertUserPst.setString(2, password);
                insertUserPst.setString(3, fullName);
                insertUserPst.setString(4, role);
                if (insertUserPst.executeUpdate() != 1) {
                    throw new SQLException("Can't insert user");
                }
                con.commit();
                return true;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                con.rollback();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }
}
