package cn.com.tarena.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.tarena.pojo.product;
import cn.com.tarena.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List getStudentList() {
		// TODO Auto-generated method stub
List productList = new ArrayList();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gwap","root","");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select *from product");
			
			while(rs.next()){
				product product = new product();
				
				product.setProductid(rs.getString("productid"));
				product.setProductname(rs.getString("name"));
				product.setProductprice(rs.getString("basePrice"));


				
				productList.add(product);
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

		return productList;
	}

}
