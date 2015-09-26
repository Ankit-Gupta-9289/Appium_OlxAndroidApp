package com.olx.techathon.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils implements Constants{
	public AndroidDriver driver;

    public void startAppiumServer() throws IOException, InterruptedException {   

          Runtime.getRuntime().exec("cmd.exe");
          CommandLine command = new CommandLine("cmd"); 
          command.addArgument("/c");  
          command.addArgument("C:/AppiumForWindows-1.3.4.1/Appium/node.exe");  
          command.addArgument("C:/AppiumForWindows-1.3.4.1/Appium/node_modules/appium/bin/appium.js");  
          command.addArgument("--address", false);  
          command.addArgument("127.0.0.1");  
          command.addArgument("--port", false);  
          command.addArgument("4723");  
          command.addArgument("--full-reset", false);  

          DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
          DefaultExecutor executor = new DefaultExecutor();  
          executor.setExitValue(1);  
          executor.execute(command, resultHandler);  

          Thread.sleep(5000);  
          System.out.println("----->>> Appium server started");  
    }

    public AndroidDriver StartDriverAndroidApp(String appLocation, String appPackage) throws MalformedURLException{


          try {
                startAppiumServer();
          } catch (IOException | InterruptedException e) {
                e.printStackTrace();
          }

          try{Thread.sleep(5000);}catch(Exception e){}



          DesiredCapabilities capabilities = new DesiredCapabilities();     

          capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
          //capabilities.setCapability(MobileCapabilityType.PLATFORM, "Windows");
          capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.3");
          capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
          capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
          capabilities.setCapability(MobileCapabilityType.APP, appLocation);
          capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, appPackage);
          //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, appActivity);
    
          driver = new AndroidDriver(new URL("http://127.0.0.1:4731/wd/hub"), capabilities);

          System.out.println("========driver after launching app========" + driver );
          driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
          System.out.println("==========complete launchApp========");

          return driver;
    }     


    /***********************************************************************************************
    * Function Description : Kill the appium server via commandline
    * @author rashi.atry, date: 19-May-2014
    * @throws IOException, InterruptedException
    * *********************************************************************************************/

    public  void stopAppiumServer() throws IOException {  

          Runtime.getRuntime().exec("cmd.exe");
          String AppiumServerPortNumber = "4723";
          String command = "cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in"
                      + " (`netstat -nao ^| findstr /R /C:\"" + AppiumServerPortNumber + "\"`) do (FOR /F \"usebackq\" %b in"
                      + " (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)";

          String s = null;
          try {
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                // read the output from the command
                while ((s = stdInput.readLine()) != null) {
                      System.out.println(s);
                }

                // read any errors from the attempted command
                while ((s = stdError.readLine()) != null) {
                      System.out.println(s);
                }
          } catch (IOException e) {
                System.out.println(" ---------->>> Exception happened: ");
                e.printStackTrace();
          }

          System.out.println("----->>> Appium server stopped");
    }



    /***********************************************************************************************
    * Function Description : Stops the driver
    * @author rashi.atry, date: 25-May-2015
    * *********************************************************************************************/

    public String StopDriver(){

          driver.quit();

          try {
                stopAppiumServer();
          } catch (IOException e) {
                e.printStackTrace();
          }

          return("----->>> Browser closed");
    }
    
    public void Click_Element(By Element){
    	driver.findElement(Element).click();
    }
    
    public void WaitUntil(By Element){
    	WebDriverWait wait = new WebDriverWait(driver, 25);
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(Element)));
    }
    
    public void Fill_Element(By Element, String Value){
    	driver.findElement(Element).sendKeys(Value);
    }
    
    public boolean isVisible(By Element){
    	if (driver.findElements(Element).size()>0 && driver.findElement(Element).isDisplayed())
    		return true;
    	else 
    		return false;
    }
    
    public void HideKeyboard(){
    	driver.hideKeyboard();
    }

}
