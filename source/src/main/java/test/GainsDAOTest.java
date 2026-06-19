package test;

import java.util.List;

import dao.GainsDAO;
import dto.GainsDTO;

public class GainsDAOTest {

	public static void showAllData(List<GainsDTO> gainList) {
		/*表示数を調整可*/
		for (GainsDTO gain : gainList) {
			System.out.println("お得ID：" + gain.getGain_id());
			System.out.println("ユーザーID：" + gain.getUser_id());
			System.out.println("年月日：" + gain.getRecord_date());
			System.out.println("お得額合計：" + gain.getGain_sum());
			System.out.println("代替商品購入回数：" + gain.getAp_count());
			System.out.println();

		}
	}

	public static void main(String[] args) {
		GainsDAO dao = new GainsDAO();

		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		//データ未入力 テスト不可
		List<GainsDTO> gain1 = dao.select(new GainsDTO(0, 0, "", 0, 0));
		GainsDAOTest.showAllData(gain1);

		// select()のテスト2
		System.out.println("---------- select()のテスト2 ----------");
		List<GainsDTO> gain2 = dao.select(new GainsDTO(0, 0, "", 0, 0));
		GainsDAOTest.showAllData(gain2);

		// insert()のテスト
		System.out.println("---------- insert()のテスト ----------");
		GainsDTO insRec = new GainsDTO(0, 0, "", 0, 0);
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
			List<GainsDTO> gainIns = dao.select(new GainsDTO(0, 0, "", 180, 0));
			GainsDAOTest.showAllData(gainIns);
		} else {
			System.out.println("登録失敗！");
		}

	}

}
