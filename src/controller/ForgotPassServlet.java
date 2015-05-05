package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.AuthDAO.*;

/**
 * Servlet implementation class ForgotPassServlet
 */
@WebServlet("/ForgotPassServlet")
public class ForgotPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassServlet() {
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
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phno = request.getParameter("phone");
		String msg = "", url = "";
		int flag = 1, eflag=1;
		
		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(username.length()==0){
			url = "/forgot_password.jsp";
			msg = msg + "Please fill-in Username";
			request.setAttribute("msg", msg);
		}
		if(email.length()==0){
			url = "/forgot_password.jsp";
			msg = msg + "\nPlease fill-in Email";
			request.setAttribute("msg", msg);
		}
		if(phno.length()==0){
			url = "/forgot_password.jsp";
			msg = msg + "\nPlease fill-in Phone Number";
			request.setAttribute("msg", msg);
		}
		
		if(!phno.matches("\\d{10}")){
			url = "/forgot_password.jsp";
			msg = msg + "\nOnly numbers allowed (10-digits)";
			request.setAttribute("msg", msg);
			flag = 0;
		}
		
		if(!(email.length()==0) && !email.matches(emailPattern)){
			url = "/forgot_password.jsp";
			msg = msg + "\nPlease enter valid Email";
			request.setAttribute("msg", msg);
			eflag = 0;
		}
		
		if(username.length()!=0 && email.length()!=0 && phno.length()!=0 && flag==1 && eflag==1){ // Everything filled in
			
			String password = getPassword(email, Double.parseDouble(phno));
			if( password != null){
				msg = "User Authenticated. Password is:";
				url = "/resetPass.jsp";
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("msg", msg);
			}
			else{
				msg = "Not Found, please re-enter details.";
				url = "/forgot_password.jsp";
				request.setAttribute("msg", msg);
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
		
	}

}
