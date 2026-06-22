package test;

import java.util.List;

import dao.RegistStoresDAO;
import dto.RegistStoresDTO;

public class RegistStoresDAOTest {

	public static void showAllData(List<RegistStoresDTO> registStoreList) {
		for (RegistStoresDTO user : registStoreList) {
			/*表示数を調整可*/
			System.out.println("ユーザーID：" + user.getUser_id());
			System.out.println("店舗ID：" + user.getStore_id());
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		RegistStoresDAO registStore = new RegistStoresDAO();
	
		// insert()のテスト
		System.out.println("---------- insert()のテスト ----------");
		RegistStoresDTO insRec = new RegistStoresDTO(0, 1234567890);
		if (registStore.insert(insRec)) {
			System.out.println("登録成功！");
		}else {
			System.out.println("更新失敗！");}
		}
	}
		// delete()のテスト  エラー出る
		/*System.out.println("---------- delete()のテスト ----------");
		List<RegistStoresDTO> registStoreDel = registStore.select(new RegistStoresDTO(0, 1234567890));
		RegistStoresDTO delRec = registStoreDel.get(0);
		if (dao.delete(delRec)) {
			System.out.println("削除成功！");
			registStoreDel = registStore.select(new RegistStoresDTO(0, 0));
			RegistStoresDAOTest.showAllData(registStoreDel);
		} else {
			System.out.println("削除失敗！");
		}*/
