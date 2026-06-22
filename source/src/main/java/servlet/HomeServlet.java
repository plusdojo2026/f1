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

import dao.FeaturedItemsDAO;
import dao.RecipesDAO;
import dao.RegistStoresDAO;
import dao.StoresDAO;
import dto.FeaturedItemsDTO;
import dto.RecipesDTO;
import dto.StoresDTO;


@WebServlet("/HomeServlet")

public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		//DAOの作成
		RegistStoresDAO registDao = new RegistStoresDAO();
		StoresDAO storeDao = new StoresDAO();
		RecipesDAO recipeDao = new RecipesDAO();
		FeaturedItemsDAO featuredDao = new FeaturedItemsDAO();

		//テーブルのデータ取得
		List<StoresDTO> storeList = storeDao.select(new StoresDTO(0,"","","",""));
		List<RecipesDTO> recipeList = recipeDao.select(new RecipesDTO(0,"","",""));
		List<FeaturedItemsDTO> itemList = featuredDao.select(new FeaturedItemsDTO(0,"",0,"","","",""));
		//DisplayDTOにまとめる
		List<DisplayDTO> cardList = new ArrayList<>();
		
		for(int i = 0; i < cardList.size(); i++) {
			DisplayDTO dto = new DisplayDTO();
			dto.setStoreName(storeList.get(i).getStore_name());
			dto.setRecipeName(recipeList.get(i).getRecipe_name());
			dto.setFeaturedItemName(itemList.get(i).getFeatured_item_name());
			dto.setTotalPrice(itemList.get(i).getPrice());
			dto.setShortPr(storeList.get(i).getStore_appeal_short());
			cardList.add(dto);
		}
		
		request.setAttribute("cardList", cardList);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("storeList", storeList);

		//店舗表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}

