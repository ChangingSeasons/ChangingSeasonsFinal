package model;

import static model.EmailDAO.sendMail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailDAO {

	public static void sendMail(String to, String subject, String message, String type){

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");

		final String username = "cseasons04@gmail.com"; 
		final String password = "Software2015";
		try{
			
			Session session = Session.getDefaultInstance(props, new Authenticator(){
				
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(username, password);
				}
			});
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			
			if(type.equalsIgnoreCase("register")){
				msg.setSubject(subject); // Welcome to Changing Seasons
				msg.setText(message); // Thank you for registering with Us. We wish you a pleasant shopping experience!
			}
			else if(type.equalsIgnoreCase("authorization")){
				msg.setSubject(subject); //Authorization Status
				msg.setText(message); //Thank you for Registering with Us. You have been Authorized by the Admin. We wish you a pleasant selling experience!
			}
			else if(type.equalsIgnoreCase("orderDetailsToCustomer")){
				msg.setSubject(subject); // Order Details
				msg.setText(message);
			}
			else if(type.equalsIgnoreCase("contactus")){
				msg.setSubject(subject); //Authorization Status
				msg.setText(message); //Thank you for Contacting Us. Our Customer support team will get back to you soon.
			}
			else if(type.equalsIgnoreCase("orderDetailsToSeller")){
				msg.setSubject(subject); // Order Details
				msg.setText(message);
			}
			else if(type.equalsIgnoreCase("contactSeller")){
				msg.setSubject(subject); // Contact Seller
				msg.setText(message);
			}

			msg.setSentDate(new Date());
			Transport.send(msg);

			
			// Store the mail in Company's inbox as well

			msg = new MimeMessage(session); // Create a new message
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username,false));
			msg.setSubject(subject + " From: " + to);
			msg.setText(to+" sent:\n"+message);
			msg.setSentDate(new Date());
			Transport.send(msg);
			
		}catch (MessagingException e){ 
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
