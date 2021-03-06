package com.payon.copyandpay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class PaymentCHINAUNIONPAY {
	public static void main(String[] args) throws IOException {
		System.out.println(new PaymentCHINAUNIONPAY().prepareCheckout());
	}
	private String prepareCheckout() throws IOException {
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
				+ "&currency=USD";

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
