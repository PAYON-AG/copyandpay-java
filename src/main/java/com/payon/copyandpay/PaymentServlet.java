package com.payon.copyandpay;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
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
		String orderResponse = prepareOrder();
		System.out.println("got response: " + orderResponse);
		String token = JsonPath.read(orderResponse, "$.id");
		request.setAttribute("orderId", token);
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

	private String prepareOrder()
	{
		try
		{
			URL url = new URL("https://test.oppwa.com/v1/orders");

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

			IOUtils.write(parameters, conn.getOutputStream());

			conn.connect();

			String content = IOUtils.toString(conn.getInputStream());
			return content;
		} catch (Exception e)
		{
			e.printStackTrace();
			return "NO_ORDERID";
		} 
	}
}
