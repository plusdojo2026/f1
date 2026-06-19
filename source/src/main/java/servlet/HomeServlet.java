package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RegistStoresDAO;
import dto.RegistStoresDTO;

@WebServlet("/HomeServlet")

public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");

		RegistStoresDAO storeDao = new RegistStoresDAO();

		List<RegistStoresDTO> storeList = storeDao.selectAll();

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("storeList", storeList);

		//店舗表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}

