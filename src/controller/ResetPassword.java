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
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
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
		String password = request.getParameter("password");
		String cPassword = request.getParameter("cpassword");
		String username = request.getParameter("hidden-username");
		
		String msg = "", url = "";
		
		if(password.length()==0){
			url = "/resetPass.jsp";
			msg = msg + "\nPlease fill-in Password";
			request.setAttribute("msg", msg);
		}
		
		if(cPassword.length()==0){
			url = "/resetPass.jsp";
			msg = msg + "\nPlease fill-in Password again";
			request.setAttribute("msg", msg);	
		}
		
		if(! password.equals(cPassword)){
			url = "/resetPass.jsp";
			msg = msg + "\nPasswords don't match!";
			request.setAttribute("msg", msg);
		}
		
		if(password.length()!=0 && cPassword.length()!=0 && password.equals(cPassword)){ // Everything is filled in

			boolean status = resetPassword(username, cPassword);
			
			if(status == true){
				msg = "Password has been sucessfully Reset";
				request.setAttribute("msg", msg);
				url = "/base_login.jsp";
			}
			else{
				msg = "Password Reset Failed! Please try again.";
				request.setAttribute("msg", msg);
				url = "/resetPass.jsp";
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}

}
