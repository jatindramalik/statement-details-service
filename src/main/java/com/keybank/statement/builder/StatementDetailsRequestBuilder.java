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

        //TODO: Prepare crdVerifyServiceRequest object with the help of StatementDetailsRequest object
        return null;
    }

    public StatementDetailsDaoRequest buildDaoRequest(StatementDetailsRequest statementDetailsRequest) {

        //TODO : Prepare StatementDetailsDaoRequest object with the help of StatementDetailsRequest object
        return null;
    }

}
