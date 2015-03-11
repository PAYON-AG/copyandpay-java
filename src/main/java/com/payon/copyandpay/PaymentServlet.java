package com.payon.copyandpay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.jayway.jsonpath.JsonPath;

public class PaymentServlet extends HttpServlet
{
	private static final long serialVersionUID = 7025771436876538138L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String checkoutId = JsonPath.read(prepareCheckout(), "$.id");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>COPYandPAY Java</title>");
		out.println("	<script src=\"https://test.oppwa.com/v1/paymentWidgets.js?checkoutId=" + checkoutId +"\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<form action=\"http://192.168.2.109:8080/copyandpay-java/status\" class=\"paymentWidgets\">VISA MASTER</form>");
		out.println("</body>");
		out.println("</html>");
		
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

	private String prepareCheckout() throws IOException
	{
		URL url = new URL("https://test.oppwa.com/v1/checkouts");

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		String parameters = "authentication.userId=8a82941847c4d0780147cea1d1730dcc"
				+ "&authentication.password=n3yNMBGK" 
				+ "&authentication.entityId=8a82941849745dd3014979fc254205d3"
				+ "&paymentType=DB" 
				+ "&amount=50.99" 
				+ "&currency=EUR";

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();
        int responseCode = conn.getResponseCode();
        InputStream is;
        
        if (responseCode >= 400) is = conn.getErrorStream();
        else is = conn.getInputStream();
        
		return IOUtils.toString(is);
	}
}
