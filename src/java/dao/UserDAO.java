package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.User;
import utils.DBUtils;


public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    private static final String GET_USER = "SELECT * FROM tbl_User "
            + "WHERE userID = ? AND password = ? COLLATE Latin1_General_CS_AS";

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
}
