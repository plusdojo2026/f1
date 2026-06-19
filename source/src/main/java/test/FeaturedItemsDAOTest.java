package test;

import java.util.List;

import dao.FeaturedItemsDAO;
import dto.FeaturedItemsDTO;

public class FeaturedItemsDAOTest {

	public static void showAllData(List<FeaturedItemsDTO> featuredItems) {
		for (FeaturedItemsDTO featured : featuredItems) {
			System.out.println("目玉商品ID：" + featured.getFeatured_item_id());
			System.out.println("店舗ID：" + featured.getStore_id());
			System.out.println("価格：" + featured.getPrice());
			System.out.println("目玉商品名：" + featured.getFeatured_item_name());
			System.out.println();
		}	
	}	
	public static void main(String[] args) {
		FeaturedItemsDAO dao = new FeaturedItemsDAO();

		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		//
		List<FeaturedItemsDTO> featuredSel1 = dao.select(new FeaturedItemsDTO(0, 0, 0, "卵", "", "", ""));
		FeaturedItemsDAOTest.showAllData(featuredSel1);
		// select()のテスト2
		System.out.println("---------- select()のテスト2 ----------");
		List<FeaturedItemsDTO> featuredSel2 = dao.select(new FeaturedItemsDTO(0, 0, 0, "", "", "", ""));
		FeaturedItemsDAOTest.showAllData(featuredSel2);
		
		// update()のテスト
		System.out.println("---------- update()のテスト ----------");
		//※テストの中身はサンプルデータに応じて変更してください
		List<FeaturedItemsDTO> featuredListUp = dao.select(new FeaturedItemsDTO(0, 0, 0, "卵", "", "", ""));
		FeaturedItemsDTO upRec = featuredListUp.get(0);
		upRec.setFeatured_item_name("ささみ");	//何の項目を何に変更するか
		if (dao.update(upRec)) {
			System.out.println("更新成功！");
			featuredListUp = dao.select(new FeaturedItemsDTO(0, 0, 0, "", "", "", ""));
			FeaturedItemsDAOTest.showAllData(featuredListUp);
		} else {
			System.out.println("更新失敗！");
		}

	}
}
