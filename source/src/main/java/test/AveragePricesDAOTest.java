package test;

import java.util.List;

import dao.AveragePricesDAO;
import dto.AveragePriceDTO;

public class AveragePricesDAOTest {
	public static void showAllData(List<AveragePriceDTO> averageList) {
		for (AveragePriceDTO average : averageList) {
			System.out.println("平均価格ID：" + average.getAverage_price_id());
			System.out.println("目玉商品ID：" + average.getFeatured_item_id());
			System.out.println("都道府県ID：" + average.getPrefecture_id());
			System.out.println("平均価格：" + average.getAverage_price());
			System.out.println("更新された時間：" + average.getRecord_date());
			System.out.println();

		}
	}
	public static void main(String[] args) {
		AveragePricesDAO dao = new AveragePricesDAO();
		// insert()のテスト
		//中身は各自で調整してください
		System.out.println("---------- insert()のテスト ----------");
		AveragePriceDTO insRec = new AveragePriceDTO(0, 0, 0, 0, null);
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
		} else {
			System.out.println("登録失敗！");
		}
	}
}
