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

import dao.DetailPriceDAO;
import dao.IngredientsDAO;
import dao.RecipesDAO;
import dao.StoresDAO;
import dao.UsersDAO;
import dto.DetailPriceDTO;
import dto.IngredientsDTO;
import dto.LoginUserDTO;
import dto.RecipesDTO;
import dto.StoresDTO;
import dto.UsersDTO;

@WebServlet("/DetailServlet")

public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Getメソッド
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("address") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}
			//まずセッションスコープからaddressを取得し、user_idを取得する
			LoginUserDTO loginUserDTO = (LoginUserDTO)session.getAttribute("address");
			String address =loginUserDTO.getId();
			UsersDAO usersDao = new UsersDAO();
			List<UsersDTO> usersList = usersDao.select(new UsersDTO(0, address, "", 0, ""));
			//UsersDTO user = usersList.get(0);
			
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String phoneNumber = request.getParameter("phone_number");
			//String memo = request.getParameter("memo");
			//recipe_idをInt型に変換
			//String recipeIdStr = request.getParameter("recipe_id");
			//int recipeId = 0;
			//if (recipeIdStr !=null && !recipeIdStr.isEmpty()) {
			//	recipeId = Integer.parseInt(recipeIdStr);
			//}
				
			
			//店舗情報
			StoresDAO storesDao = new StoresDAO();
			List<StoresDTO> storeList = storesDao.select(new StoresDTO(phoneNumber,"",0,"",""));
			request.setAttribute("storeList", storeList);
			
			//ユーザーメモ検索
			UsersDAO usersDAO = new UsersDAO();
			usersList = usersDAO.selectmemo(loginUserDTO);
			
			//リクエストパラメータに格納
			request.setAttribute("usersList", usersList);
			
			//レシピ関連情報
			//レシピDAO
			RecipesDAO recipesDAO = new RecipesDAO();
			RecipesDTO recipe = recipesDAO.select(phoneNumber).get(0);

			//材料DAO
			IngredientsDAO ingredientsDao = new IngredientsDAO();
			List<IngredientsDTO> ingredientsList =
			        ingredientsDao.select(phoneNumber, recipe.getRecipe_id());

			//JSPに渡す
			request.setAttribute("recipe", recipe);
			request.setAttribute("ingredientsList", ingredientsList);

			
			//目玉商品一覧と値段と平均価格
			DetailPriceDAO detailDAO = new DetailPriceDAO();
			List<DetailPriceDTO> dpList = detailDAO.select(new DetailPriceDTO(phoneNumber, "", 0, 0));
			request.setAttribute("dpList", dpList);
			
			//JSPへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
			dispatcher.forward(request, response);
			
		
	}
	//Postメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		//まずセッションスコープからaddressを取得し、user_idを取得する
		LoginUserDTO loginUserDTO = (LoginUserDTO)session.getAttribute("address");
		String address =loginUserDTO.getId();
		UsersDAO usersDao = new UsersDAO();
		List<UsersDTO> usersList = usersDao.select(new UsersDTO(0, address, "", 0, ""));
		UsersDTO user = usersList.get(0);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		String memo = request.getParameter("memo");
		
		//ユーザーメモ更新
		UsersDTO dto = new UsersDTO();
		dto.setUser_id(user.getUser_id());
		dto.setMemo(memo);
		usersDao.updateMemo(dto);
        
	}
}