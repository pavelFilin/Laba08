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
        String id2 = req.getParameter("id");
        UUID id = UUID.fromString(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String secondName = req.getParameter("secondName");
        String school= req.getParameter("school");
        String attendDate = req.getParameter("attendDate");
        String endDate = req.getParameter("endDate");
        GregorianCalendar attendDateCalend =  new GregorianCalendar();
        GregorianCalendar endDateCalend =  new GregorianCalendar();

        PersonDTO person = new PersonDTO();
        person.id = id;
        person.firstName = firstName;
        person.middleName = middleName;
        person.secondName = secondName;
        try{
            attendDateCalend.set(Calendar.YEAR, Integer.parseInt(attendDate));
            endDateCalend.set(Calendar.YEAR, Integer.parseInt(endDate));
            person.school=school;
            person.attendDate = attendDateCalend;
            person.endDate = endDateCalend;
        } catch (Exception e) {

        }

        INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());

        bll.update(person);

        req.getServletContext().setAttribute("Persons",  bll.getAllPersons());
        req.getRequestDispatcher("jsp\\DisplayPersonView.jsp").forward(req, resp);
    }
}
