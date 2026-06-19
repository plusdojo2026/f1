package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PrefecturesDAO;
import dao.UsersDAO;
import dto.PrefecturesDTO;
import dto.ResultDTO;
import dto.UsersDTO;


/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String  prefecture_name = request.getParameter("prefecture_name");
		//都道府県をプルダウンに
		PrefecturesDAO prefectureDao = new PrefecturesDAO();
		List<PrefecturesDTO> prefectureList = prefectureDao.distinct(new PrefecturesDTO(0, prefecture_name));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("prefectureList", prefectureList);
		
		// 新規登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String prefecture_name = request.getParameter("prefecture");
		String phone_number = request.getParameter("phone_number");
		// 登録処理(メールアドレス・パスワード)
		UsersDAO userDao = new UsersDAO();
		if (userDao.insert(new UsersDTO(0, address, password, prefecture_name, phone_number, ""))) { // 登録成功
			request.setAttribute("result", new ResultDTO("登録成功！", "レコードを登録しました。", "/WebContent/LoginServlet"));
		} else { // 登録失敗
			request.setAttribute("result", new ResultDTO("登録失敗！", "レコードを登録できませんでした。", "/WebContent/LoginServlet"));
		}
		
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
		
	}
}

