/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.serviceclient;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.keybank.statement.model.CardVerifyServiceRequest;
import com.keybank.statement.model.CardVerifyServiceResponse;

/**
 * @author jatin, 21-Oct-2022
 *         Description:
 */
@Component
public class CardVerifyServiceClientImpl implements ICardVerifyServiceClient {

    @Value("${cardserviceUri}")
    private String cardserviceUri;

    @Override
    public CardVerifyServiceResponse verifyCardDetails(CardVerifyServiceRequest request) {

        System.out.println("Enter into cardverifyService");

        // String cardserviceUri = "http://localhost:2025/v1/card/verify";


        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> reqHeaders = new LinkedMultiValueMap<>();

        reqHeaders.add("client-id", "online");
        reqHeaders.add("msg-ts", new Date().toString());
        reqHeaders.add("request-id", UUID.randomUUID().toString());

        HttpEntity requestEntity = new HttpEntity(request, reqHeaders);

        ResponseEntity<CardVerifyServiceResponse> responseEntity = restTemplate.exchange(cardserviceUri,
                HttpMethod.POST, requestEntity, CardVerifyServiceResponse.class);

        CardVerifyServiceResponse response = responseEntity.getBody();

        System.out.println("Exit from cardverifyService");

        return response;
    }

    // This code is developed for local test of client code (RestTempletTesting)

    
    /* public static void main(String[] args) {

        CardVerifyServiceClientImpl svcClientImpl = new CardVerifyServiceClientImpl();

        CardVerifyServiceRequest request = new CardVerifyServiceRequest();
        request.setCardNum("123258");
        request.setCvv("123");
        request.setExpDate("12-2022");
        request.setNameOnCard("jk");

        CardVerifyServiceResponse response = svcClientImpl.verifyCardDetails(request);

        System.out.println("response is : " + response);
    } */

}
