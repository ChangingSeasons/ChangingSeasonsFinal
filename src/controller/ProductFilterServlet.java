package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Product;

/**
 * Servlet implementation class ProductFilterServlet
 */
@WebServlet("/ProductFilterServlet")
public class ProductFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductFilterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> totalProducts = (List<Product>) request.getSession().getAttribute("products");
		
		List<Integer> filteredIDs = new ArrayList<Integer>();
		
		for (Product p : totalProducts) {
			filteredIDs.add(p.getProductID());
		}
		
		Map<String, String[]>  choices = request.getParameterMap();

		List<Integer> showItems = new ArrayList<Integer>();
	
		//filtering by brand 
		for (String filter : choices.get("brand")[0].split("\\s*,\\s*")){
			//System.out.println("Brands for filtering: "+filter);
			showItems.addAll(filterBrand(totalProducts, filter));
		}
		
		//filtering by type
		for (String filter : choices.get("type")[0].split("\\s*,\\s*")){
			showItems.addAll(filterType(totalProducts, filter));
		}

		//filtering by price
		for (String filter : choices.get("price")[0].split("\\s*,\\s*")){
			//System.out.println("Prices for filtering: "+filter);
			showItems.addAll(filterPrice(totalProducts, filter));
		}
		filteredIDs.removeAll(showItems);
		List<List> result = new ArrayList<List>();
		
		result.add(showItems);
		result.add(filteredIDs);
		
		
		String json = new Gson().toJson(result);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	public List<Integer> filterBrand(List<Product> products, String brand) {
		List<Integer> output = new ArrayList<Integer>();
		for (Product p : products) {
			//System.out.println("Company name:"+p.getCompanyName() + " brand:"+brand);
			if (p.getCompanyName().equals(brand)) {
				
				output.add(p.getProductID()); 
			}
		}
		return output;
	}

	public List<Integer> filterType(List<Product> products, String type) {
		List<Integer> output = new ArrayList<Integer>();
		for (Product p : products) {
			if (p.getType().equals(type)) {
				output.add(p.getProductID()); 
			}
		}
		return output;
	}

	public List<Integer> filterPrice(List<Product> products,  String price) {
		List<Integer> output = new ArrayList<Integer>();
		float fPrice = price.equals("") ? 0f : Float.parseFloat(price);
		for (Product p : products) {
			if (p.getPrice() < fPrice) {
				output.add(p.getProductID()); 
			}
		}
		return output;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
