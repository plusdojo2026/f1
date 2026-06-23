package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IdPwDAO;
import dto.LoginUserDTO;
import dto.ResultDTO;
import dto.UsersDTO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
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

		// ログイン処理を行う
		IdPwDAO iDao = new IdPwDAO();
		if (iDao.isLoginOK(new UsersDTO(0,address,password,0,"",""))) { // ログイン成功
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("address", new LoginUserDTO(address));
			
			//ホームサーブレットにリダイレクトする
			response.sendRedirect("/f1/HomeServlet");
		}else { // ログイン失敗
				// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
				request.setAttribute("result", new ResultDTO("もう一度入力してください。", "AddressまたはPasswordに間違いがあります。", "/WebContent/LoginServlet"));

			// 店舗表示ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			dispatcher.forward(request, response);
		}
	}
}
