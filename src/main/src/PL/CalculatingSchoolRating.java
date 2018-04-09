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
import java.net.NetworkInterface;
import java.util.Map;

@WebServlet("/CalculatingSchoolRatingController")
public class CalculatingSchoolRating  extends HttpServlet {
    INetWorkLogic bll = new NetWorkLogic(new MariaDBDAL());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> calculateSchoolRating = bll.getCalculateSchoolRating();
        req.getServletContext().setAttribute("Schools", calculateSchoolRating);
        req.getRequestDispatcher("jsp\\CalculatingSchoolRating.jsp").forward(req, resp);
    }
}
