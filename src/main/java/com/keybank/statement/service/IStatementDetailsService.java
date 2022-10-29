/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.service;

import com.keybank.statement.exception.BusinessException;
import com.keybank.statement.exception.SystemException;
import com.keybank.statement.model.StatementDetailsRequest;
import com.keybank.statement.model.StatementDetailsResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
public interface IStatementDetailsService {

    public StatementDetailsResponse getStatementDetails(StatementDetailsRequest statementDetailsRequest)throws SystemException, BusinessException;

}
