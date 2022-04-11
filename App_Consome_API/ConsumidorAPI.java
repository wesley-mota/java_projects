package br.com.app;


import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ConsumidorAPI {
	
	protected static String URLBase = "http://vhcfads4ci.sap.cofer.com:8000/zcofer_srv/BALANCA/manutencao?sap-client=100";
	
	protected static ConsumidorAPI instance;
	
	protected CloseableHttpClient clientHTTP;
	

	
	protected ConsumidorAPI() {
		this.clientHTTP = HttpClients.createDefault();
		
	}
	
	public static ConsumidorAPI getInstance() {
		if (instance == null) {
			instance = new ConsumidorAPI();
		}
		return instance;
	}
	
	
	public void doLogin() {

		
		
		try {
			
			HttpPost httpPost = new HttpPost(ConsumidorAPI.URLBase);
    		
		       JSONObject file = new JSONObject();
		       file.put("Id", "12");
		       file.put("Roll No.",(1704310046));
		       file.put("Tution Fees", (65400));
			
		    				
		    String json = null;
			StringEntity entity = new StringEntity(json);
		    httpPost.setEntity(entity);
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");

		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			@Override
			public String handleResponse(
					final HttpResponse response) throws ClientProtocolException, IOException{
				int status = response.getStatusLine().getStatusCode();
				if(status >= 200 && status <300){
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				}else {
						throw new ClientProtocolException("Unexpected response status:" + status);
				}
			}
		
		};
		String responseBody = this.clientHTTP.execute(httpPost, responseHandler);
		System.out.println("----------------------------------------------");
		System.out.println(responseBody);
		
		}catch (IOException ex) {
			Logger.getLogger(ConsumidorAPI.class.getName()).log(Level.SEVERE, null, ex);
			
		}
			
	}
	
	public String doRequest (String string) {
		String responseBody = null;
		try {
			HttpGet httpGet = new HttpGet(ConsumidorAPI.URLBase + string);
			
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(
						final HttpResponse response) throws ClientProtocolException, IOException{
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status <300){
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					}else {
						throw new ClientProtocolException("Unexpected response status:" + status);
					}
				}
			};
		responseBody = this.clientHTTP.execute(httpGet, responseHandler);
		System.out.println("---------------------------------------------");
		}
		catch(IOException ex) {
		Logger.getLogger(ConsumidorAPI.class.getName()).log(Level.SEVERE, null, ex);
		}
		return responseBody;
	}
}