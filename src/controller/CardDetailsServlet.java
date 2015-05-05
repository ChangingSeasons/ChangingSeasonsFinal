package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static model.OrderDAO.*;
import static model.OrderProductsDAO.*;
import static model.ShoppingCartDAO.*;
import model.Order;
import model.OrderProducts;
import model.User;
import static model.EmailDAO.*;
import static model.AuthDAO.*;

/**
 * Servlet implementation class CardDetailsServlet
 */
@WebServlet("/CardDetailsServlet")
public class CardDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paypal = request.getParameter("hidden-paypal");
		String msg = "", url = "";

		if(paypal!=null && !paypal.equalsIgnoreCase("Not Entered")){
			int customerID = Integer.parseInt(request.getParameter("user"));
			String orderStatus = "Order Placed";
			HttpSession se = request.getSession();
			String shippingAddress= "";
			if (se.getAttribute("shippingAddress")!= null){
				shippingAddress = (String) se.getAttribute("shippingAddress");
			} else {
				User currentUser = (User) se.getAttribute("user");
				shippingAddress = currentUser.getAddress();
			}

			int orderID = addOrder(customerID, orderStatus, shippingAddress);

			insertIntoOrderProducts(customerID, orderID);
			initShoppingCart(customerID);

			url = "/final.jsp?orderID="+orderID;
			msg = "";

			request.setAttribute("msg", msg);

			User u = (User)se.getAttribute("user");
			emailCustomer(u, orderID);
			emailSeller(u, orderID);
		}

		else{

			msg = "PayPay ID Not Entered";
			url = "/cardDetails.jsp";
			request.setAttribute("msg", msg);
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = "", url = "";
		int cflag = 0, cvflag = 0;

		//PayPal not entered, proceed with Card Details

		String cardno = request.getParameter("cardno");
		String cvv = request.getParameter("cvv");
		String cardholder = request.getParameter("cardholder");

		if(cardno.length()==0){
			msg += "Please enter 16-Digit Card Number!";
			url = "/cardDetails.jsp";
			request.setAttribute("msg", msg);
		}
		else{
			cflag = 1;
			if(!cardno.matches("\\d{16}")){
				msg += "Invalid Card Number";
				url = "/cardDetails.jsp";
				request.setAttribute("msg", msg);
				cflag = 0;
			}
		}
		if(cvv.length()==0){
			msg += "Please enter 3-Digit CVV Number!";
			url = "/cardDetails.jsp";
			request.setAttribute("msg", msg);
		}
		else{
			cvflag = 1;
			if(!cvv.matches("\\d{3}")){
				msg += "Invalid CVV Number!";
				url = "/cardDetails.jsp";
				request.setAttribute("msg", msg);
			}
		}
		if(cardholder.length()==0){
			msg += "Please enter Card Holder's Name!";
			url = "/cardDetails.jsp";
			request.setAttribute("msg", msg);
		}


		if(cflag== 1 && cvflag == 1 && cardholder.length()!=0){
			// Everything is filled-in

			int customerID = Integer.parseInt(request.getParameter("user"));
			String orderStatus = "Order Placed";
			HttpSession se = request.getSession();
			String shippingAddress= "";
			if (se.getAttribute("shippingAddress")!= null){
				shippingAddress = (String) se.getAttribute("shippingAddress");
			} else {
				User currentUser = (User) se.getAttribute("user");
				shippingAddress = currentUser.getAddress();
			}

			int orderID = addOrder(customerID, orderStatus, shippingAddress);

			insertIntoOrderProducts(customerID, orderID);
			initShoppingCart(customerID);

			url = "/final.jsp?orderID="+orderID;
			msg = "";

			request.setAttribute("msg", msg);

			User u = (User)se.getAttribute("user");
			emailCustomer(u, orderID);
			emailSeller(u, orderID);
		}


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	

	}

	public static void emailCustomer(User u, int orderID){

		Order o = viewOrder(orderID);

		List<OrderProducts> op = viewOrderProducts(orderID);

		StringBuilder sb = new StringBuilder();
		sb.append("Hello "+u.getFirstname()+" "+u.getLastname()+",");
		sb.append("\nYou recently placed an Order with ChangingSeasons. Order Details:\n");
		sb.append("Order ID: "+o.getOrderID());
		sb.append("\nDate of Order: "+o.getDateOfOrder());
		sb.append("\nOrder Status: "+o.getOrderStatus());
		sb.append("\nShipping Address: "+u.getAddress());
		sb.append("\nTotal Price (Includes 8% Tax): "+o.getTotal_price()+"\n\nOrder Includes:\n");

		for(OrderProducts temp : op)
			sb.append("Name: "+temp.getName()+"\tColor: "+
					temp.getColor()+"\tSize: "+temp.getSize()+"\tQuantity: "+temp.getQuantity()+"\n");
		
		sb.append("\n\nThank you for shopping with Us. We Hope to See you again soon!\nChangingSeasons.com");

		sendMail(u.getEmail(), "Order Details", sb.toString(), "orderDetailsToCustomer");
	}
	
	public static void emailSeller(User customer, int orderID){
		int ID = sellerDetails(orderID);
		
		User u = getUserbyId(ID);
		
		Order o = viewOrder(orderID);

		List<OrderProducts> op = viewOrderProducts(orderID);
		StringBuilder sb = new StringBuilder();
		
		sb.append("Hello "+u.getFirstname()+" "+u.getLastname()+",");
		sb.append("\nCongratulations, One of our Customers bought some of your products. Order Details:\n");
		sb.append("Order ID: "+o.getOrderID());
		sb.append("\nDate of Order: "+o.getDateOfOrder());
		sb.append("\nOrder Status: "+o.getOrderStatus());
		sb.append("\nShipping Address: "+customer.getAddress());
		sb.append("\nTotal Price (Includes 8% Tax): "+o.getTotal_price()+"\n\nOrder Includes:\n");
		
		for(OrderProducts temp : op)
			sb.append("Name: "+temp.getName()+"\tColor: "+
					temp.getColor()+"\tSize: "+temp.getSize()+"\tQuantity: "+temp.getQuantity()+"\n");
		
		sb.append("\n\nWe Hope to see more Customer purchasing your great Products! You are our valued Seller\n");
		sb.append("\nThank you From ChanginSeasons.com");
		
		sendMail(u.getEmail(), "Order Details", sb.toString(), "orderDetailsToSeller");
		
	}

}
