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

import model.Product;
import model.ProductDAO;
import static model.ProductDAO.*;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")


public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProduct() {
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
		int i = 0;
		
		String productName = request.getParameter("productName");
		String type = request.getParameter("type");
		String productDesc = request.getParameter("productDesc");
		String price = request.getParameter("price");

		String imagepath = "zzz";
		String shippingCost = request.getParameter("shippingCost");
		
		/*** 

		String sizearray[] = request.getParameterValues("size"); // User input
		String colorarray[] = request.getParameterValues("color"); // User input

		StringBuilder sb = new StringBuilder();

		String [] str = new String[6];
		for(i=0; i<str.length; i++)
			str[i] = "false";

		String size = "";
		if(sizearray.length!=0){
			for(i=0; i<sizearray.length; i++){
				if(sizearray[i].equals("XS"))
					str[0] = "true";
				if(sizearray[i].equals("S"))
					str[1] = "true";
				if(sizearray[i].equals("M"))
					str[2] = "true";
				if(sizearray[i].equals("L"))
					str[3] = "true";
				if(sizearray[i].equals("XL"))
					str[4] = "true";
				if(sizearray[i].equals("XXL"))
					str[5] = "true";
			}

			for(i=0; i<str.length-1; i++){
				sb.append(str[i]);
				sb.append(" ");
			}sb.append(str[i]);

			size = sb.toString(); // This goes to Database
		}
		else
			size = "false false false false false";

		sb = new StringBuilder();

		// Re Init for Colors
		for(i=0; i<str.length; i++)
			str[i] = "false";

		String color = "";
		if(colorarray.length!=0){
			for(i=0; i<colorarray.length; i++){
				if(colorarray[i].equals("Black"))
					str[0] = "true";
				if(colorarray[i].equals("White"))
					str[1] = "true";
				if(colorarray[i].equals("Red"))
					str[2] = "true";
				if(colorarray[i].equals("Brown"))
					str[3] = "true";
				if(colorarray[i].equals("Grey"))
					str[4] = "true";
				if(colorarray[i].equals("Blue"))
					str[5] = "true";
			}

			for(i=0; i<str.length-1; i++){
				sb.append(str[i]);
				sb.append(" ");
			}sb.append(str[i]);

			color = sb.toString(); // This goes to Database
		}
		else
			color = "false false false false false";
			
		***/

		String imageName = request.getParameter("imageName");

		String msg = "", url = "";
		int flag = 0;

		if(productName.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product-Name";
			request.setAttribute("msg", msg);
		}
		if(productDesc.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product-Description";
			request.setAttribute("msg", msg);
		}
		if(price.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product-Price";
			request.setAttribute("msg", msg);
		}
		if(type.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please Select Product-type";
			request.setAttribute("msg", msg);
		}

		if(shippingCost.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product Shipping-Cost";
			request.setAttribute("msg", msg);
		}
		/**
		if(color.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product Color";
			request.setAttribute("msg", msg);
		}
		if(size.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product Size";
			request.setAttribute("msg", msg);
		}
		**/
		if(imageName.length()==0){
			url = "/addProducts.jsp";
			msg = msg + "Please fill-in Product Image name";
			request.setAttribute("msg", msg);
		}
		
		
		if(productName.length()!=0 && productDesc.length()!=0 && price.length()!=0 && shippingCost.length()!=0 &&
				 imageName.length()!=0 && type.length()!=0)
			flag = 1;

		if(flag == 1){ // Everything is filled in
			HttpSession se = request.getSession();
			int sellerID = (int)se.getAttribute("ID");
			float prPrice = Float.parseFloat(price);
			float shipCost = Float.parseFloat(shippingCost);
			int id = insertProduct(productName, productDesc, sellerID, prPrice, imagepath, shipCost, imageName, type);
			
			
			
			if(id>0){
				Product product = ProductDAO.viewProduct(id);
				List<Product> currentProducts = (List<Product>) se.getAttribute("products");
				currentProducts.add(product);
				se.setAttribute("products",currentProducts);
				se.setAttribute("currentProductID", id);
				url = "/upload.jsp";
				msg = "Product Added Successfully";
				request.setAttribute("msg", msg);
			}
			else{
				url = "/base_index.jsp";
				msg = "Failed to Add Product";
				request.setAttribute("msg", msg);
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
