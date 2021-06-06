package amazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Amazonsearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
        
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.amazon.in/");
        
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");     //driver initialization for mysql
            
            //create connection to the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
            Statement stmt = con.createStatement();  
            
            //saved Amazon table data into result set
            
            ResultSet rs = stmt.executeQuery("select * from Amazon"); 
            
            while(rs.next()) 
            {
                
                System.out.println(rs.getString(2)+"  "+rs.getString(3)); 
                
                String category=rs.getString(2);
                String searchval=rs.getString(3);
             }
		
            
         //find the xpath for dropdown and saved in cname
        
         WebElement cname=driver.findElement(By.xpath("//*[@title='Search in']"));
         Select categorn=new Select(cname);    
         categorn.selectByVisibleText("Electronics");
         
         //find the xpath for search in and saved in searchname
         
        WebElement searchname=driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
         searchname.sendKeys("Mobiles");
      
         
         //find the xpath for submit and saved in submit
         
         WebElement submit=driver.findElement(By.xpath("//*[@id='nav-search-submit-button']"));
         submit.click();
         
         
         //list element to get the count for result set
         
	    List<WebElement> resultList = driver.findElements(By.xpath("//*[@data-component-type='s-search-result']"));
	    System.out.println("Total search count : " + resultList.size());
	    	
	    	 

        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
        
	}
}
	