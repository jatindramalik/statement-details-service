package com.keybank.statement.model;

import java.util.List;

import lombok.Data;

@Data
public class StatementDetailsDaoResponse {

    private String respCode;
    private String respMsg;

    private List<StatementDaoDetails> statementDetails;
    
}
