package controller;

import java.io.IOException;
import static model.EmailDAO.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactSellerServlet
 */
@WebServlet("/ContactSellerServlet")
public class ContactSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactSellerServlet() {
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
		String to = request.getParameter("email");
		String subject = request.getParameter("title");
		String message = request.getParameter("message");
		
		String msg = "", url = "";
		
		if(to.length()==0){
			url = "/contactSeller.jsp";
			msg = msg + "\nPlease fill-in Seller's Email";
			request.setAttribute("msg", msg);
		}
		
		if(subject.length()==0){
			url = "/contactSeller.jsp";
			msg = msg + "\nPlease fill-in Title";
			request.setAttribute("msg", msg);
		}
		if(message.length()==0){
			url = "/contactSeller.jsp";
			msg = msg + "\nPlease fill-in Email Content";
			request.setAttribute("msg", msg);
		}
		
		if(to.length()!=0 && subject.length()!=0 && message.length()!=0){
			//Everything is filled in!
			url = "/base_index.jsp";
			msg = "Email sent to Seller";
			request.setAttribute("msg", msg);
			sendMail(to, subject, message, "contactSeller");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}

}
