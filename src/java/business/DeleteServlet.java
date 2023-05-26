package business;

import dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("uid");
        if (userID == null) {
            LOGGER.log(Level.SEVERE, "Null user id");
            return;
        }
        if (!UserDAO.deleteUser(userID)) {
            LOGGER.log(Level.SEVERE, "Can't delete user");
            request.setAttribute("actionFailed", "Delete");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, "{0} does not support POST", LoginServlet.class.getName());
    }
}
