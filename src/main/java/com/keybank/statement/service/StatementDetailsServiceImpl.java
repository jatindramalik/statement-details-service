/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keybank.statement.builder.StatementDetailsRequestBuilder;
import com.keybank.statement.builder.StatementDetailsResponseBuilder;
import com.keybank.statement.dao.IStatementDetailsDao;
import com.keybank.statement.model.CardVerifyServiceRequest;
import com.keybank.statement.model.CardVerifyServiceResponse;
import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsDaoResponse;
import com.keybank.statement.model.StatementDetailsRequest;
import com.keybank.statement.model.StatementDetailsResponse;
import com.keybank.statement.serviceclient.ICardVerifyServiceClient;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Component
public class StatementDetailsServiceImpl implements IStatementDetailsService {

    @Autowired
    StatementDetailsRequestBuilder statementDetailsRequestBuilder;

    @Autowired
    ICardVerifyServiceClient iCardVerifyServiceClient;

    @Autowired
    IStatementDetailsDao statementDetailsDao;

    @Autowired
    StatementDetailsResponseBuilder statementDetailsResponseBuilder;

    @Override
    public StatementDetailsResponse getStatementDetails(StatementDetailsRequest statementDetailsRequest) {

        //1.Get the request from controller

        //2.Prepare the response for service client

      CardVerifyServiceRequest serviceClientRequest = statementDetailsRequestBuilder.buildServiceClientRequest(statementDetailsRequest);

      //3.call CardVerifyServiceCient by passing serviceClientRequest object and get the serviceClientResponse object

      CardVerifyServiceResponse cardVerifyServiceResponse = iCardVerifyServiceClient.verifyCardDetails(serviceClientRequest);

      //4. Perform some business logics

      //5. Prepare the request for dao with the help of statementDetaislRequest object

      StatementDetailsDaoRequest daoRequest = statementDetailsRequestBuilder.buildDaoRequest(statementDetailsRequest);

      //6. call dao by passing daoRequest and get the daoResponse

      StatementDetailsDaoResponse daoResponse = statementDetailsDao.getStatementDetails(daoRequest);

      //Prepare the StatementDetailsResponse response with the help of dao response and serviceclient resp

      StatementDetailsResponse statementDetailsResponse = statementDetailsResponseBuilder.buildServiceResponse(daoResponse,cardVerifyServiceResponse);

        return statementDetailsResponse;
    }

}
