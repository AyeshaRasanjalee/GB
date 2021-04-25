package com;
import model.payment;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/pay") 
public class PaymentService {
	payment paymenttObj = new payment(); 
	
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readpayments() 

	 { 
	    return  paymenttObj.readpayments(); 
	 }
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String productPrice(@FormParam("userid") String userid, 
			@FormParam("pMethod") String pMethod, 
			@FormParam("totalPrice") String totalPrice, 
			@FormParam("country") String country,
			@FormParam("city") String city,
			@FormParam("addres") String addres)
	{ 
		String output = paymenttObj.insertpayments(userid, pMethod,totalPrice, country,city,addres);
		
		  return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayments(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
		JsonObject jObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 	String pid = jObject.get("pid").getAsString(); 
	 	String userid = jObject.get("userid").getAsString(); 
	 	String pMethod = jObject.get("pMethod").getAsString(); 
	 	String totalPrice = jObject.get("totalPrice").getAsString(); 
	 	String country = jObject.get("country").getAsString(); 
	 	String city = jObject.get("city").getAsString(); 
	 	String addres = jObject.get("itemDesc").getAsString(); 
	 	
	 	String output = paymenttObj.updatePayments(pid, userid, pMethod, totalPrice, country,city,addres); 
	 	
	       return output; 
	}

	

	 @DELETE
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_XML) 
	 @Produces(MediaType.TEXT_PLAIN) 
	public String deletepayments(String paymentData) 
	   { 
	      //Convert the input string to an XML document
	      Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
	 
	     //Read the value from the element
	      String pid = doc.select("pid").text(); 
	      String output = paymenttObj.deletepayments(pid); 
	 
	   return output; 
     }
}
