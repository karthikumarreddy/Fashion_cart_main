package com.fashioncart.service;

public class ValidationServices {
	static final String USERNAME_REGEX = "^[a-zA-Z].{7,29}$";
	static final String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.com$";
	static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	static final String FULLNAME_REGEX = "^[a-zA-Z]+(?: [a-zA-Z]+)*$";
	static final String ADDRESS1_REGEX = "^[a-zA-Z0-9 ,./#-]{5,100}$";
	static final String ADDRESS2_REGEX = "^[a-zA-Z0-9 ,./#-]{0,100}$";
	static final String CITY_REGEX = "^[a-zA-Z ]{2,50}$";
	static final String PINCODE_REGEX = "^[1-9][0-9]{5}$";
	static final String MOBILE_REGEX = "^[6-9][0-9]{9}$";

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

	public static boolean validateAddress1(String address1) {

		if (address1 != null) {
			return address1.matches(ADDRESS1_REGEX);
		}
		return false;
	}

	public static boolean validateAddress2(String address2) {

		if (address2 != null) {
			return address2.matches(ADDRESS2_REGEX);
		}
		return false;
	}

	public static boolean validateCity(String city) {

		if (city != null) {
			return city.matches(CITY_REGEX);
		}
		return false;
	}

	public static boolean validatePincode(String pincode) {

		if (pincode != null) {
			return pincode.matches(PINCODE_REGEX);
		}
		return false;
	}

	public static boolean validateMoileNumber(String mobileNumber) {

		if (mobileNumber != null) {
			return mobileNumber.matches(MOBILE_REGEX);
		}
		return false;
	}
}
