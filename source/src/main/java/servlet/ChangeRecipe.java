package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecipesDAO;
import dto.RecipesDTO;
import gemini.GeminiBusyException;
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
				try {
					int recipeId = gemini.generateRecipe(phoneNumber);
					
				} catch (GeminiBusyException e) {
		            /*このかっこの中にDBにアクセスしてデータを取り出す処理を書く*/
		            
				}
				
				//かっこの外にGeminiもしくはDBからとってきたデータを詰める処理を書く
				//以下要修正
				
	            RecipesDTO recipe = new RecipesDTO();
				recipe.getRecipe_name();
		        //この下に追加したDAO、DTOをもとにDBからデータを取り寄せるプログラムを作成。名前を仮にComplexDAOとする
				
				//DTOを作成して検索条件をセット
				ComplexDTO coDTO = new ComplexDTO();
				coDTO.set/*ここからDTOの中身をもとに作成*/
				
				//DAOにDTOを渡す
				ComplexDAO coDao = new ComplexDAO();
				List<ComplexDTO> coList = coDao.select(coDTO);
				
				//結果をJSPに渡す
				request.setAttribute("coList", coList);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
			    dispatcher.forward(request, response);
				
				
				
		}
}
