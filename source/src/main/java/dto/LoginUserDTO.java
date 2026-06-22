package dto;

import java.io.Serializable;

public class LoginUserDTO implements Serializable {
	private String address; // ログイン時のID

	public String getId() {
		return address;
	}

	public void setUserId(String address) {
		this.address = address;
	}

	public LoginUserDTO() {
		this(null);
	}

	public LoginUserDTO(String address) {
		this.address = address;
	}
}
