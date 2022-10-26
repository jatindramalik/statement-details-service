/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.dao;

import com.keybank.statement.exception.BusinessException;
import com.keybank.statement.exception.SystemException;
import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsDaoResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
public interface IStatementDetailsDao {

    StatementDetailsDaoResponse getStatementDetails(StatementDetailsDaoRequest daoRequest) throws SystemException, BusinessException;

}
