package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    @Autowired
    RestTemplate restTemplate;
    
    private static Logger logger = LoggerFactory.getLogger(RestService.class);
    
    public static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjQ1ODM3MTQsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJndWlsbGVzYWx2YXRvcmVAZ21haWwuY29tIn0.9v0zsPFw9LcZDihhJvUBTastlEbhaaqNQ-WH76nDZTYLszzP6-WgqSIhb_iw0dyCH_Rx_ehBkQamlqMngqunaw";

    public <T> T Get(String uri, Class<T> responseType){
        return restTemplate.getForEntity( uri, responseType).getBody();
    }
    
    public <T> Integer getPriceUSD(String url) {
    	Integer response = 0;
    	try {
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    		headers.set("Authorization", "BEARER "+TOKEN);

    		JSONObject JsonObject = new JSONObject();
    	    JsonObject.put("d", "2021-09-27");
    	    JsonObject.put("v", "186.5");
    	    
    		HttpEntity<String> entity = new HttpEntity<String>(JsonObject.toString(), headers);
    		ResponseEntity<List> res = restTemplate.exchange(url, HttpMethod.GET,entity,List.class);
    		LinkedHashMap json = (LinkedHashMap) res.getBody().get(res.getBody().size() - 1);
    		response = (Integer) json.get("v");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return response;
	}
}
