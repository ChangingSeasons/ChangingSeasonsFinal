package controller;

import static model.AuthDAO.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		HttpSession se = request.getSession();
		int ID = (int)se.getAttribute("ID");
		
		User u = getUserbyId(ID);
		
		if(u.getType().equals("sel")){
			
			String firstname = request.getParameter("firstname");
			String company = (String)request.getParameter("company");
			String middlename = (String)request.getParameter("middlename");
			String lastname = (String)request.getParameter("lastname");
			String address = (String)request.getParameter("address");
			double phone = Double.parseDouble((String)request.getParameter("phone"));
			String accoutno = (String)request.getParameter("accoutno");
			String routingno = (String)request.getParameter("routingno");
			String paypal = (String)request.getParameter("paypal");
			String email = (String)request.getParameter("email");
			updateSeller(u.getID(), phone, company, address, email, accoutno, routingno, firstname, middlename, lastname, paypal);
		}
		else if(u.getType().equals("buy")){
			
			String firstname = (String)request.getParameter("firstname");
			String middlename = (String)request.getParameter("middlename");
			String lastname = (String)request.getParameter("lastname");
			double phone = Double.parseDouble((String)request.getParameter("phone"));
			String paypal = (String)request.getParameter("paypal");
			String email = (String)request.getParameter("email");
			String address = (String)request.getParameter("address");
			updateBuyer(u.getID(), firstname, lastname, address, email, phone, paypal, middlename);
		}
		u = new User();
		se.setAttribute("user", getUserbyId(ID));
		request.setAttribute("msg", "Profile Updated!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/base_index.jsp");
		dispatcher.forward(request, response);
	}

}
