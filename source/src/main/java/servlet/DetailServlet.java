package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DetailServlet")

public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}
		
		//詳細表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
		dispatcher.forward(request, response);
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String storeName = request.getParameter("store_name");
		String memo = request.getParameter("memo");
		String recipeName = request.getParameter("recipe_name");
		String recipe = request.getParameter("recipe");
		String featuredName = request.getParameter("featured_name");
		String price = request.getParameter("price");
		String averagePrice = request.getParameter("average_price");
		
	}
}
