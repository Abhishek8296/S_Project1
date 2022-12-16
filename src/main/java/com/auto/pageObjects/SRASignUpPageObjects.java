package com.auto.pageObjects;

public class SRASignUpPageObjects {

	
	//Locators
	public static String signupLink = "a[href*='sranonprod.org.uk/aad/signup']";
//	public static String signupLink = "a[href=\"https://mysra-gold.sradev.org.uk/aad/signup?ngsw-bypass=true\"]";
	public static String userName = "input[id=\"UserId\"]";
	public static String password= "input[id=\"newPassword\"]";
	public static String confirmPassword = "input[id=\"reenterPassword\"]";
	public static String email = "input[id=\"email\"]";
	public static String sendVerificationCodeButton = "button[id=\"email_ver_but_send\"]";
	public static String continueButton = "button[id=\"continue\"]";
	public static String verificationCode = "input[id=\"email_ver_input\"]";
	public static String submitCodeButton = "button[id=\"email_ver_but_verify\"]";
	public static String continueButton2 = "button[id=\"continue\"]";
	public static String firstName = "input[id*=\"firstname\"]";
	public static String lastName = "input[id*=\"lastname\"]";
	public static String dob = "input[id*=\"dateofbirth2\"]";
	public static String contactEmail = "input[id*=\"emailaddress\"]";
	public static String personalAddress = "input[placeholder*=\"Enter the first line of your address\"]";
	public static String saveButton = "button.btn.btn-primary.btn-md.save-continue";
	public static String logoutButton = "//button[text()=\" Logout \"]";
	public static String loader = "svg[id=\"Layer_1\"]";
	public static String test = "gsahg";
//	public static String enterAddressManually = "button[name=\"data[notintheUKorwanttoentertheaddressmanually]\"]";
//	public static String addressLine1 = "input[id*=\"addressLine1\"]";
//	public static String city = "input[id*=\"addressCity\"]";

}
