package com.keybank.statement.model;

import lombok.Data;

@Data
public class CardVerifyServiceRequest {

    private String cardNum;
    private String cvv;
    private String nameOnCard;
    private String expDate;
    
}
