package com.keybank.statement.model;

import lombok.Data;

@Data
public class CardVerifyServiceResponse {

    private String customerName;
    private String status;
    private boolean isPastDue;
    
    
}
