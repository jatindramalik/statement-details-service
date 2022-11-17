/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.model;



import lombok.Data;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Data
public class StatementDetailsRequest {

    private CustomerDetails CustomerDetails;
    private DateRange dateRange;
    private RequestInfo requestInfo;
   

}
