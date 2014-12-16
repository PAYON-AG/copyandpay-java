package com.payon.copyandpay;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import com.jayway.jsonpath.JsonPath;

public class StatusServlet extends HttpServlet {

	private static final long serialVersionUID = -7305728645317107693L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    String orderId = request.getParameter("orderId");
	  //TODO this needs proper validation 
	    URL url = new URL("https://test.oppwa.com/v1/orders/" + orderId);
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	  
	    String content = IOUtils.toString(conn.getInputStream());
	    try {
	        String status = JsonPath.read(content, "$.result.code");
	        if ("00".equals(status))
	        {
	            request.getRequestDispatcher("paymentSuccess.jsp").forward(request, response);
	            return;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    request.getRequestDispatcher("paymentError.jsp").forward(request, response);
	}
}
