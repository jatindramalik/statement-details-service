package com.keybank.statement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.keybank.statement.builder.StatementDetailsRequestBuilder;
import com.keybank.statement.builder.StatementDetailsResponseBuilder;
import com.keybank.statement.dao.StatementDetailsDaoImpl;
import com.keybank.statement.exception.BusinessException;
import com.keybank.statement.exception.SystemException;
import com.keybank.statement.model.CardVerifyServiceRequest;
import com.keybank.statement.model.CardVerifyServiceResponse;
import com.keybank.statement.model.CustomerDetails;
import com.keybank.statement.model.DateRange;
import com.keybank.statement.model.RequestInfo;
import com.keybank.statement.model.StatementDaoDetails;
import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsDaoResponse;
import com.keybank.statement.model.StatementDetailsRequest;
import com.keybank.statement.model.StatementDetailsResponse;
import com.keybank.statement.service.StatementDetailsServiceImpl;
import com.keybank.statement.serviceclient.ICardVerifyServiceClient;

@ExtendWith(SpringExtension.class)

public class StatementDetailsServiceImplTest {

    @InjectMocks
    StatementDetailsServiceImpl statementDtlsSvcImpl = new StatementDetailsServiceImpl();

    @Mock
    StatementDetailsRequestBuilder statementDetailsRequestBuilder;

    @Mock
    ICardVerifyServiceClient iCardVerifyServiceClient;

    @Mock
    StatementDetailsDaoImpl statementDetailsDao;

    @Mock
    StatementDetailsResponseBuilder statementDetailsResponseBuilder;

    @Test
    // @SneakyThrows
    void testGetStatementDetails_Success_Scenario() {

        System.out.println("serviceimpl:  " + statementDtlsSvcImpl);
        System.out.println("statementDetailsRequestBuilder:  " + statementDetailsRequestBuilder);
        System.out.println("iCardVerifyServiceClient:  " + iCardVerifyServiceClient);
        System.out.println("statementDetailsResponseBuilder:  " + statementDetailsResponseBuilder);

        try {
            Mockito.when(statementDetailsRequestBuilder
                    .buildServiceClientRequest(ArgumentMatchers.any(StatementDetailsRequest.class))).thenCallRealMethod();

            Mockito.when(
                    statementDetailsRequestBuilder.buildDaoRequest(ArgumentMatchers.any(StatementDetailsRequest.class)))
                    .thenCallRealMethod();

            Mockito.when(statementDetailsResponseBuilder.buildServiceResponse(
                    ArgumentMatchers.any(StatementDetailsDaoResponse.class),
                    ArgumentMatchers.any(CardVerifyServiceResponse.class))).thenCallRealMethod();

            Mockito.when(iCardVerifyServiceClient.verifyCardDetails(ArgumentMatchers.any(CardVerifyServiceRequest.class)))
                    .thenReturn(buildCardVerifyResponse());

            Mockito.when(statementDetailsDao.getStatementDetails(ArgumentMatchers.any(StatementDetailsDaoRequest.class)))
                    .thenReturn(buildDaoResponse());

            StatementDetailsRequest statementDetailsRequest = buildStatementDetailsRequest();

            StatementDetailsResponse response = statementDtlsSvcImpl.getStatementDetails(statementDetailsRequest);

            // assertNotNull(response);

            assertEquals("0", response.getStatus_block().getRespCode());
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testGetStatementDetails_BusinessException_Scenario() {

        StatementDetailsResponse response;

        try {
            Mockito.when(statementDetailsRequestBuilder
                    .buildServiceClientRequest(ArgumentMatchers.any(StatementDetailsRequest.class)))
                    .thenCallRealMethod();

            Mockito.when(
                    statementDetailsRequestBuilder.buildDaoRequest(ArgumentMatchers.any(StatementDetailsRequest.class)))
                    .thenCallRealMethod();

            Mockito.when(
                    iCardVerifyServiceClient.verifyCardDetails(ArgumentMatchers.any(CardVerifyServiceRequest.class)))
                    .thenReturn(buildCardVerifyResponse());

            Mockito.when(
                    statementDetailsDao.getStatementDetails(ArgumentMatchers.any(StatementDetailsDaoRequest.class)))
                    .thenThrow(new BusinessException("47","cardnumber invalid"));

            StatementDetailsRequest statementDetailsRequest = buildStatementDetailsRequest();

            response = statementDtlsSvcImpl.getStatementDetails(statementDetailsRequest);

        } catch (BusinessException e) {
            assertEquals("47", e.getRespCode());
        } catch (SystemException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testGetStatementDetails_SystemException_Scenario() {

        StatementDetailsResponse response;

        try {
            Mockito.when(statementDetailsRequestBuilder
                    .buildServiceClientRequest(ArgumentMatchers.any(StatementDetailsRequest.class)))
                    .thenCallRealMethod();

            Mockito.when(
                    statementDetailsRequestBuilder.buildDaoRequest(ArgumentMatchers.any(StatementDetailsRequest.class)))
                    .thenCallRealMethod();

            Mockito.when(
                    iCardVerifyServiceClient.verifyCardDetails(ArgumentMatchers.any(CardVerifyServiceRequest.class)))
                    .thenReturn(buildCardVerifyResponse());

            Mockito.when(
                    statementDetailsDao.getStatementDetails(ArgumentMatchers.any(StatementDetailsDaoRequest.class)))
                    .thenThrow(new SystemException("666", "cardnumber invalid"));

            StatementDetailsRequest statementDetailsRequest = buildStatementDetailsRequest();

            response = statementDtlsSvcImpl.getStatementDetails(statementDetailsRequest);

        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            assertEquals("666", e.getRespCode());

        }

    }

    private StatementDetailsRequest buildStatementDetailsRequest() {

        StatementDetailsRequest statementDetailsRequest = new StatementDetailsRequest();

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCardNum("1258357863254562");
        customerDetails.setCvv("123");
        customerDetails.setExpDate("03-11-2024");
        customerDetails.setNameOnCard("jk");

        DateRange dateRange = new DateRange();
        dateRange.setSartDate("03-07-2022");
        dateRange.setEndDate("03-11-2022");

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setSortField("date");
        requestInfo.setSorting(true);
        requestInfo.setSortType("ascen");
        requestInfo.setTransType("success");

        statementDetailsRequest.setCustomerDetails(customerDetails);
        statementDetailsRequest.setDateRange(dateRange);
        statementDetailsRequest.setRequestInfo(requestInfo);
        return statementDetailsRequest;
    }

    private StatementDetailsDaoResponse buildDaoResponse() {

        System.out.println("entered mock StatementDetailsDaoResponse");

        StatementDetailsDaoResponse statementDetailsDaoResponse = new StatementDetailsDaoResponse();
        statementDetailsDaoResponse.setRespCode("0");
        statementDetailsDaoResponse.setRespMsg("success");

        List<StatementDaoDetails> statementDeatils = new ArrayList<>();

        StatementDaoDetails statementDaoDetails = new StatementDaoDetails();
        statementDaoDetails.setTxnId("12345");
        statementDaoDetails.setAmount("10000");
        statementDaoDetails.setDate("03-11-2022");
        statementDaoDetails.setDescription("good");
        statementDaoDetails.setName("jk");
        statementDaoDetails.setRewardPoints("10000");
        statementDaoDetails.setStatus("delivered");
        statementDaoDetails.setRemark("na");

        statementDeatils.add(statementDaoDetails);
        statementDetailsDaoResponse.setStatementDetails(statementDeatils);

        return statementDetailsDaoResponse;
    }

    private CardVerifyServiceResponse buildCardVerifyResponse() {

        System.out.println("entered mock cardverifyresponse");

        CardVerifyServiceResponse response = new CardVerifyServiceResponse();
        response.setRespCode("0");
        response.setStatus("active");
        response.setRespMsg("success");
        return response;
    }

}
