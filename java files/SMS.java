package com.etm;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
public class SMS {

	    // Find your Account Sid and Token at twilio.com/console 
	    public static final String ACCOUNT_SID = "########"; 
	    public static final String AUTH_TOKEN = "auth-token"; 
	 
	    public static void main(String[] args) { 
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("xxxxxxxxx"), new com.twilio.type.PhoneNumber("xxxxxx"),
	                "New Task has been created")      
	            .create(); 
	
	        System.out.println(message.getSid()); 
	    
	}
}
