package com.chuck.norris.joke.handler;


import com.chuck.norris.joke.model.Joke;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;

@Component
@Slf4j
public class JokeFeedHandler {


    public Joke handle() {
        ResponseEntity<Joke> responseEntity;
        try {
            //Use ssl certificate for request
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
            responseEntity = restTemplate.getForEntity("https://api.chucknorris.io/jokes/random", Joke.class);
        } catch (RestClientException ex) {
            log.error("Exception while executing REST call to chuck norris jokes api", ex);
            throw ex;
        }
        return responseEntity.getBody();
    }
}
