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
import dao.UsersDAO;
import dto.FeaturedItemsDTO;
import dto.HomeDisplayDTO;
import dto.RecipesDTO;
import dto.StoresDTO;
import dto.UsersDTO;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/f1/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");

		//フォームからメモを受け取る
		String memo = request.getParameter("memo");
		//セッションからユーザーIDを取得
		int userId = (int) session.getAttribute("id");
		//ユーザーIDとメモをDTOに格納
		UsersDTO dto = new UsersDTO();
		dto.setUser_id(userId);
		dto.setMemo(memo);
		//DAOを呼び出してメモを更新
		UsersDAO dao = new UsersDAO();
		dao.updateMemo(dto);
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//DAOの作成
		RegistStoresDAO registstoreDao = new RegistStoresDAO();
		StoresDAO storeDao = new StoresDAO();
		RecipesDAO recipeDao = new RecipesDAO();
		FeaturedItemsDAO featuredDao = new FeaturedItemsDAO();
		//テーブルのデータ取得
		List<StoresDTO> storeList = storeDao.select(new StoresDTO("","","","",""));
		List<RecipesDTO> recipeList = recipeDao.select(new RecipesDTO(0,"","",""));
		List<FeaturedItemsDTO> itemList = featuredDao.select(new FeaturedItemsDTO(0,"",0,"","","",""));
		//HomeDisplayDTOにまとめる
		List<HomeDisplayDTO> cardList = new ArrayList<>();

		for(int i = 0; i < storeList.size(); i++) {
			HomeDisplayDTO homedto = new HomeDisplayDTO();
			homedto.setStore_name(storeList.get(i).getStore_name());
			homedto.setRecipe_name(recipeList.get(i).getRecipe_name());
			homedto.setFeatured_item_name(itemList.get(i).getFeatured_item_name());
			homedto.setPrice(itemList.get(i).getPrice());
			homedto.setStore_appeal_short(storeList.get(i).getStore_appeal_short());
			cardList.add(homedto);
		}
		
		//合計金額を計算
		int totalPrice = 0;
		
		
		//店舗名、料理名、目玉商品、合計金額、店舗PRをカードリストに格納する
		request.setAttribute("cardList", cardList);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("storeList", storeList);
		//合計金額を格納する
		request.setAttribute("totalPrice",totalPrice);
		
		//店舗表示ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}

