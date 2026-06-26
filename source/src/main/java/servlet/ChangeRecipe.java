package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecipesDAO;
import dto.RecipesDTO;
import gemini.GeminiService;

public class ChangeRecipe {
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
				if (session.getAttribute("address") == null) {
					response.sendRedirect("/f1/LoginServlet");
					return;
				}
				// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String phoneNumber = request.getParameter("phone_number");
				
				//レシピを再生成する
				GeminiService gemini = new GeminiService();
				Integer recipeId = null;
				try {
					recipeId = gemini.generateRecipe(phoneNumber);
				//もしGeminiAPIの問題で生成に失敗したら、DBにrecipe_idを取得しに行く
				} catch (Exception e) {
		            RecipesDAO Rdao = new RecipesDAO();
		            RecipesDTO Rdto = new RecipesDTO();
		            Rdto.setPhone_number(phoneNumber);

		            List<RecipesDTO> list = Rdao.select(Rdto);

		            //最新のrecipe_idを取得する
		            if (!list.isEmpty()) {
		                //listはrecipe_id昇順で返ってくるので、一番最後の要素を使う
		                recipeId = list.get(list.size() - 1).getRecipe_id();
		            }

				}
				
				//かっこの外にGeminiもしくはDBからとってきたデータを詰める処理を書く
				
				
				//結果をJSPに渡す
				request.setAttribute("List", List);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
			    dispatcher.forward(request, response);
				
				
				
		}
}
