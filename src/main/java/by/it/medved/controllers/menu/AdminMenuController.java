package by.it.medved.controllers.menu;

import by.it.medved.util.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/menu")
public class AdminMenuController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case "showListUsers" :
                req.getRequestDispatcher(Link.USERS_READ_URI).forward(req, resp);
                break;
            case "editProfile" :
                req.getRequestDispatcher(Link.USER_EDIT_PAGE).forward(req, resp);
                break;
            case "showListMovies" :
                req.getRequestDispatcher("/movie/read").forward(req, resp);
                break;
            case "addMovie" :
                req.getRequestDispatcher("/pages/movie/create-movie.jsp").forward(req, resp);
                break;
            case "exit" :
                req.getRequestDispatcher("/exit").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/user/admin-menu.jsp").forward(req, resp);
    }
}
