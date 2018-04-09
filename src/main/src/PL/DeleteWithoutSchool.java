package PL;

import BLL.abstruct.INetWorkLogic;
import BLL.logic.NetWorkLogic;
import DAL.file.MariaDBDAL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteWithoutSchool")
public class DeleteWithoutSchool extends HttpServlet {
    INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bll.deleteAllPersonsWithoutSchool();
        req.getRequestDispatcher("/ShowPersonsTable").forward(req, resp);
    }
}
