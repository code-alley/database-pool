package kr.co.inslab.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;



public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {

		/*
		Iterator iter = requestContext.getHeaders().keySet().iterator();
		
		SLog.d("Request Info", "------------------  start  ---------------------");
		SLog.d("Uri", requestContext.getUriInfo().getAbsolutePath().toString());
		SLog.d("Method", requestContext.getMethod());
		*/
		
		/*
		while(iter.hasNext()){
			String headerKey = (String)iter.next();
			List<String> values = requestContext.getHeaders().get(headerKey);
			for(int i=0;i<values.size();i++){
				SLog.d("Header", headerKey + "|" + values.get(i)); 
			}
		}
		*/
		
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Headers",
	            "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials","true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}

	
	
}
