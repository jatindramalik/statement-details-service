/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.builder;

import org.springframework.stereotype.Component;

import com.keybank.statement.model.CardVerifyServiceRequest;
import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsRequest;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Component
public class StatementDetailsRequestBuilder {

    public CardVerifyServiceRequest buildServiceClientRequest(StatementDetailsRequest statementDetailsRequest) {

        
        System.out.println("Entered into buildServiceClientRequest");

        CardVerifyServiceRequest cardServiceRequest = new CardVerifyServiceRequest();
        cardServiceRequest.setCardNum(statementDetailsRequest.getCustomerDetails().getCardNum());
        cardServiceRequest.setCvv(statementDetailsRequest.getCustomerDetails().getCvv());
        cardServiceRequest.setExpDate(statementDetailsRequest.getCustomerDetails().getExpDate());
        cardServiceRequest.setNameOnCard(statementDetailsRequest.getCustomerDetails().getNameOnCard());
        
        
        return cardServiceRequest;
    }

    public StatementDetailsDaoRequest buildDaoRequest(StatementDetailsRequest statementDetailsRequest) {

        StatementDetailsDaoRequest daoRequest = new StatementDetailsDaoRequest();
        daoRequest.setCardnum(statementDetailsRequest.getCustomerDetails().getCardNum());
        daoRequest.setClientId("web");
        daoRequest.setCvv(statementDetailsRequest.getCustomerDetails().getCvv());
        daoRequest.setExpDate(statementDetailsRequest.getCustomerDetails().getExpDate());
        daoRequest.setNameOnCard(statementDetailsRequest.getCustomerDetails().getNameOnCard());
        daoRequest.setStDate(statementDetailsRequest.getCustomerDetails().getStDate());
        daoRequest.setEdDate(statementDetailsRequest.getCustomerDetails().getEdDate());
        
        return daoRequest;
    }

}
