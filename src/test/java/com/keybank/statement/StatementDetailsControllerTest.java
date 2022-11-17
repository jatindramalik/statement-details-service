package com.keybank.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.keybank.statement.controller.StatementDetailsController;
import com.keybank.statement.model.CustomerDetails;
import com.keybank.statement.model.StatementDetails;
import com.keybank.statement.model.StatementDetailsRequest;
import com.keybank.statement.model.StatementDetailsResponse;
import com.keybank.statement.model.StatusBlock;
import com.keybank.statement.service.StatementDetailsServiceImpl;
import com.keybank.statement.validator.StatementDetailsValidator;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatementDetailsController.class)
@ContextConfiguration(classes = {StatementDetailsController.class})
@ExtendWith(SpringExtension.class)
public class StatementDetailsControllerTest {


    @MockBean
    StatementDetailsServiceImpl svcImpl;

    @MockBean
    StatementDetailsValidator mockValidator;

    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void testGetStatementDetails_Success_Scenario(){

        String path ="/v1/transactions";

        //expection
        Mockito.doNothing().when(mockValidator).validateRequest(ArgumentMatchers.any(StatementDetailsRequest.class));

        Mockito.when(svcImpl.getStatementDetails(ArgumentMatchers.any(StatementDetailsRequest.class))).thenReturn(buildStatementDtlsResp());

        StatementDetailsRequest statementDetailsRequest = new StatementDetailsRequest();
        CustomerDetails customerDetails = new CustomerDetails();
        statementDetailsRequest.setCustomerDetails(customerDetails);

        //invoke or call service

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path).header("client-id", "1234")
                                        .header("request-id", "abc123xyz")
                                        .content(new ObjectMapper().writeValueAsString(statementDetailsRequest))
                                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(),mockResponse.getStatus());
    }

    @Test
    @SneakyThrows
    void testGetStatementDetails_400_Scenario(){

        String path ="/v1/transactions";

        //expection
        Mockito.doNothing().when(mockValidator).validateRequest(ArgumentMatchers.any(StatementDetailsRequest.class));

        Mockito.when(svcImpl.getStatementDetails(ArgumentMatchers.any(StatementDetailsRequest.class))).thenReturn(buildStatementDtlsResp());

        StatementDetailsRequest statementDetailsRequest = new StatementDetailsRequest();
        CustomerDetails customerDetails = new CustomerDetails();
        statementDetailsRequest.setCustomerDetails(customerDetails);

        //invoke or call service

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path).header("client-id", "1234")
                                        .header("request-id", "abc123xyz")
                                        .content(new ObjectMapper().writeValueAsString(statementDetailsRequest))
                                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockResponse = mvcResult.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(),mockResponse.getStatus());
    }


    private StatementDetailsResponse buildStatementDtlsResp() {

        StatementDetailsResponse statementDtlsResp = new StatementDetailsResponse();
        StatusBlock statusBlock = new StatusBlock();
        statusBlock.setRespCode("0");
        statusBlock.setRespMsg("success");

        List<StatementDetails> statementDetailsLst = new ArrayList<>();
        
        StatementDetails statementDetails = new StatementDetails();
        statementDetails.setAmount("10000");
        statementDetails.setDescription("good");
        statementDetails.setName("reliance");
        statementDetails.setRewardPoints("1000");
        statementDetails.setStatus("delivered");
        statementDetails.setTxnId("1111");
        statementDetails.setRemark("na");

        statementDetailsLst.add(statementDetails);

        statementDtlsResp.setStatus_block(statusBlock);
        statementDtlsResp.setStatementDetails(statementDetailsLst);
        
        return statementDtlsResp;
    }
    
}
