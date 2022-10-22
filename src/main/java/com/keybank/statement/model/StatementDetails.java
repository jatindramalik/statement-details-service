package com.keybank.statement.model;

import lombok.Data;

@Data
public class StatementDetails {

    private String txnId;
    private String name;
    private String amount;
    private String description;
    private String rewardPoints;
    private String status;
    private String remark;

}
