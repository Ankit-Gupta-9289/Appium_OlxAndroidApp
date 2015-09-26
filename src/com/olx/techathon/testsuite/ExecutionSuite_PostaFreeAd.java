package com.olx.techathon.testsuite;

import java.io.File;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import xslt.Xslt_XlsReader;

import com.olx.techathon.pom.HomePage;
import com.olx.techathon.pom.SubmitaFreeAdPage;
import com.olx.techathon.utils.Utils;


public class ExecutionSuite_PostaFreeAd extends Utils{
	Xslt_XlsReader datatable;
	
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
	
	@DataProvider(name = "TestSuite_Validate_PostaFreeAdFlow")
	public Object[][] DataSheetTraverser_PostaFreeAdFlow() {
		String SheetName = "postafreead";
	    datatable = new Xslt_XlsReader(datasheetpath);
		int rowcount = datatable.getRowCount(SheetName);
		
		Object result[][] = new Object[rowcount - 1][1];
		for (int i = 2; i < rowcount + 1; i++) {
			result[i - 2][0] = i;
		}
		return result;
	}

	@Test(dataProvider = "TestSuite_Validate_PostaFreeAdFlow")
	public void TestSuite_Validate_PostaFreeAdFlow(int rowno) {
		SubmitaFreeAdPage submitaFreeAdPage = new SubmitaFreeAdPage(driver, this);
		String SheetName = "postafreead";
		Click_Element(HomePage.Hamburger_Btn);
		WaitUntil(HomePage.SubmitaFreeAd_Btn);
		Click_Element(HomePage.SubmitaFreeAd_Btn);
		WaitUntil(SubmitaFreeAdPage.AdTitle_LL);
		submitaFreeAdPage.FillForm_SubmitaFreeAd(datatable,SheetName,rowno);
	}


	@AfterMethod
	public void stopDriver() {
		StopDriver();
	}


	
}

