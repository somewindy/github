package com.swf.seed.web.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import com.swf.seed.base.SystemContants;

public class HttpClientUtils {
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	public static void get(String url) throws IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			logger.debug("statusLine:"+response.getStatusLine());
			HttpEntity entity = response.getEntity();
			logger.info("return content:"+SystemContants.line_separator+EntityUtils.toString(entity).trim());
			EntityUtils.consume(entity);
		}finally{
			closeQuietly(response);
		}
	}
	public static void post(String url,Map<String,Object> map) throws IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("j_username", "console"));
		nvps.add(new BasicNameValuePair("j_password", "console"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    logger.info("return content:"+SystemContants.line_separator+EntityUtils.toString(entity).trim());
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
			closeQuietly(response);
		}
	}
	
	public static void closeQuietly(CloseableHttpResponse  response){
		if (response!=null){
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
public static List<NameValuePair>  transferMap(Map<String,String> map){
	String name;
	String value;
	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	for (Entry<String, String> entry: map.entrySet()) {
	    name = entry.getKey();
	    value = entry.getValue();
	    nvps.add(new BasicNameValuePair(name, value));
	}
	return nvps;
}
	
	public static void main(String[] args) {
		try {
			post("http://192.168.52.139:7013/console/j_security_check",new HashMap<String, Object>());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
