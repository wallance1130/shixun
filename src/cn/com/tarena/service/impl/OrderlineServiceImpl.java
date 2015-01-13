package cn.com.tarena.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.tarena.pojo.Orderlineproduct;
import cn.com.tarena.pojo.receiver;
import cn.com.tarena.service.OrderlineproductService;

public class OrderlineServiceImpl implements OrderlineproductService {

	@Override
	public List getStudentList(String id) {
		// TODO Auto-generated method stub
		List orderlineList = new ArrayList();
		
		System.out.println(id);
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gwap","root","");
			
			stmt = conn.createStatement();
			
			stmt.execute("set names 'gbk'");
			
			rs = stmt.executeQuery("select product.name,cost,amount,paystyle from orders,orderline,product,payway where orders.orderid=orderline.orderid and orderline.productid=product.productid and orders.paywayid=payway.paywayid and orders.orderid='"+id+"'");
			
			while(rs.next()){
				Orderlineproduct orderline = new Orderlineproduct();
				orderline.setProductname(rs.getString("product.name"));
				orderline.setProductprice(rs.getString("cost"));
				orderline.setCount(rs.getString("amount"));
				orderline.setPayway(rs.getString("paystyle"));
				


				
				orderlineList.add(orderline);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("error when querying product ",e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				
				throw new RuntimeException("error when querying product ",e);
			}
		}

		return orderlineList;
	}

}
