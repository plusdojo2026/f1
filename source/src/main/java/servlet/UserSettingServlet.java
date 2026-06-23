package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GainsDAO;
import dto.GainsDTO;

@WebServlet("/UserSettingServlet")
public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// DAO取得
		GainsDAO dao = new GainsDAO();

		GainsDTO param = new GainsDTO();
		param.setRecord_date(null);

		List<GainsDTO> list = dao.select(param);

		// null対策
		if (list == null) {
		    list = new ArrayList<GainsDTO>();
		}

		// 集計用
		Map<String, GainsDTO> monthlyMap = new LinkedHashMap<String, GainsDTO>();
		Map<Integer, GainsDTO> yearlyMap = new LinkedHashMap<Integer, GainsDTO>();

		for (GainsDTO g : list) {

		    // 日付チェック
		    if (g.getRecord_date() == null || g.getRecord_date().length() < 7) {
		        continue;
		    }

		    String date = g.getRecord_date();

		    String[] parts = date.split("-");
		    if (parts.length < 2) continue;

		    String year = parts[0];
		    String month = parts[1];

		    //月を2桁に統一
		    if (month.length() == 1) {
		        month = "0" + month;
		    }

		    // ---------- 月別 ----------
		    String key = year + "-" + month;

		    if (!monthlyMap.containsKey(key)) {
		        GainsDTO m = new GainsDTO();
		        m.setRecord_date(key);
		        m.setGain_sum(0);
		        m.setAp_count(0);
		        monthlyMap.put(key, m);
		    }


		    GainsDTO m = monthlyMap.get(key);

		    int gain = g.getGain_sum();
		    int count = g.getAp_count();

		    m.setGain_sum(m.getGain_sum() + gain);
		    m.setAp_count(m.getAp_count() + count);


		    // ---------- 年別 ----------
		    int y = Integer.parseInt(year);

		    if (!yearlyMap.containsKey(y)) {
		        GainsDTO yr = new GainsDTO();
		        yr.setRecord_date(year);   // ← 年をセット
		        yr.setGain_sum(0);
		        yr.setAp_count(0);
		        yearlyMap.put(y, yr);
		    }

		    GainsDTO yr = yearlyMap.get(y);
		    yr.setGain_sum(yr.getGain_sum() + gain);
		    yr.setAp_count(yr.getAp_count() + count);
		}

		// Listに変換
		List<GainsDTO> monthlyList = new ArrayList<GainsDTO>(monthlyMap.values());
		List<GainsDTO> yearlyList = new ArrayList<GainsDTO>(yearlyMap.values());

		// JSPへ渡す
		request.setAttribute("monthlyList", monthlyList);
		request.setAttribute("yearlyList", yearlyList);

		// 最後にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/summary.jsp");
		dispatcher.forward(request, response);
	}
}
	