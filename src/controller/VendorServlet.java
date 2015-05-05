package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.AuthDAO.*;
import static model.EmailDAO.sendMail;
/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/VendorServlet")
public class VendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String check = request.getParameter("check"); //Check button for username
		String username = request.getParameter("username");
		String company = request.getParameter("company");
		String firstname = request.getParameter("firstname");
		String middlename = request.getParameter("middlename");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String accoutno = request.getParameter("accoutno");
		String routingno = request.getParameter("routingno");
		String paypal = request.getParameter("paypal");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordc = request.getParameter("passwordc");
		String url = "", msg = "";
		int userId=0;
		boolean available = false;
		boolean status = false;
		int flag=0, eflag=1;
		
		
		final String type = "sel";
		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		if(username.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "Please fill-in Username";
			request.setAttribute("msg", msg);
			flag=0;
		}
		else{
			if( request.getParameter("check") != null){
				available = isUsernameAvailable(username);
				if(available){
					url = "/seller_signup.jsp";
					msg = msg + "\nUsername Available!";
					request.setAttribute("msg", msg);
					flag=1;
				}
				else{
					url = "/seller_signup.jsp";
					msg = msg + "\nUsername NOT available!";
					request.setAttribute("msg", msg);
					flag=0;
				}
			}
		}

		if(company.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Company name";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		if(firstname.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Firstname";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		
		if(lastname.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Lastname";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		if(phone.length()!=0 && !phone.matches("\\d{10}")){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Phone Number (10-Digits)";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		if(email.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Email";
			request.setAttribute("msg", msg);
			flag=0;
		}
		else{
			eflag = 1;
			if(!email.matches(emailPattern)){
				url = "/seller_signup.jsp";
				msg = msg + "\nPlease fill-in Valid Email";
				request.setAttribute("msg", msg);
				eflag=0;
			}
		}
		
		if(password.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Password";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		if(passwordc.length()==0){
			url = "/seller_signup.jsp";
			msg = msg + "\nPlease fill-in Password again";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		if(! password.equals(passwordc)){
			url = "/seller_signup.jsp";
			msg = msg + "\nPassword does not match!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		
		if(username.length()!=0 && firstname.length()!=0 && lastname.length()!=0 && company.length()!=0
				&& eflag==1 && password.length()!=0 && passwordc.length()!=0 && password.equals(passwordc))
			flag = 1;
			
		if(flag == 1){ //Everything is filled in
			
			userId = enterNewuser(username, passwordc, type);
			
			if(userId > 0){
				
				// middle name, address, bankaccount, phone, routing, paypal
				
				if(middlename.length()==0)
					middlename = "";
				if(address.length()==0)
					address = "";
				if(accoutno.length()==0)
					accoutno = "";
				if(routingno.length()==0)
					routingno = "";
				if(paypal.length()==0)
					paypal = "";
				
				if(phone.length()==0){
					phone = "";
					status = enterUsernameSeller(userId, 0, false, company, address, email, url, accoutno, routingno, firstname, middlename, lastname, paypal);
				}
				else{
					double phno = Double.parseDouble(phone);
					status = enterUsernameSeller(userId, phno, false, company, address, email, url, accoutno, routingno, firstname, middlename, lastname, paypal);
				}
				
				if(status == true){
					sendMail(email, "Welcome to Changing Seasons", "Thank you for registering with Us. We wish you a pleasant experience!","register");
					msg = "Account Created Successfully";
					request.setAttribute("msg", msg);
					url = "/base_login.jsp";
				}
				else{
					msg = "UserName Insert Failed";
					request.setAttribute("msg", msg);
					url = "/seller_signup.jsp";
				}
			}
			
			else{
				msg = "Create Account Failed, Please Try Again!";
				request.setAttribute("msg", msg);
				url = "/seller_signup.jsp";
			}	
		}
		
		//DB_close();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}

}
