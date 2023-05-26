package obj;

import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userID;
    private String password;
    private String fullName;
    private String role;

    public User() {
        this.userID = "";
        this.password = "";
        this.fullName = "";
        this.role = "";
    }

    public User(String userID, String fullName, String role) {
        this.userID = userID;
        this.password = "";
        this.fullName = fullName;
        this.role = role;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
