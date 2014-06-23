package com.swf.seed.web.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	private static final Log logger = LogFactory.getLog(HttpClientUtil.class);
	/**
	 * 发送字节数组给指定url
	 * @param url 指定url路径
	 * @param byte_msg  内容字节数组
	 * @return 返回结果
	 */
	public static String send(String url,byte[] byte_msg) {
		String returnContent = null;
		HttpClient httpclient = wrapClient(new DefaultHttpClient()); 
		HttpPost httppost = new  HttpPost(url); 
		ByteArrayEntity entity = new ByteArrayEntity(byte_msg);
		httppost.setEntity(entity);
		HttpResponse response;
		try {
			response = httpclient.execute(httppost);
		HttpEntity returnEntity = response.getEntity();
		if (entity != null) {  
			int statusCode = response.getStatusLine().getStatusCode();
			logger.debug(response.getStatusLine().toString());
			if (statusCode==200){
				returnContent = EntityUtils.toString(returnEntity, "GBK");
				logger.info("返回的内容为  ： " + returnContent);
			}
		}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return returnContent;
	}
	
	
	/**
     * 避免HttpClient的”SSLPeerUnverifiedException: peer not authenticated”异常
     * 不用导入SSL证书
     *
     */
	public static HttpClient wrapClient(org.apache.http.client.HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");

			X509TrustManager tm = new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(
						X509Certificate[] ax509certificate, String s)
						throws CertificateException {
				}

				@Override
				public void checkClientTrusted(
						X509Certificate[] ax509certificate, String s)
						throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			PlainSocketFactory sf = PlainSocketFactory.getSocketFactory();
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", 7001, sf));
			registry.register(new Scheme("https", 7002, ssf));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(
					registry);
			return new DefaultHttpClient(mgr, base.getParams());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return base;
	}
}
