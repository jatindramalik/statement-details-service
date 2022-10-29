/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keybank.statement.exception.BusinessException;
import com.keybank.statement.exception.StatementDetailsRequestInvalidException;
import com.keybank.statement.exception.SystemException;
import com.keybank.statement.model.StatementDetailsResponse;
import com.keybank.statement.model.StatusBlock;

/**
 * @author jatin, 21-Oct-2022
 *         Description:
 */
@ControllerAdvice
public class StatementDetailsControllerAdvice {

    @ExceptionHandler(value = StatementDetailsRequestInvalidException.class)
    public StatementDetailsResponse handleRequestInvalidException(StatementDetailsRequestInvalidException exception) {

        return buildErrorResp(exception.getRespCode(), exception.getRespMsg());

    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public StatementDetailsResponse handleDataErrors(BusinessException exception) {

        return buildErrorResp(exception.getRespCode(), exception.getRespMsg());

    }

    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public StatementDetailsResponse handleSystemErrors(SystemException exception) {

        return buildErrorResp(exception.getRespCode(), exception.getRespMsg());

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public StatementDetailsResponse handleGenricErrors(Exception exception) {
        exception.printStackTrace();
        return buildErrorResp("stmt777", "Unknown error from statementdetails service");

    }

    private StatementDetailsResponse buildErrorResp(String respCode, String respMsg) {
        StatementDetailsResponse statementDetailsResponse = new StatementDetailsResponse();

        StatusBlock statusBlock = new StatusBlock();
        statusBlock.setRespCode(respCode);
        statusBlock.setRespMsg(respMsg);

        statementDetailsResponse.setStatus_block(statusBlock);
        return statementDetailsResponse;
    }

}
