package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    private final String LOGIN = "login";
    private final String LOGIN_URL = "/LoginServlet";

    private final String INDEX = "index";
    private final String INDEX_URL = "index.jsp";

    private final String ADMIN = "admin";
    private final String ADMIN_URL = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("a");
        if (action != null) {
            String url = "";
            switch (action) {
                case LOGIN:
                    url = LOGIN_URL;
                    break;

                default:
                    LOGGER.log(Level.SEVERE, "Invalid action");
            }
            request.getRequestDispatcher(url).include(request, response);
        }

        String page = request.getParameter("p");
        if (page == null) {
            request.getRequestDispatcher(INDEX_URL).forward(request, response);
        } else {
            String pageUrl = INDEX_URL;
            switch (page) {
                case INDEX:
                    pageUrl = INDEX_URL;
                    break;

                case ADMIN:
                    pageUrl = ADMIN_URL;
                    break;

                default:
                    LOGGER.log(Level.SEVERE, "Invalid page");
            }
            request.getRequestDispatcher(pageUrl).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
