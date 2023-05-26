package business;

import dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.User;


public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, "{0} does not support GET", LoginServlet.class.getName());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");
        User user = UserDAO.getUser(userID, password);
        if (user == null) {
            LOGGER.log(Level.INFO, "Invalid login");
            request.setAttribute("blInvalidLogin", true);
        } else {
            request.getSession().setAttribute("user", user);
        }
    }
}
