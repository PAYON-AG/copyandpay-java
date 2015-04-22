package com.payon.copyandpay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class PaymentBRADESCO {
	public static void main(String[] args) throws IOException {
		System.out.println(new PaymentBRADESCO().prepareCheckout());
	}
	private String prepareCheckout() throws IOException {
		URL url = new URL("https://test.oppwa.com/v1/checkouts");

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		String parameters = "authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&paymentType=PA"
				+ "&amount=10.00"
				+ "&currency=BRL"
				+ "&customParameters[BRADESCO_cpfsacado]=11111111111"
				+ "&billing.country=BR"
				+ "&billing.postcode=12345678"
				+ "&billing.state=SP"
				+ "&billing.street1=Amazonstda"
				+ "&billing.city=Brasilia"
				+ "&customer.givenName=Braziliano"
				+ "&customer.surname=Babtiste"
				+ "&testMode=EXTERNAL";

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
