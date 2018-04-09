package PL;

import BLL.abstruct.INetWorkLogic;
import BLL.logic.NetWorkLogic;
import DAL.file.MariaDBDAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/DeleteController")
public class DeletePersonController extends HttpServlet {

    INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id==null || id==""){
            throw new NullPointerException();
        }

        bll.deletePerson(UUID.fromString(id));
        req.getRequestDispatcher("/ShowPersonsTable").forward(req, resp);
    }
}
