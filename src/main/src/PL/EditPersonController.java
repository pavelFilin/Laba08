package PL;

import BLL.abstruct.INetWorkLogic;
import BLL.logic.NetWorkLogic;
import DAL.file.MariaDBDAL;
import enitities.PersonDTO;
import enitities.PersonVM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

@WebServlet("/EditPersonController")
public class EditPersonController extends HttpServlet {
    INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id == "") {
            throw new NullPointerException();
        }
        PersonDTO personDTO = bll.getPerson(UUID.fromString(id));
        req.getServletContext().setAttribute("Person", personDTO);
        req.getRequestDispatcher("jsp/EditPerson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO write process to find expectations
        PersonVM person = getPersonFromRequest(req);
        INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());
        bll.update(person.toPersonDTO());

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
