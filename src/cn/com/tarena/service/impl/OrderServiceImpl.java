package cn.com.tarena.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.tarena.pojo.Order;

import cn.com.tarena.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public List getStudentList() {
		// TODO Auto-generated method stub
		List orderList = new ArrayList();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gwap","root","");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select orderid,cost,orderstatus.name,paystyle from orders,orderstatus,payway where orders.statusid=orderstatus.statusid and orders.paywayid=payway.paywayid and userid='admin'");
			
			while(rs.next()){
				Order order = new Order();
				
				order.setOrderid(rs.getString("orderid"));
				order.setOrdercost(rs.getString("cost"));
				order.setOrderstatus(rs.getString("name"));
				order.setOrderpayway(rs.getString("paystyle"));

				
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("error when querying order ",e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				
				throw new RuntimeException("error when querying order ",e);
			}
		}

		return orderList;
	}

}
