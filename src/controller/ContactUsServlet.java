package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.EmailDAO.*;
/**
 * Servlet implementation class ContactUsServlet
 */
@WebServlet("/ContactUsServlet")
public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactUsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// email, title, message

		String email =(String) request.getParameter("email");
		String title =(String) request.getParameter("title");
		String message =(String) request.getParameter("message");
		
		System.out.println(email+"\t"+title+"\t"+message);
		
		String url = "", msg = "";
		int eflag = 1;
		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if(!(email.length()==0) && !email.matches(emailPattern)){
			url = "/contactUs.jsp";
			msg = msg + "\nPlease enter valid Email";
			request.setAttribute("msg", msg);
			eflag = 0;
		}
		
		if(eflag==1){ // Everything is fine!
			msg = "Thank you for Contacting Us. Our Customer support team will get back to you soon.";
			url = "/contactUs.jsp";
			request.setAttribute("msg", msg);
			sendMail(email.toString(), title.toString(), message.toString(),"contactus");
			
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}

}
