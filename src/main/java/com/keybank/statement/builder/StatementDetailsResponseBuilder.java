/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.builder;

import org.springframework.stereotype.Component;

import com.keybank.statement.model.CardVerifyServiceResponse;
import com.keybank.statement.model.StatementDetailsDaoResponse;
import com.keybank.statement.model.StatementDetailsResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Component
public class StatementDetailsResponseBuilder {

    public StatementDetailsResponse buildServiceResponse(StatementDetailsDaoResponse daoResponse, CardVerifyServiceResponse cardVerifyServiceResponse) {

        //TODO: prepare the StatementDetailsResponse object with the help of daoResponse and cardVerifyServiceResponse
        return null;
    }

}
