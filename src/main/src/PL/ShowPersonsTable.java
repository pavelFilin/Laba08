package PL;

import BLL.abstruct.INetWorkLogic;
import BLL.logic.NetWorkLogic;
import DAL.file.MariaDBDAL;
import enitities.PersonDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowPersonsTable")
public class ShowPersonsTable extends HttpServlet {
    INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().setAttribute("Persons",  bll.getAllPersons());
        req.getRequestDispatcher("jsp\\DisplayPersonView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Iterable<PersonDTO> allPersonByDateAndSchool = bll.getAllPersonByDateAndSchool(req.getParameter("school"), Integer.parseInt(req.getParameter("year")));
        req.getServletContext().setAttribute("Persons",  allPersonByDateAndSchool);
        req.getRequestDispatcher("jsp\\DisplayPersonView.jsp").forward(req, resp);
    }
}
