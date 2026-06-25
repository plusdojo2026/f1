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

import dao.AveragePricesDAO;
import dao.FeaturedItemsDAO;
import dao.RecipesDAO;
import dao.StoresDAO;
import dao.UsersDAO;
import dto.DetailDisplayDTO;
import dto.FeaturedItemsDTO;
import dto.RecipesDTO;
import dto.StoresDTO;
import dto.UsersDTO;

@WebServlet("/DetailServlet")

public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Getメソッド
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("address") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}
	}*/
	
	//Postメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		/*if (session.getAttribute("address") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}*/

		//まずセッションスコープからaddressを取得し、user_idを取得する
		String address = (String)session.getAttribute("address");
		UsersDAO usersDao = new UsersDAO();
		List<UsersDTO> usersList = usersDao.select(new UsersDTO(0, address, "", 0, ""));
		UsersDTO user = usersList.get(0);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String phoneNumber = request.getParameter("number");
		//recipe_idをInt型に変換
		String recipeIdStr = request.getParameter("recipe_id");
		int recipeId = 0;
		if (recipeIdStr !=null && !recipeIdStr.isEmpty()) {
			recipeId = Integer.parseInt(recipeIdStr);
		}
		
		//DAOのインスタンスを生成
		StoresDAO storesDao = new StoresDAO();
		RecipesDAO recipesDao = new RecipesDAO();
		FeaturedItemsDAO featuredItemsDao = new FeaturedItemsDAO();
		AveragePricesDAO averagePricesDao = new AveragePricesDAO();
		
		List<StoresDTO> storeList = storesDao.select(new StoresDTO(phoneNumber,"",0,"",""));
		List<RecipesDTO> recipeList = recipesDao.select(new RecipesDTO(recipeId,"","",""));
		List<FeaturedItemsDTO> itemList = featuredItemsDao.select(new FeaturedItemsDTO(0,phoneNumber,0,"","","",""));
		
		//1件の詳細データを入れるDTO
		DetailDisplayDTO detail = new DetailDisplayDTO();
		
		//店舗情報
		StoresDTO store = storeList.isEmpty() ? new StoresDTO() : storeList.get(0);
		detail.setStore_name(store.getStore_name());
		detail.setStore_appeal_long(store.getStore_appeal_long());
		
		//ユーザーメモ
		detail.setMemo(user.getMemo());
		
		//レシピ情報
		RecipesDTO recipe = recipeList.isEmpty() ? new RecipesDTO() : recipeList.get(0);
		detail.setRecipe_name(recipe.getRecipe_name());
		detail.setRecipe(recipe.getRecipe());
		
		//レシピ材料（複数）
		request.setAttribute("itemList", itemList);

		//必要目玉商品（複数）ここは宮崎さんに合わせて書き直す
		List<FeaturedItemsDTO> featuredItems = featuredItemsDao.selectl(recipeId);
		request.setAttribute("featuredItems", featuredItems);

		//その他の目玉商品（複数）
		List<FeaturedItemsDTO> otherItems = featuredItemsDao.selectByStoreId(phoneNumber);
		request.setAttribute("otherItems", otherItems);

		//detailは1件だけ渡す
		request.setAttribute("detailList", detail);
		
		//JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
		dispatcher.forward(request, response);
        
	}
}