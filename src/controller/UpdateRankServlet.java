package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.RankDAO;

/**
 * Servlet implementation class UpdateRankServlet
 */
@WebServlet("/UpdateRankServlet")
public class UpdateRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRankServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rank = Integer.parseInt(request.getParameter("rank"));
		int oldRank = Integer.parseInt(request.getParameter("oldRank"));
		int productID = Integer.parseInt(request.getParameter("productID"));
		int customerID = Integer.parseInt(request.getParameter("customerID"));
		if(oldRank == -1) {
			//System.out.println("Rank has not been set, inserting oldRank: "+oldRank+" and newRank:"+rank);
			RankDAO.addRank(productID, customerID, rank);
		} else {
			//System.out.println("Rank has been set, updating oldRank: "+oldRank+" and newRank:"+rank);
			RankDAO.editRank(productID, customerID, rank);
		}
		
		String url = "/view_product.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
