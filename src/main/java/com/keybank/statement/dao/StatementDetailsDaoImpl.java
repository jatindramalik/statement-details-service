/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.keybank.statement.model.StatementDaoDetails;
import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsDaoResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description:
 */
@Component
public class StatementDetailsDaoImpl implements IStatementDetailsDao {

    @Override
    public StatementDetailsDaoResponse getStatementDetails(StatementDetailsDaoRequest daoRequest) {

        StatementDetailsDaoResponse statementDetailsDaoResponse = new StatementDetailsDaoResponse();
        try {
            String spcall = "CALL rtpb.GET_STATEMENT_DETAILS(?,?,?,?,?,?,?,?)";
            
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc://localhost:3306", "root", "root");

            CallableStatement csmt = con.prepareCall(spcall);

            //bind the values to the parameter

            csmt.setString(1, daoRequest.getClientId());
            csmt.setString(2,"online");
            csmt.setString(3,daoRequest.getCardnum());
            csmt.setString(4, daoRequest.getCvv());
            csmt.setString(5, daoRequest.getNameOnCard());
            csmt.setString(6, daoRequest.getExpDate());

            //register outparams

            csmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            csmt.registerOutParameter(8, java.sql.Types.VARCHAR);

            //execute the stored procedure
            boolean b = csmt.execute();

            String dbrespCode = csmt.getString(7);
            String dbrespMSg = csmt.getString(8);

            System.out.println("respcode: " + dbrespCode + ", " + "respmsg: " + dbrespMSg);

            List<StatementDaoDetails> statementDetailsList = new ArrayList<>();

            if("0".equals(dbrespCode)){
                //TODO: prepare dao resp

                ResultSet rs = csmt.executeQuery();

                while(rs.next()){
                    StatementDaoDetails statementDaoDetails = new StatementDaoDetails();
                    statementDaoDetails.setTxnId(rs.getString("txnid"));
                    statementDaoDetails.setDate(rs.getString("date"));
                    statementDaoDetails.setAmount(rs.getString("amount"));
                    // statementDaoDetails.setName(rs.getString("0"));
                    statementDaoDetails.setDescription(rs.getString("desc"));
                    statementDaoDetails.setRemark(rs.getString("remark"));
                    // statementDaoDetails.setRewardPoints(rs.getString("0"));
                    statementDaoDetails.setStatus(rs.getString("status"));

                    statementDetailsList.add(statementDaoDetails);
                }
                statementDetailsDaoResponse.setRespCode(dbrespCode);
                statementDetailsDaoResponse.setRespMsg(dbrespMSg);

                statementDetailsDaoResponse.setStatementDetails(statementDetailsList);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        //1.Get the request from service
        //2.prepare the sql/plsql queries to talk to database and get the response
        //3.prepare the StatementDetailsDaoResponse object with the help of ResultSet/database response
        return statementDetailsDaoResponse;
    }
    public static void main(String[] args) {
        StatementDetailsDaoImpl statementDetailsDaoImpl = new StatementDetailsDaoImpl();
        StatementDetailsDaoRequest daoRequest = new StatementDetailsDaoRequest();

        daoRequest.setCardnum("14789632258456");
        daoRequest.setCvv("123");
        daoRequest.setClientId("web");
        daoRequest.setNameOnCard("jk");
        daoRequest.setExpDate("12-2022");

        StatementDetailsDaoResponse statementDtlsResp = statementDetailsDaoImpl.getStatementDetails(daoRequest);

        System.out.println("response is : " + statementDtlsResp);
    }

}
