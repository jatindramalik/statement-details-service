/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.dao;

import org.springframework.stereotype.Component;

import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsDaoResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Component
public class StatementDetailsDaoImpl implements IStatementDetailsDao {

    @Override
    public StatementDetailsDaoResponse getStatementDetails(StatementDetailsDaoRequest daoRequest) {

        StatementDetailsDaoResponse statementDetailsDaoResponse = new StatementDetailsDaoResponse();
        //1.Get the request from service
        //2.prepare the sql/plsql queries to talk to database and get the response
        //3.prepare the StatementDetailsDaoResponse object with the help of ResultSet/database response
        return statementDetailsDaoResponse;
    }

}
