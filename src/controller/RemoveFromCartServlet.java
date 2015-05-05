package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ShoppingCart;
import model.ShoppingCartDAO;
import static model.CartProductsDAO.removeProductfromCart;
import static model.ShoppingCartDAO.getCartID;
import static model.ShoppingCartDAO.updateTotalPrice;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productToBeRemoved = Integer.parseInt(request.getParameter("cartProductID"));
		//int userID = Integer.parseInt(request.getParameter("userID"));
		//int cartId = getCartID(userID);
		int ID = (int)request.getSession().getAttribute("ID");
		removeProductfromCart(productToBeRemoved);	
		updateTotalPrice(ID);
		String url = "/shoppingCart.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
