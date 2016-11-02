package com.carteblanche.kwd.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login {
	
	@Test
	@Parameters({"userName","password"})
	public void login(String userName, String password){
//		fill username "hello"
//		fill password "wrong"
//		submit APIRequest expect true
		System.out.println("Logging in with UserName: "+ userName +"\tPassword: "+password);
	}
	
	@Test(dependsOnMethods={"login"})
	public void doSomeThing(){
		System.out.println("I am doing some thing.. don't disturb me");
	}

}
