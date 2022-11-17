package com.keybank.statement.serviceclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.keybank.statement.model.CardVerifyServiceRequest;
import com.keybank.statement.model.CardVerifyServiceResponse;

import io.swagger.models.HttpMethod;

@ExtendWith(SpringExtension.class)
public class CardVerifyServiceClientImplTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    CardVerifyServiceClientImpl serviceClientImpl;

    @Test
    void testVerifyCardDetails() {

        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.any(Class.class))).thenReturn(buildCardVerifyResp());

        CardVerifyServiceClientImpl serviceClientImpl = new CardVerifyServiceClientImpl();

        CardVerifyServiceRequest request = new CardVerifyServiceRequest();
        request.setCardNum("4758236548822");
        request.setCvv("254");
        request.setExpDate("12-2022");
        request.setNameOnCard("jk");

        CardVerifyServiceResponse response = new CardVerifyServiceResponse();

        assertNotNull(response);

    }

    private ResponseEntity<CardVerifyServiceResponse> buildCardVerifyResp() {

        System.out.println("Enter into mockresponse");
        CardVerifyServiceResponse response = new CardVerifyServiceResponse();
        response.setRespCode("0");
        response.setRespMsg("success");
        response.setStatus("active");

        return new ResponseEntity<CardVerifyServiceResponse>(response, null, HttpStatus.OK.value());
    }
}
