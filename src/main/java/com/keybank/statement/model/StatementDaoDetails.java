package com.keybank.statement.model;

import lombok.Data;

@Data
public class StatementDaoDetails {

    private String txnId;
    private String date;
    private String name;
    private String amount;
    private String description;
    private String rewardPoints;
    private String status;
    private String remark;

}
