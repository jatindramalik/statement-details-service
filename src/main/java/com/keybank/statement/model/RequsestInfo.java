package com.keybank.statement.model;

import lombok.Data;

@Data
public class RequsestInfo {

    private String transType;
    private boolean isSorting;
    private String sortField;
    private String sortType;

}
