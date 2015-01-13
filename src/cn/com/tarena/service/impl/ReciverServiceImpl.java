package cn.com.tarena.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.tarena.pojo.UserInfo;
import cn.com.tarena.pojo.product;
import cn.com.tarena.pojo.receiver;
import cn.com.tarena.service.ReciverService;

public class ReciverServiceImpl implements ReciverService {

	@Override
	public List getStudentList(String id) {
		// TODO Auto-generated method stub
List reciverList = new ArrayList();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gwap","root","");
			
			stmt = conn.createStatement();
			
			stmt.execute("set names 'gbk'");
			
			rs = stmt.executeQuery("select *from contactinfo where userid =(select userid from orders where orderid='"+id+"')");
			
			while(rs.next()){
				receiver re = new receiver();
				
				re.setUserid(rs.getString("userid"));
				re.setStreet1(rs.getString("street1"));
				re.setZip(rs.getString("zip"));
				re.setHomephone(rs.getString("homephone"));
				re.setOfficephone(rs.getString("officephone"));
				re.setCellphone(rs.getString("cellphone"));
				re.setEmail(rs.getString("email"));


				
				reciverList.add(re);
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

		return reciverList;

	}

}
