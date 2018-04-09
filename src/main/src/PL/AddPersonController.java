package PL;

import BLL.abstruct.INetWorkLogic;
import BLL.logic.NetWorkLogic;
import DAL.file.MariaDBDAL;
import enitities.PersonVM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet("/AddPersonController")
public class AddPersonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/AddPerson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO write process to find expectations
        PersonVM person = getPersonFromRequest(req);

        INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());
        bll.createPerson(person.toPersonDTO());

        req.getServletContext().setAttribute("Persons",  bll.getAllPersons());
        req.getRequestDispatcher("jsp\\DisplayPersonView.jsp").forward(req, resp);
    }

    private PersonVM getPersonFromRequest(HttpServletRequest req) {
        PersonVM person;
        String attendDate = req.getParameter("attendDate");
        String endDate = req.getParameter("endDate");

        String school = req.getParameter("school");
        if (school != null && school != "null" && school.trim() != "") {
            GregorianCalendar attendDateCalendar = new GregorianCalendar();
            GregorianCalendar endDateCalendar = new GregorianCalendar();
            attendDateCalendar.set(Calendar.YEAR, Integer.parseInt(attendDate));
            endDateCalendar.set(Calendar.YEAR, Integer.parseInt(endDate));
            person = new PersonVM(req.getParameter("firstName"), req.getParameter("middleName"), req.getParameter("secondName"), school, attendDateCalendar, endDateCalendar);
        } else {
            person = new PersonVM(req.getParameter("firstName"), req.getParameter("middleName"), req.getParameter("secondName"));
        }
        return person;
    }
}
