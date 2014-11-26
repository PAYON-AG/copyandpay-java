package com.payon.copyandpay;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

public class StatusServlet extends HttpServlet {

	private static final long serialVersionUID = -7305728645317107693L;
	private static final Logger log = LoggerFactory.getLogger("StatusServlet");

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");

		URL url = new URL(
				"https://test.ctpe.net/frontend/GetStatus;jsessionid=" + token);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		String content = IOUtils.toString(conn.getInputStream());
		try {
			String status = JsonPath.read(content,
					"$.transaction.processing.result");
			if ("ACK".equals(status)) {
				request.getRequestDispatcher("paymentSuccess.jsp").forward(
						request, response);
				return;
			}
		} catch (Exception ex) {
			log.error("error while reading JSON: " + ex.getLocalizedMessage());
		}
		request.getRequestDispatcher("paymentError.jsp").forward(request,
				response);
	}
}
