package com.payon.copyandpay;

import java.io.IOException;
import java.io.PrintWriter;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>COPYandPAY Java</title>");
		out.println("</head>");
		out.println("<body>");
		
	    String checkoutId = request.getParameter("id");
	    String responseBody = getPaymentStatus(checkoutId);
        String status = JsonPath.read(responseBody, "$.result.code");
        if (status.startsWith("000")) {
            out.println("SUCCESS <br/><br/> Here is the result of your transaction: <br/><br/>");
            out.println(responseBody);
        }
        else {
        	out.println("ERROR <br/><br/> Here is the result of your transaction: <br/><br/>");
            out.println(responseBody);
        }
		out.println("</body>");
		out.println("</html>");
		
		out.flush();
	}
	
	private String getPaymentStatus(String checkoutId) throws IOException {
		URL url = new URL("https://test.oppwa.com/v1/checkouts/" + checkoutId + "/payment");
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

	    return IOUtils.toString(conn.getInputStream());
	}
}
