package business;

import dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, "{0} does not support GET", LoginServlet.class.getName());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String createType = request.getParameter("ct");
        if (createType == null) {
            LOGGER.log(Level.SEVERE, "Null create type");
            return;
        }

        String userID = request.getParameter("txtUserID");
        if (UserDAO.checkUserExists(userID)) {
            LOGGER.log(Level.SEVERE, "User already exists");
            request.setAttribute("userExisted", true);
            return;
        }

        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFullName");
        String role;
        switch (createType) {
            case "ad":
                role = request.getParameter("txtRole");
                break;

            case "us":
                role = "US";
                break;

            default:
                LOGGER.log(Level.SEVERE, "Invalid create type");
                return;
        }

        if (!UserDAO.insertUser(userID, password, fullName, role)) {
            LOGGER.log(Level.SEVERE, "Can't insert user");
        }
    }
}
