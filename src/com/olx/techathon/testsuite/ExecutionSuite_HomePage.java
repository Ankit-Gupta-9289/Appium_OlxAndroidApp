package com.olx.techathon.testsuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.olx.techathon.pom.HomePage;
import com.olx.techathon.utils.Utils;

public class ExecutionSuite_HomePage extends Utils{
	
	@Test
	public void Validate_Visibility_Categories_HomePage(){
		Click_Element(HomePage.Hamburger_Btn);
		WaitUntil(HomePage.SubmitaFreeAd_Btn);
		Assert.assertTrue(isVisible(HomePage.ListView_HomePage_LV),"HomePage Not Opening Properly!!");
	}

}
