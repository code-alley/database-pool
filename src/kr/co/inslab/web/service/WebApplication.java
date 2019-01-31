package kr.co.inslab.web.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import kr.co.inslab.web.filter.CORSFilter;


public class WebApplication extends Application {

	 @Override
	    public Set<Class<?>> getClasses() {
	        final Set<Class<?>> classes = new HashSet<Class<?>>();
	        // register resources and features
	        classes.add(CORSFilter.class);

	        return classes;
	    }
	 
}
