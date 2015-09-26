package com.olx.techathon.testsuite;

import java.io.File;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.olx.techathon.pom.HomePage;
import com.olx.techathon.pom.SubmitaFreeAdPage;
import com.olx.techathon.utils.Utils;
import com.olx.techathon.xslt.Xslt_XlsReader;


public class ExecutionSuite extends Utils{

	@BeforeMethod
	public void StartDriver()
	{
		try {
			File file = new File(appLocation);
			String appPath = file.getAbsolutePath();
			driver = StartDriverAndroidApp(appPath, appPackage);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestSuite_Validate_Posting() {
		SubmitaFreeAdPage submitaFreeAdPage = new SubmitaFreeAdPage(driver, this);
		Xslt_XlsReader datatable = new Xslt_XlsReader(datasheetpath);
		String SheetName = "postafreead";
		Click_Element(HomePage.Hamburger_Btn);
		WaitUntil(HomePage.SubmitaFreeAd_Btn);
		Click_Element(HomePage.SubmitaFreeAd_Btn);
		WaitUntil(SubmitaFreeAdPage.AdTitle_LL);
		submitaFreeAdPage.FillForm_SubmitaFreeAd(datatable,SheetName,2);
	}


	@AfterMethod
	public void stopDriver() {
		StopDriver();
	}


	
}

