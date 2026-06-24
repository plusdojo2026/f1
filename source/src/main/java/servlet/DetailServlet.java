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

import dao.AveragePricesDAO;
import dao.FeaturedItemsDAO;
import dao.RecipesDAO;
import dao.StoresDAO;
import dao.UsersDAO;
import dto.AveragePriceDTO;
import dto.DetailDisplayDTO;
import dto.FeaturedItemsDTO;
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
	}
	
	//Postメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("address") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}

		//まずセッションスコープからaddressを取得し、user_idを取得する
		String address = (String)session.getAttribute("address");
		UsersDAO usersDao = new UsersDAO();
		List<UsersDTO> usersList = usersDao.select(new UsersDTO(0, address, "", 0, ""));
		UsersDTO user = usersList.get(0);
		//int userId = user.getUser_id();

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String phoneNumber = request.getParameter("number");
		//recipe_idをInt型に変換
		String recipeIdStr = request.getParameter("recipe_id");
		int recipeId = 0;
		if (recipeIdStr !=null && !recipeIdStr.isEmpty()) {
			recipeId = Integer.parseInt(recipeIdStr);
		}
		
		//検索処理を行う(6/24ingredientsDAOとDTOの追加修正はここから)
		StoresDAO storesDao = new StoresDAO();
		RecipesDAO recipesDao = new RecipesDAO();
		FeaturedItemsDAO featuredItemsDao = new FeaturedItemsDAO();
		AveragePricesDAO averagePricesDao = new AveragePricesDAO();
		
		List<StoresDTO> storeList = storesDao.select(new StoresDTO(phoneNumber,"",0,"",""));
		List<RecipesDTO> recipeList = recipesDao.select(new RecipesDTO(recipeId,"","",""));
		List<FeaturedItemsDTO> itemList = featuredItemsDao.select(new FeaturedItemsDTO(0,phoneNumber,0,"","","",""));
		//List<UsersDTO> userList2 = usersDao.select(new UsersDTO(userId,"","",0,"",""));
		
		
		// DTO が空の場合の対策
        StoresDTO store = storeList.isEmpty() ? new StoresDTO() : storeList.get(0);
        RecipesDTO recipe = recipeList.isEmpty() ? new RecipesDTO() : recipeList.get(0);
        FeaturedItemsDTO item = itemList.isEmpty() ? new FeaturedItemsDTO() : itemList.get(0);
        
		
        
        int FeaturedItemId = item.getFeatured_item_id();
        List<AveragePriceDTO> averagePriceList = averagePricesDao.select(new AveragePriceDTO(0,FeaturedItemId,0,0,null));
        AveragePriceDTO avg = averagePriceList.isEmpty() ? new AveragePriceDTO() : averagePriceList.get(0);
        
		//DetailDisplayDTOにまとめる
		List<DetailDisplayDTO> detailList = new ArrayList<>();
		
		DetailDisplayDTO dto = new DetailDisplayDTO();
		dto.setStore_name(store.getStore_name());
		dto.setStore_appeal_long(store.getStore_appeal_long());
		dto.setMemo(user.getMemo());
		dto.setRecipe_name(recipe.getRecipe_name());
		dto.setRecipe(recipe.getRecipe());
		dto.setFeatured_item_name(item.getFeatured_item_name());
		dto.setAverage_price(avg.getAverage_price());
		detailList.add(dto);
		
		//まとめたデータをリクエストスコープに格納する
		request.setAttribute("detailList", detailList);
		
		//詳細表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
		dispatcher.forward(request, response);
	}
}