package cn.com.tarena.web.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.tarena.service.OrderService;
import cn.com.tarena.service.ReciverService;
import cn.com.tarena.service.impl.ServiceFactory;
import cn.com.tarena.web.CurrentServlet;

public class ControllerServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String path = req.getServletPath();		
		path = path.substring(0,path.indexOf("."));
		System.out.println(path);
if("/orderList".equals(path))
{
	
	try{
		OrderService orderService = ServiceFactory.getOrderList();

		List orderlist = orderService.getStudentList();
		
		req.setAttribute("orderlist", orderlist);
						
		getServletContext().getRequestDispatcher("/orderList").forward(req, resp);
		
	//	toOrderList(resp,orderlist);
		} catch (Exception e){
			//toError(resp,e.getMessage());
			req.setAttribute("message", e.getMessage());
			
		    getServletContext().getRequestDispatcher("/error").forward(req, resp);
		}
}

else if("/CurrentServlet".equals(path))
{
	try{
		String id = req.getParameter("name");
		System.out.println(id);
		ReciverService reciverService = ServiceFactory.getreciverList();

		List reciverlist = reciverService.getStudentList(id);
	
		req.setAttribute("reciverlist", reciverlist);
	
		getServletContext().getRequestDispatcher("/CurrentServlet").forward(req, resp);
	} catch (Exception e){
		//toError(resp,e.getMessage());
		//req.setAttribute("message", e.getMessage());
		
	    //getServletContext().getRequestDispatcher("/error").forward(req, resp);
	}

	
}
else
{
	//resp.sendError(resp.SC_NOT_FOUND);
}
	}

}
