package com.olx.techathon.pom;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import xslt.Xslt_XlsReader;

import com.olx.techathon.utils.Utils;

public class SubmitaFreeAdPage {
	AndroidDriver driver;
	Utils utils;

	public static final By AdTitle_LL = By.id("com.olx.southasia.hackathon:id/titleView");
	public static final By AdTitle_Txt = By.id("com.olx.southasia.hackathon:id/value");

	public static final By ChooseCategory_ll = By.id("com.olx.southasia.hackathon:id/categoryChooser");
	public static final By ChooseCategory_DD = By.id("com.olx.southasia.hackathon:id/chooserBtn");

	public static final By Categorylist_LV =By.id("com.olx.southasia.hackathon:id/name");

	public static final By Description_LL = By.id("com.olx.southasia.hackathon:id/descriptionView");
	public static final By Description_Txt = By.id("com.olx.southasia.hackathon:id/value");
	
	public static final By Location_LL = By.id("com.olx.southasia.hackathon:id/locationChooser");
	public static final By Location_DD = By.id("com.olx.southasia.hackathon:id/chooserBtn");
	
	public static final By Name_LL = By.id("com.olx.southasia.hackathon:id/contactPersonView");
	public static final By Name_Txt = By.id("com.olx.southasia.hackathon:id/value");
	
	public static final By Price_LL = By.id("");
	public static final By Price_Txt = By.id("");
		
	public static final By Email_Address_LL = By.id("com.olx.southasia.hackathon:id/emailView");
	public static final By Email_Address_Txt = By.id("com.olx.southasia.hackathon:id/value");
	
	public static final By Phone_Number_LL = By.id("com.olx.southasia.hackathon:id/phoneNumberView");
	public static final By Phone_Number_Txt = By.id("com.olx.southasia.hackathon:id/value");
	
	public static final By SubmitAd_Btn = By.id("com.olx.southasia.hackathon:id/postAdBtn");

	public SubmitaFreeAdPage(AndroidDriver driver, Utils utils) {
		this.driver = driver;
		this.utils = utils;
	}

	public void Fill_AdTitle_Txt(String Value){
		driver.findElement(AdTitle_LL).findElement(AdTitle_Txt).sendKeys(Value);
		utils.HideKeyboard();
	}

	public void Select_Category_DD(String Value){
		driver.findElement(ChooseCategory_ll).findElement(ChooseCategory_DD).click();
		utils.WaitUntil(Categorylist_LV);
		String Str[] = Value.split(";");
		List<WebElement> we = driver.findElements(Categorylist_LV);

		for (WebElement webElement : we) {
			if(webElement.getText().equals(Str[0])){
				webElement.click();
				break;
			}
		}

		we = driver.findElements(Categorylist_LV);

		for (WebElement webElement : we) {
			if(webElement.getText().equals(Str[1])){
				webElement.click();
				break;
			}
		}

	    we = driver.findElements(Categorylist_LV);

		for (WebElement webElement : we) {
			if(webElement.getText().equals(Str[2])){
				webElement.click();
				break;
			}
		}

	}
	
	public void Select_Location_DD(String Value){
		driver.findElement(Location_LL).findElement(Location_DD).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text = '"+Value+"']")).click();
	}

	public void Fill_Description_Txt(String Value){
		driver.findElement(Description_LL).findElement(Description_Txt).sendKeys(Value);
		utils.HideKeyboard();
	}
	
	public void Fill_Price_Txt(String Value){
		driver.findElement(Price_LL).findElement(Price_Txt).sendKeys(Value);
		utils.HideKeyboard();
	}
	
	public void Fill_Name_Txt(String Value){
		driver.findElement(Name_LL).findElement(Name_Txt).sendKeys(Value);
		utils.HideKeyboard();
	}
	
	public void Fill_Email_Address_Txt(String Value){
		driver.findElement(Email_Address_LL).findElement(Email_Address_Txt).sendKeys(Value);
		utils.HideKeyboard();
	}
	
	public void Fill_Phone_Number_Txt(String Value){
		driver.findElement(Phone_Number_LL).findElement(Phone_Number_Txt).sendKeys(Value);
		utils.HideKeyboard();
	}

	public void Click_SubmitAd_Btn(){
		driver.findElement(SubmitAd_Btn).click();
	}
	

	public void FillForm_SubmitaFreeAd(Xslt_XlsReader datatable,String sheetName, int i){
		Fill_AdTitle_Txt(datatable.getCellData(sheetName, "Ad_Title", i));
		Select_Category_DD(datatable.getCellData(sheetName, "Category", i));
		Fill_Description_Txt(datatable.getCellData(sheetName, "Description", i));
		Select_Location_DD(datatable.getCellData(sheetName, "Location", i));
		Fill_Name_Txt(datatable.getCellData(sheetName, "Name", i));
		if(utils.isVisible(Location_LL))
			Fill_Price_Txt(datatable.getCellData(sheetName, "Price", i));
		Fill_Email_Address_Txt(datatable.getCellData(sheetName, "Email_Address", i));
		Fill_Phone_Number_Txt(datatable.getCellData(sheetName, "Phone_Number", i));
		Click_SubmitAd_Btn();
	}

}
