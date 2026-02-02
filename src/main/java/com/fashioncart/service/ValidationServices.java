package com.fashioncart.service;

public class ValidationServices {
	static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9_]{7,29}$";
	static final String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.com$";
	static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	static final String MOBILE_REGEX = "^[6-9]\\d{9}$";
	static final String FULLNAME_REGEX = "^[a-zA-Z]+(?: [a-zA-Z]+)*$";

	public static boolean validateUserName(String userName) {

		if (userName != null) {
			return userName.matches(USERNAME_REGEX);
		}

		return false;
	}

	public static boolean validateEmail(String email) {

		if (email != null) {
			email = email.toLowerCase();
			return email.matches(EMAIL_REGEX);
		}
		return false;
	}

	public static boolean validatePssword(String password) {
		if (password != null) {
			return password.matches(PASSWORD_REGEX);
		}
		return false;
	}

	public static boolean validatePhone(String phone) {
		if (phone != null) {
			return phone.matches(MOBILE_REGEX);
		}
		return false;
	}

	public static boolean validateFullName(String fullName) {

		if (fullName != null) {
			return fullName.matches(FULLNAME_REGEX);
		}
		return false;
	}
}
