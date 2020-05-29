package com.bank.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.bank.base.BaseTest;

public class TestUtil extends BaseTest {
	
	public static String screenshotName;
	
	 public static void takeScreenShot(String filePath, String methodName, WebDriver driver) {
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	 Date d = new Date();
			screenshotName = filePath+methodName+"_"+d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
         //The below method will save the screen shot in d drive with test method name 
            try {
				FileUtils.copyFile(scrFile, new File(screenshotName));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	 
	 public static String randomestring()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(8);
			return(generatedstring);
		}
	 
	 public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}
		
	 
	 
		public static String randomeNum() {
			String generatedString2 = RandomStringUtils.randomNumeric(4);
			return (generatedString2);
		}
		@DataProvider(name="AddAccountData")
		String [][] getAddAccountData() throws IOException
		{
			String path=System.getProperty("user.dir")+"/src/test/java/com/bank/testData/LoginData.xlsx";
			
			int rownum=ExcelReader.getRowCount(path, "Sheet2");
			System.out.println("Total no of Rows:"+rownum);
			log.info("Total no of Rows:"+rownum);
			int colcount=ExcelReader.getCellCount(path,"Sheet1",1);
			System.out.println("Total no of Columns:"+colcount);
			log.info("Total no of Columns:"+colcount);
			String logindata[][]=new String[rownum][colcount];
			
			for(int i=1;i<=rownum;i++)
			{
				for(int j=0;j<colcount;j++)
				{
					logindata[i-1][j]=ExcelReader.getCellData(path,"Sheet1", i,j);//1 0
				}
					
			}
		return logindata;
		}
}
