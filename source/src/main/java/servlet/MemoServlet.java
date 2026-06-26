package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import dto.LoginUserDTO;
import dto.UsersDTO;

/**
 * Servlet implementation class MemoServlet
 */
@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// フォームから受け取る
		String phoneNumber = request.getParameter("number");
		String memo = request.getParameter("memo");

		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("address");
		String address = loginUserDTO.getId();

		UsersDAO usersDao = new UsersDAO();
		List<UsersDTO> usersList = usersDao.select(new UsersDTO(0, address, "", 0, ""));
		UsersDTO user = usersList.get(0);
		int userId = user.getUser_id();

		// メモ更新
		UsersDTO dto = new UsersDTO();
		dto.setUser_id(user.getUser_id());
		dto.setMemo(memo);
		usersDao.updateMemo(dto);

		UsersDAO usersDAO = new UsersDAO();
		usersList = usersDAO.selectmemo(loginUserDTO);
		request.setAttribute("usersList", usersList);

		// 店舗表示ページにリダイレクトする
		response.sendRedirect("/f1/HomeServlet");
		
		// フォワードじゃダメ
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeServlet");
//		dispatcher.forward(request, response);
	}

}
