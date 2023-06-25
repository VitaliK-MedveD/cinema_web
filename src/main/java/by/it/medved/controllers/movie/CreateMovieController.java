package by.it.medved.controllers.movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/movie/create")
public class CreateMovieController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieTitle = req.getParameter("movieTitle");
        String showDate = req.getParameter("showDate");
        String showTime = req.getParameter("showTime");
        String price = req.getParameter("price");
        String ageLimit = req.getParameter("ageLimit");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
