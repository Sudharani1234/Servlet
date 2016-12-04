package com.cluster;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
	res.setContentType("text/html");
	PrintWriter pw=res.getWriter();
    String StrNme=req.getParameter("nme");
    String StrPwd=req.getParameter("pwd");
    String StrEmail=req.getParameter("email");
    String StrPhone=req.getParameter("phone");
    
    System.out.println("******Name is "+StrNme);
    System.out.println("******Pwd is "+StrPwd);
    System.out.println("******Email is "+StrEmail);
    System.out.println("******Phone is "+StrPhone);
    
    Connection con=null;
    PreparedStatement pst=null;
    
    
    
    try {
    	
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	System.out.println("+++++++++ driver is loaded");
    	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ravioracle","ravioracle");
    	System.out.println("Got database onnection"+con);
    	pst=con.prepareStatement("INSERT INTO CUSTEMERS(NAME,PASSWORD,EMAIL,PHONE) VALUES (?,?,?,?)");
    	
    	pst.setString(1, StrNme);
    	pst.setString(2, StrPwd);
    	pst.setString(3, StrEmail);
    	pst.setString(4, StrPhone);
    	
    	int i = pst.executeUpdate();
    	
    	if(i==1)
    	{
    		pw.println("<html>");
    		pw.println("<body bgcolor='orange'>");
    		pw.println("Hello Mr."+StrNme+"thankyou for becoming a member");
    		pw.println("</body>");
    		pw.println("</html>");
    	}
    	else
    	{
    		pw.println("<html>");
    		pw.println("<body bgcolor='red'>");
    		pw.println("Hello Mr."+StrNme+"Better luck next time");
    		pw.println("</body>");
    		pw.println("</html>");
    	}
    	
    
		
	}
    catch (ClassNotFoundException e) 
    {
		// TODO: handle exception
		System.out.println("Exception caught"+e);
		
    }
   
    
	catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    finally{
        try {
        	if(pst!=null)
    		{
    			pst.close();
    		}
    		if(con!=null)
    		{
    			con.close();
    		}
			
		} catch (SQLException e2) {
			// TODO: handle exception
		System.out.println("exception caught in finally block"+e2);
		}
    }
    }
    	}