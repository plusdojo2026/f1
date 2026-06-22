package test;

import java.util.List;

import dao.RegistStoresDAO;
import dto.RegistStoresDTO;

public class RegistStoresDAOTest {

	public static void showAllData(List<RegistStoresDTO> registStoreList) {
		for (RegistStoresDTO regist : registStoreList) {
			/*表示数を調整可*/
			System.out.println("ユーザーID：" + regist.getUser_id());
			System.out.println("電話番号：" + regist.getPhone_number());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		RegistStoresDAO registStore = new RegistStoresDAO();

		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		//select
		List<RegistStoresDTO> registStoreSel1 = registStore.select(new RegistStoresDTO(0, ""));
		RegistStoresDAOTest.showAllData(registStoreSel1);

		// select()のテスト2
		System.out.println("---------- select()のテスト2 ----------");
		List<RegistStoresDTO> registStoreSel2 = registStore.select(new RegistStoresDTO(0, ""));
		RegistStoresDAOTest.showAllData(registStoreSel2);
		// insert()のテスト
		System.out.println("---------- insert()のテスト ----------");
		RegistStoresDTO insRec = new RegistStoresDTO(0, "1234567890");
		if (registStore.insert(insRec)) {
			System.out.println("登録成功！");
		}else {
			System.out.println("更新失敗！");
		}
	
		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		List<RegistStoresDTO> registListDel = registStore.select(new RegistStoresDTO(0, ""));
		RegistStoresDTO delRec = registListDel.get(0);
		if (registStore.delete(delRec)) {
			System.out.println("削除成功！");
			registListDel = registStore.select(new RegistStoresDTO(0, ""));
			RegistStoresDAOTest.showAllData(registListDel);
		} else {
			System.out.println("削除失敗！");
		}
	}
}
