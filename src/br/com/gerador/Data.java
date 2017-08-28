package br.com.gerador;

import java.text.DateFormat;
import java.util.TimeZone;

public class Data {
	public String getDateFromTimeapi_org(String fuso) {
		DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(TimeZone.getTimeZone("GMT" + fuso));
		String nova = df.format(new java.util.Date()).toString().replace("GMT", "");
		return nova;
	}
}