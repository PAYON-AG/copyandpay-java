package com.payon.copyandpay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class PaymentKLARNA_INVOICE {
	public static void main(String[] args) throws IOException {
		System.out.println(new PaymentKLARNA_INVOICE().prepareCheckout());
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
				+ "&paymentType=PA"
				+ "&amount=10.00"
				+ "&currency=SEK"
				+ "&billing.country=SE"
				+ "&customer.givenName=Joe"
				+ "&customer.surname=Doe"
				+ "&cart.items[0].merchantItemId=1"
				+ "&cart.items[0].discount=0.00"
				+ "&cart.items[0].quantity=5"
				+ "&cart.items[0].name=Product 1"
				+ "&cart.items[0].price=1.00"
				+ "&cart.items[0].tax=6.00"
				+ "&customParameters[KLARNA_CART_ITEM1_FLAGS]=32"
				+ "&cart.items[1].merchantItemId=2"
				+ "&cart.items[1].discount=0.00"
				+ "&cart.items[1].quantity=1"
				+ "&cart.items[1].name=Product 2"
				+ "&cart.items[1].price=1.00"
				+ "&cart.items[1].tax=6.00"
				+ "&customParameters[KLARNA_CART_ITEM2_FLAGS]=32";

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
