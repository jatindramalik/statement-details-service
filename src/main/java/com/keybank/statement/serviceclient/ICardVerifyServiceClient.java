/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.serviceclient;



import com.keybank.statement.model.CardVerifyServiceRequest;
import com.keybank.statement.model.CardVerifyServiceResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */

public interface ICardVerifyServiceClient {

    CardVerifyServiceResponse verifyCardDetails(CardVerifyServiceRequest request);


}
