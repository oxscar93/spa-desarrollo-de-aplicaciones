package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.BCRAdto;

@Service
public class RestService {
    @Autowired
    RestTemplate restTemplate;
    
    public static final String TOKEN = "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjQzNTI4OTIsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJndWlsbGVzYWx2YXRvcmVAZ21haWwuY29tIn0.Ix6hc9r1tZ69sToGhTG30YS90yomFCzbxHmgPjDNSDPyfArTiaSj2dmUnXFoJtcGdbN4e20VbeyqTIo4CNsyJg";

    public <T> T Get(String uri, Class<T> responseType){
        return restTemplate.getForEntity( uri, responseType).getBody();
    }
    
    public <T> String getPriceUSD(String url) {
    	String result = "";
    	try {
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		headers.set("Authorization", "Bearer "+TOKEN);
    		Gson gson = new Gson();
    		String gsonString = gson.toJson(new BCRAdto());
//    		String requestJson = "{\"d\": \"2021-09-27\",\r\n" + "        \"v\": 186.5}";
    															//requestJson
    		HttpEntity<String> entity = new HttpEntity<String>(gsonString,headers);
    		result = restTemplate.postForObject(url, entity, String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
}
