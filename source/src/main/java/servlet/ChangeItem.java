package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gemini.GeminiService;

@WebServlet("/ChangeItem")
public class ChangeItem extends HttpServlet {
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
				int recipe_id = gemini.generateRecipe(phoneNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}
