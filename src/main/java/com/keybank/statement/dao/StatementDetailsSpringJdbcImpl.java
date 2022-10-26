package com.keybank.statement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.lang.Nullable;

import com.keybank.statement.exception.BusinessException;
import com.keybank.statement.exception.SystemException;
import com.keybank.statement.model.StatementDaoDetails;
import com.keybank.statement.model.StatementDetailsDaoRequest;
import com.keybank.statement.model.StatementDetailsDaoResponse;

/**
 * @author jatin, 21-Oct-2022
 * Description: Here JdbcTemplet is used to call stored procedure
 */
public class StatementDetailsSpringJdbcImpl extends StoredProcedure implements IStatementDetailsDao,RowMapper<StatementDaoDetails> {

    public StatementDetailsSpringJdbcImpl(JdbcTemplate jdbcTemplate){
        super(jdbcTemplate, "GET_STATEMENT_DETAILS");
        compileParams();

    }

    private void compileParams() {
        //input params

        declareParameter(new SqlParameter("CLIENT_ID_IN", Types.VARCHAR));
        declareParameter(new SqlParameter("CHANNEL_ID_IN", Types.VARCHAR));
        declareParameter(new SqlParameter("CARDNUMBER_IN", Types.VARCHAR));
        declareParameter(new SqlParameter("CVV_IN", Types.VARCHAR));
        declareParameter(new SqlParameter("NAME_ON_CARD_IN", Types.VARCHAR));
        declareParameter(new SqlParameter("EXP_DATE_IN", Types.VARCHAR));

        //Output params
        declareParameter(new SqlOutParameter("RESP_CODE_OUT", Types.VARCHAR));
        declareParameter(new SqlOutParameter("RESP_MSG_OUT", Types.VARCHAR));

        //resultset
        declareParameter(new SqlReturnResultSet("statement-result-ser", this));
        compile();
    }

    @Override
    @Nullable
    public StatementDaoDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        StatementDaoDetails statementDaoDetails = new StatementDaoDetails();

        statementDaoDetails.setTxnId(rs.getString(1));
        statementDaoDetails.setDate(rs.getString(2));
        statementDaoDetails.setAmount(rs.getString(3));
        statementDaoDetails.setDescription(rs.getString(4));
        statementDaoDetails.setStatus(rs.getString(5));
        statementDaoDetails.setRemark(rs.getString(6));


        return statementDaoDetails;
    }

    @Override
    public StatementDetailsDaoResponse getStatementDetails(StatementDetailsDaoRequest daoRequest) throws SystemException, BusinessException {
        
        StatementDetailsDaoResponse daoResp = new StatementDetailsDaoResponse();

        try {
            // prepare stored procedure request
           
            Map<String, Object> inParams = new HashMap<>();
            
            inParams.put("CLIENT_ID_IN", daoRequest.getClientId());
            inParams.put("CHANNEL_ID_IN", "online");
            inParams.put("CARDNUMBER_IN", daoRequest.getCardnum());
            inParams.put("CVV_IN", daoRequest.getCvv());
            inParams.put("NAME_ON_CARD_IN", daoRequest.getNameOnCard());
            inParams.put("EXP_DATE_IN", daoRequest.getEdDate());
    
            Map<String, Object> spResponse = super.execute(inParams);
    
            String dbRespCode = null;
            String dbRespMsg = null;
    
            if(spResponse != null){
    
                dbRespCode = spResponse.get("RESP_CODE_OUT").toString();
                dbRespMsg = spResponse.get("RESP_MSG_OUT").toString();
            }
    
            List<StatementDaoDetails> statementDetails;
    
            if("0".equals(dbRespCode)){
                statementDetails = (List<StatementDaoDetails>)spResponse.get("statement-result-set");
    
                daoResp.setRespCode(dbRespCode);
                daoResp.setRespMsg(dbRespMsg);
                daoResp.setStatementDetails(statementDetails);
            }else if("100".equals(dbRespCode) || "101".equals(dbRespCode) || "102".equals(dbRespCode) || "103".equals(dbRespCode)){
                throw new BusinessException(dbRespCode, dbRespMsg);
    
            }else if("111".equals(dbRespCode) || "222".equals(dbRespCode) || "333".equals(dbRespCode) || "444".equals(dbRespCode)){
                throw new SystemException(dbRespCode, dbRespMsg);
    
            }else{
                throw new SystemException("777", "Unknown error from database");
            }
            
        } catch (DataAccessException de) {
            de.printStackTrace();
            throw de;
        }catch (BusinessException be) {
            be.printStackTrace();
            throw be;
        }catch (SystemException se) {
            se.printStackTrace();
            throw se;
        }
   
    return daoResp;
    }
    
}
