package test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AmazonSearch {

	public static void main(String[] args)  {
		String category="";
		String searchterm="" ;
		System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in");
driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
try {
// TODO Auto-generated method stub
Class.forName("com.mysql.jdbc.Driver");

Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce1","root","root");
Statement stmt=con.createStatement();
//stmt.executeUpdate("insert into Amazon (category, searchterm) values ('Electronics', 'laptop'");
ResultSet rs=stmt.executeQuery("select * from Amazon");
while(rs.next()) {
	System.out.println(rs.getString(2)+" " +rs.getString(3));
	category = rs.getString(2);
	 searchterm = rs.getString(3);
	
		
}

WebElement dropdown=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
Select dd= new Select(dropdown);
dd.selectByVisibleText(category);
WebElement search=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
search.sendKeys(searchterm);
WebElement Enterbutton=driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
Enterbutton.click();
List<WebElement> devices=driver.findElements(By.xpath("//div[@class='_multi-card-creative-desktop_DesktopGridColumn_gridColumn__2Jfab']"));
System.out.println("total count of devices"+devices.size());
int j=devices.size();
for(int i=0;i<j;i++)
{
	
	System.out.println("Names of devices are "+devices.get(i).getText());
}


TakesScreenshot obj=(TakesScreenshot) driver;
File myfile=obj.getScreenshotAs(OutputType.FILE);
try {
    FileUtils.copyFile(myfile,  new File("test1.png"));
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}

}
catch(SQLException e){
	System.out.println("sql exception");
	
}
catch(ClassNotFoundException e){
	System.out.println("classnot found exception");
	
}
	}
}
