package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IngredientsDAO;
import dao.RecipesDAO;
import dao.StoresDAO;
import dao.UsersDAO;
import dto.IngredientsDTO;
import dto.LoginUserDTO;
import dto.RecipesDTO;
import dto.StoresDTO;
import dto.UsersDTO;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("address") == null) {
			response.sendRedirect("/f1/");
			return;
		}

		// セッションスコープからアドレスを取得する
		LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("address");
		UsersDAO usersDAO = new UsersDAO();
		List<UsersDTO> usersList = usersDAO.selectmemo(loginUserDTO);
		request.setAttribute("usersList", usersList);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		StoresDAO storeDao = new StoresDAO();
		List<StoresDTO> storesList = storeDao.selectstores(usersList.get(0));
		request.setAttribute("storesList", storesList);

		RecipesDAO recipesDAO = new RecipesDAO();
		List<RecipesDTO> recipesList = new ArrayList();
		
		IngredientsDAO ingredientsDao = new IngredientsDAO();
		List<List> ingredientsListList = new ArrayList();
		
		String phoneNumber = "";
		
		for (StoresDTO storesDTO : storesList) {
			phoneNumber = storesDTO.getPhone_number();
			
			RecipesDTO recipesDTO = recipesDAO.selectrecipes(phoneNumber).get(0);
			recipesList.add(recipesDTO);
			List<IngredientsDTO> ingredientsList = ingredientsDao.select(
					phoneNumber, recipesDTO.getRecipe_id());
			ingredientsListList.add(ingredientsList);
		}
		request.setAttribute("recipesList", recipesList);
		request.setAttribute("ingredientsListList", ingredientsListList);


////		GeminiService gemini = new GeminiService();
//		try {
//			//レシピ生成メソッドを実行し、recipe_idを受け取る
//				int recipe_id = gemini.generateRecipe(phoneNumber);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		//recipe_idをInt型に変換
//		String recipeIdStr = request.getParameter("recipe_id");
//		int recipeId = 0;
//		if (recipeIdStr !=null && !recipeIdStr.isEmpty()) {
//			recipeId = Integer.parseInt(recipeIdStr);
//		}

//
//		//テーブルのデータ取得

//		List<IngredientsDTO> ingredientsList = ingredientsDao.select(new IngredientsDTO());
//		//HomeDisplayDTOにまとめる
//		List<HomeDisplayDTO> cardList = new ArrayList<>();
//
//		//DTOが空の場合の対策
//		StoresDTO store = storeList.isEmpty() ? new StoresDTO() : storeList.get(0);
//		RecipesDTO recipe = recipeList.isEmpty() ? new RecipesDTO() : recipeList.get(0);
//		FeaturedItemsDTO item = featuredList.isEmpty() ? new FeaturedItemsDTO() : featuredList.get(0);
//		IngredientsDTO ingredients = featuredList.isEmpty() ? new IngredientsDTO() : ingredientsList.get(0);
//
//		for(int i = 0; i < storeList.size(); i++) {
//			HomeDisplayDTO homedto = new HomeDisplayDTO();
//			homedto.setStore_name(storeList.get(i).getStore_name());
//			homedto.setRecipe_name(recipeList.get(i).getRecipe_name());
//			homedto.setIngredients_name(ingredientsList.get(i).getIngredients_name());
//			homedto.setPrice(featuredList.get(i).getPrice());
//			homedto.setStore_appeal_short(storeList.get(i).getStore_appeal_short());
//			cardList.add(homedto);
//		}
//
//		//合計金額を計算
//		int totalPrice = 0;
//		for(IngredientsDTO i : ingredientsList) {
//			totalPrice += i.getPrice();;
//		}
//

//		request.setAttribute("totalPrice",totalPrice);

		// 店舗表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

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

		// 店舗表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}
