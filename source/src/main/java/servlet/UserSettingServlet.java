package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RegistStoresDAO;
import dao.UsersDAO;
import dto.RegistStoresDTO;
import dto.ResultDTO;
import dto.UsersDTO;

@WebServlet("/UserSettingServlet")
public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSetting.jsp");
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
				
		// 登録処理(都道府県)
		UsersDAO userDao = new UsersDAO();
		
		if (userDao.update(new UsersDTO(0, "", "" , prefecture_id, ""))) { // 登録成功
			request.setAttribute("result", new ResultDTO("更新成功！", "更新完了しました。", "/f1/HomeServlet"));
		} else { // 登録失敗
			request.setAttribute("result", new ResultDTO("更新失敗！", "更新できませんでした。", "/f1/HomeServlet"));
		}
		
		//ログイン時に格納したuser_idを取得,選択した店舗の電話番号を取得
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("user_id");
		String[] phone_number = request.getParameterValues("phone_number");
		
		//登録店舗更新(削除→登録)
		RegistStoresDAO deleteRegist = new RegistStoresDAO();
		RegistStoresDTO deleteNumber = new RegistStoresDTO();
		deleteNumber.setUser_id(userid);
		deleteRegist.delete(deleteNumber);
		
		//新しく店舗登録
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
			request.setAttribute("result", new ResultDTO("更新成功！", "更新完了及び、" + phone_number.length + "件の店舗登録処理が完了しました。", "/f1/UserServlet"));
		} else {
			request.setAttribute("result", new ResultDTO("更新失敗！", "更新する店舗が選択されていません。", "/f1/UserSettingServlet"));
		}
		
		// リザルトjspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
		
	}
}
	