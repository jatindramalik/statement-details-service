/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keybank.statement.model.StatementDetailsRequest;
import com.keybank.statement.model.StatementDetailsResponse;
import com.keybank.statement.service.IStatementDetailsService;
import com.keybank.statement.validator.StatementDetailsValidator;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@RestController
@RequestMapping("/v1")
public class StatementDetailsController {

    @Autowired
    private StatementDetailsValidator validator;

    @Autowired
    private IStatementDetailsService statementDetailsService;

    @PostMapping("/transactions")
    public StatementDetailsResponse getStatementDetails(@RequestBody StatementDetailsRequest statementDetailsRequest,
                                                        @RequestHeader("client_id")String clientId,
                                                        @RequestHeader("request_id")String requestId,
                                                        @RequestHeader("msg_ts") String messageTs){
        
        //1.Get the request from the client/consumer
        //2.validate the request
        validator.validateRequest(statementDetailsRequest);

        //3.prepare the request for service class

        //4.call service class and get the service response  

        StatementDetailsResponse response = statementDetailsService.getStatementDetails(statementDetailsRequest);

         return response;
        
    }

}
