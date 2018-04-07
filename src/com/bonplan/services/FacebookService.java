/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;
import com.bonplan.entities.Voyage;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 *
 * @author Radhouen
 */
public class FacebookService {
     public void partager(Voyage v){
        
          String domain="https://www.facebook.com/";
          //domain="https://google.fr/";
         String appId="156489881725620";
         String appSecret="f9c55173ba6315542eec0b118bf82f5a";
         String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
                 +"&scope=user_about_me,ads_management,publish_actions";
         
         System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
         WebDriver driver= new ChromeDriver();
         driver.get(authURL);
         String accessToken="" ;
         
         boolean ok=true;
         while(ok)
         {
             if ( (! driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl()!=authURL) )
             {
                 String url =driver.getCurrentUrl();
                 accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
                 System.out.println(accessToken);
                
                 ok=false;
              }
             
         }
          //driver.quit();
         System.out.println("act:"+accessToken);
         driver.quit();
         FacebookClient fbClient = new DefaultFacebookClient(accessToken);
              User me = fbClient.fetchObject("me", User.class);
              System.out.println(me.getName());
            
              FacebookType publishMessageResponse =
            fbClient.publish("me/feed", FacebookType.class,com.restfb.Parameter.with("message", "BonPlan"+""));
            System.out.println("Published message ID: " + publishMessageResponse.getId());
    
            
    }
}
