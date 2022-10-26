/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.exception;


/**
 * @author jatin, 23-Sep-2022
 * Description:
 */
public class SystemException extends Exception {

    private String respCode;
    private String respMsg;

    public SystemException(String respCode, String respMsg){
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    

}
