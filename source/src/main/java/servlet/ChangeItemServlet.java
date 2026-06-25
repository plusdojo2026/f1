package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FeaturedItemsDAO;
import dto.FeaturedItemsDTO;

@WebServlet("/ChangeItemServlet")
public class ChangeItemServlet extends HttpServlet {
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
			//HttpSession session = request.getSession();
			/*if (session.getAttribute("address") == null) {
				response.sendRedirect("/f1/LoginServlet");
				return;
			}*/
			//リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String phoneNumber = request.getParameter("phone_number");
			
			String[] checkedIds = request.getParameterValues("featured_item_id");
			//何もチェックされていない場合は元の画面へ戻す
	        if (checkedIds == null) {
	            request.setAttribute("エラー", "商品が選択されていません。");
	            request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp").forward(request, response);
	            return;
	        }
			
	        //チェックされた商品だけ代替商品に置き換える
	        for (String idStr : checkedIds) {
	            int id = Integer.parseInt(idStr);
	            
	        
			//DTOを作成して検索条件をセット
			FeaturedItemsDTO fiDTO = new FeaturedItemsDTO();
		    fiDTO.setFeatured_item_id(id);
		    fiDTO.setPhone_number(phoneNumber);
		    
		    //DAOにDTOを渡す
		    FeaturedItemsDAO itemsDao = new FeaturedItemsDAO();
		    List<FeaturedItemsDTO> itemList = itemsDao.select(FeaturedItemsDTO(0,phoneNumber,0,"","","",""));

            // 元のリストの該当商品を探して置き換える
            for (int i = 0; i < newList.size(); i++) {
                if (newList.get(i).getId() == id) {
                    newList.set(i, alternative);
                    break;
                }
            }
			
			//結果をJSPに渡す
		    request.setAttribute("cItemList", itemList);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
		    dispatcher.forward(request, response);
		}
}
