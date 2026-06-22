package test;

import java.util.List;

import dao.UsersDAO;
import dto.UsersDTO;

public class UsersDAOTest {
	public static void showAllData(List<UsersDTO> userList) {
		for (UsersDTO user : userList) {
			System.out.println("メールアドレス：" + user.getAddress());
			System.out.println("パスワード：" + user.getPassword());
			System.out.println("都道府県ID：" + user.getPrefecture_id());
			System.out.println("電話番号：" + user.getPhone_number());
			System.out.println("メモ：" + user.getMemo());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		UsersDAO userDao = new UsersDAO();
		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		List<UsersDTO> userListSel1 = userDao.select(new UsersDTO(0, "", "", 0, "", ""));
		UsersDAOTest.showAllData(userListSel1);
		// select()のテスト2
		System.out.println("---------- select()のテスト2 ----------");
		List<UsersDTO> userListSel2 = userDao.select(new UsersDTO(0, "", "", 0, "", ""));
		UsersDAOTest.showAllData(userListSel2);
	
		System.out.println("---------- insert()のテスト ----------");
		UsersDTO insRec = new UsersDTO(0, "f1@gmail.com", "password", 0, "", "");
		if (userDao.insert(insRec)) {
			System.out.println("登録成功！");

		} else {
			System.out.println("登録失敗！");
		}
		// update()のテスト
		System.out.println("---------- update()のテスト ----------");
		List<UsersDTO> cardListUp = userDao.select(new UsersDTO(0, "", "", 0, "", ""));
		UsersDTO upRec = cardListUp.get(0);
		upRec.setPrefecture_id(37);
		if (userDao.update(upRec)) {
			System.out.println("更新成功！");
			cardListUp = userDao.select(new UsersDTO(0, "", "", 0, "", ""));
			UsersDAOTest.showAllData(cardListUp);
		} else {
			System.out.println("更新失敗！");
		}
	}
}