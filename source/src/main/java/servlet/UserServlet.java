package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegistStoresDAO;
import dao.UsersDAO;
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
		/*String  prefecture_name = request.getParameter("prefecture_name");
		//都道府県をプルダウンに
		PrefecturesDAO prefectureDao = new PrefecturesDAO();
		List<PrefecturesDTO> prefectureList = prefectureDao.distinct(new PrefecturesDTO(0, prefecture_name));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("prefectureList", prefectureList);
		*/
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
		//int prefecture_id = Integer.parseInt(request.getParameter("prefecture_id"));
		
		//都道府県ID INT変換　ここから
		String param = request.getParameter("prefecture_id");
		int prefecture_id = 0; // デフォルト値

		if (param != null && !param.trim().isEmpty()) {
		    prefecture_id = Integer.parseInt(param);
		}
		UsersDTO user = new UsersDTO();
		user.setPrefecture_id(prefecture_id);
		//ここまで

		// 登録処理(メールアドレス・パスワード)
		UsersDAO userDao = new UsersDAO();
		
		int userid = userDao.insert(new UsersDTO(0, address, password, prefecture_id, ""));
		if (userid > 0) { // 登録成功
			request.setAttribute("result", new ResultDTO("登録成功！", "新規ユーザー登録しました。 user_id=" + userid, "/f1/UserServlet"));
		} else { // 登録失敗
			request.setAttribute("result", new ResultDTO("登録失敗！", "新規ユーザー登録できませんでした。", "/f1/UserServlet"));
		}
		
		String[] phone_number = request.getParameterValues("phone_number");
	
		if (phone_number != null) {
			RegistStoresDAO registDao = new RegistStoresDAO();
			//ループ処理で、配列から1店舗ずつ取り出す
			for (String phoneNumber : phone_number) {
				//空文字（選択したけど名前が空など）が紛れ込んでいないかチェック
				if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
					//1店舗ずつDAOの登録メソッドを呼び出す
					registDao.insert(userid , phoneNumber);
				}
			}
			request.setAttribute("result", new ResultDTO("登録成功！", "新規ユーザー登録が完了しました。", "/f1/UserServlet"));
		} else {
			request.setAttribute("result", new ResultDTO("登録失敗！", "登録する店舗が選択されていません。", "/f1/UserServlet"));
		}
		// リザルトjspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
		
	}
}

