package com.payon.copyandpay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class PaymentPAYOLUTION_INVOICE {
	public static void main(String[] args) throws IOException {
		System.out.println(new PaymentPAYOLUTION_INVOICE().prepareCheckout());
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
				+ "&currency=EUR"
				+ "&customer.surname=Jones"
				+ "&customer.givenName=Jane"
				+ "&customer.birthDate=1970-01-01"
				+ "&billing.city=Test"
				+ "&billing.country=DE"
				+ "&billing.street1=123 Test Street"
				+ "&billing.postcode=TE1 2ST"
				+ "&customer.email=test@test.com"
				+ "&customer.phone=1234567890"
				+ "&customer.ip=123.123.123.123"
				+ "&customParameters[PAYOLUTION_ITEM_PRICE_1]=2.00"
				+ "&customParameters[PAYOLUTION_ITEM_DESCR_1]=Test item #1"
				+ "&customParameters[PAYOLUTION_ITEM_PRICE_1]=3.00"
				+ "&customParameters[PAYOLUTION_ITEM_DESCR_1]=Test item #2"
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
