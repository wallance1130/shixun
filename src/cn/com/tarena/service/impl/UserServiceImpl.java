package cn.com.tarena.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.tarena.pojo.UserInfo;
import cn.com.tarena.pojo.product;
import cn.com.tarena.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public List getStudentList(String uid) {
		// TODO Auto-generated method stub
		List userinfoList = new ArrayList();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gwap","root","");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select *from users,contactinfo where users.userid=contactinfo.userid and users.userid='"+ uid +"'");
			
			while(rs.next()){
				UserInfo userinfo = new UserInfo();
				
				userinfo.setUserid(rs.getString("userid"));
				userinfo.setUserpassword(rs.getString("password"));
				userinfo.setStreet1(rs.getString("street1"));
				userinfo.setStreet2(rs.getString("street2"));
				userinfo.setCountry(rs.getString("country"));
				userinfo.setProvince(rs.getString("province"));
				userinfo.setCity(rs.getString("city"));
				userinfo.setOfficephone(rs.getString("officephone"));
				userinfo.setHomephone(rs.getString("homephone"));
				userinfo.setCellphone(rs.getString("cellphone"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setZip(rs.getString("zip"));


				
				userinfoList.add(userinfo);
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

		return userinfoList;
	}

}
