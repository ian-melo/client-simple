package net.ism.crud.clientsimple.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import net.ism.crud.clientsimple.util.constants.HttpConstants;

public class HttpJsonUtil {
	
	public static <X extends Object> X doJsonGet(String url, Class<X> clazz) {
		Gson gson = new Gson();
		try {
			String response = HttpJsonUtil.doGet(url);
			X obj = gson.fromJson(response, clazz);
			return obj;
		} catch(Exception ex) {
			return null;
		}
	}

	public static String doGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty(HttpConstants.HEADER_USER_AGENT, HttpConstants.USER_AGENT_FIREFOX);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}