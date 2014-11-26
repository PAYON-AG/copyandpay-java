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

public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 7025771436876538138L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tokenResponse = generateToken();
		String token = JsonPath.read(tokenResponse, "$.transaction.token");
		request.setAttribute("token", token);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("payment.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	private String generateToken() {
		try {
			URL url = new URL("https://test.ctpe.net/frontend/GenerateToken");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			String parameters = "SECURITY.SENDER=ff80808142b2c03c0142b7a7339603e0"
					+ "&TRANSACTION.CHANNEL=ff80808142b2c03c0142b7a7339803e5"
					+ "&TRANSACTION.MODE=INTEGRATOR_TEST"
					+ "&USER.LOGIN=ff80808142b2c03c0142b7a7339803e4"
					+ "&USER.PWD=demo"
					+ "&PAYMENT.TYPE=DB"
					+ "&PRESENTATION.AMOUNT=0.99"
					+ "&PRESENTATION.CURRENCY=EUR";

			IOUtils.write(parameters, conn.getOutputStream());

			conn.connect();

			String content = IOUtils.toString(conn.getInputStream());
			System.out.println("got content" + content);
			return content;

		} catch (IOException e) {
			System.out.println("error during token creation: "
					+ e.getLocalizedMessage());
			return "";
		}
	}
}
