package test;

import java.util.List;

import dao.StoresDAO;
import dto.StoresDTO;

public class StoresDAOTest {
	//テストを行う方へ　getStore_idがINT型、getPrefecture_idがString型となっています。ご注意ください。
	//表示項目数は調整可能
	public static void showAllData(List<StoresDTO> storeList) {
		for (StoresDTO store : storeList) {
			System.out.println("電話番号：" + store.getStore_id());
			System.out.println("店舗名：" + store.getStore_name());
			System.out.println("都道府県ID：" + store.getPrefecture_id());
			System.out.println("店舗アピール(短)：" + store.getStore_appeal_short());
			System.out.println("店舗アピール(長)：" + store.getStore_appeal_long());
			System.out.println();
		}
	}
	public static void main(String[] args) {
		StoresDAO dao = new StoresDAO();
		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		//中身はサンプルデータに合わせて入れる
		List<StoresDTO> cardListSel1 = dao.select(new StoresDTO(0, "", "", "", ""));
		StoresDAOTest.showAllData(cardListSel1);
		// select()のテスト2
		System.out.println("---------- select()のテスト2 ----------");
		List<StoresDTO> cardListSel2 = dao.select(new StoresDTO(0, "", "", "", ""));
		StoresDAOTest.showAllData(cardListSel2);

		
	}

}
