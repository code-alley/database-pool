package kr.co.inslab.web.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.inslab.log.SLog;

/**
 * DatabasePool Service
 * Version 1.0
 * 2105-09-21
 * @author jdkim
 *
 */
@Path("/v1")
public class DatabasePoolService {
	@Context ServletContext context;
	
	private boolean bInit= false;
	public final String KEY_ERROR = "error";
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/query")
	public String execQuery(@QueryParam("callback") String callback, 
								@PathParam("database") String database,
								String body,
								@Context HttpServletRequest httpRequest) {
		
		serviceInit();
		
		SLog.d("sql", body);
		
		JSONObject result = new JSONObject();
		
		/*
		 * Basic Authentication
		 */
		if(!checkAuthentication(httpRequest))
		{
			SLog.d("Authentication failed");
			return result.put(KEY_ERROR, "Authentication failed").toString();
		}
			
		/*	
		 * API KEY 
		String apikey = httpRequest.getHeader("apikey");
		if(apikey == null || !apikey.equalsIgnoreCase("inslab"))
		{
			return new JSONObject().put("error", "invalid apikey").toString();
		}
		 */		
		
		
		Connection conn = null;
	    try{
	    	
	        javax.naming.Context initContext = (javax.naming.Context) new InitialContext();
	        javax.naming.Context envContext  = (javax.naming.Context)initContext.lookup("java:/comp/env");
	        DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
	        conn = ds.getConnection();
	        
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(body);

	        result.put("result", convertToJSON(rs));
	        
	    }catch(Exception e){
	        e.printStackTrace();
	        result.put(KEY_ERROR, e.getLocalizedMessage());
	    }finally {
	        try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result.put(KEY_ERROR, e.getLocalizedMessage());
			}
	    }
	
	    
	    SLog.d("response", result.toString());
	    
		return result.toString();
	}
	
	private void serviceInit() {
		if(!bInit){
			SLog.project = "DatabasePool";
			bInit = true;
		}
	}

	/**
	 * 인증 처리 (Basic Authentication)
	 * @param request
	 * @return
	 */
	private boolean checkAuthentication(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header == null || header.equalsIgnoreCase(""))
			return false;
		
		assert header.substring(0, 6).equals("Basic ");
		String basicAuthEncoded = header.substring(6);
		String basicAuthAsString = new String(
				new Base64().decode(basicAuthEncoded.getBytes()));
		
		String user = context.getInitParameter("auth-user");
		String passwd = context.getInitParameter("auth-password");
		
		String [] tokens = basicAuthAsString.split(":");
		
		if(tokens.length != 2)
			return false;
		
		if(user.equals(tokens[0]) && passwd.equals(tokens[1]))
			return true;
		
		return false;
	}

	/**
	 * Database 쿼리 실행결과를 JSON형식으로 변환
	 * @param resultSet
	 * @return
	 * @throws Exception
	 */
	public JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        int total_columns = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            
            JSONObject obj = new JSONObject();
            for (int i = 1; i <= total_columns; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i)
                        .toLowerCase(), resultSet.getObject(i));
                
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }
	
	/**
	 * JQuery - JSONP CrossDomain 처리
	 * @param callback
	 * @param result
	 * @return
	 */
	public String checkJsonP(String callback, String result){
		if(callback != null)
			result = callback + "(" + result + ")";
		
		SLog.d("result", result);
		return result;
	}
	
	
	
}


/**
 *	GET Method Sample 
 *
 	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/jenkins/build")
	public String jekinsBuild(
			@QueryParam("repository") String repository) 
	{
		return "";
	}
	*/
