/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.keybank.statement.model.CardVerifyServiceResponse;
import com.keybank.statement.model.StatementDaoDetails;
import com.keybank.statement.model.StatementDetails;
import com.keybank.statement.model.StatementDetailsDaoResponse;
import com.keybank.statement.model.StatementDetailsResponse;
import com.keybank.statement.model.StatusBlock;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Component
public class StatementDetailsResponseBuilder {

    public StatementDetailsResponse buildServiceResponse(StatementDetailsDaoResponse daoResponse, CardVerifyServiceResponse cardVerifyServiceResponse) {

        StatementDetailsResponse serviceResponse = new StatementDetailsResponse();
        List<StatementDetails> statementDetailsList = new ArrayList<>();

        
        
        //prepare statusblock
        StatusBlock statusBlock = new StatusBlock();
        statusBlock.setRespCode(daoResponse.getRespCode());
        statusBlock.setRespMsg(daoResponse.getRespMsg());

        List<StatementDaoDetails> statementDaoList = daoResponse.getStatementDetails();

        //get the list of statement details from dao and prepare StatementDetails service response

        for (StatementDaoDetails statementDaoDetails : statementDaoList) {
            StatementDetails statementDetails = new StatementDetails();
            statementDetails.setAmount(statementDaoDetails.getAmount());
            statementDetails.setAmount(statementDaoDetails.getDescription());
            statementDetails.setAmount(statementDaoDetails.getName());
            statementDetails.setAmount(statementDaoDetails.getRemark());
            statementDetails.setAmount(statementDaoDetails.getRewardPoints());
            statementDetails.setAmount(statementDaoDetails.getStatus());
            statementDetails.setAmount(statementDaoDetails.getTxnId());

            statementDetailsList.add(statementDetails);
        }
        serviceResponse.setStatus_block(statusBlock);;
        serviceResponse.setStatementDetails(statementDetailsList);
        System.out.println("service resp from response builder : " + serviceResponse );
        return null;
    }

}
