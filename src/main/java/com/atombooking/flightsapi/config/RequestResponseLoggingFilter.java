package com.atombooking.flightsapi.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


/*
 * This class serves the purpose of putting the request uuid and consumer info in the Mapped Diagnostic Context(MDC)
 * So that when loggers are logging , they can log this as part of every log messages . MDC values would be per thread basis
 * meaning each thread would have its own MDC context. When the request is received, we will put the uuid and consumer info in the MDC context and after sending the response we should clear it
 *
 * It also serves the purpose of logging requests and responses in a single place
 */
@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
	private final String requuestUUID = "request-uuid";
	private final String consumerName = "consumer-name";
	private static Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request); // This will copy the original request
	        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response); // This will copy the original response
	        if(req.getHeader(requuestUUID)!= null && !req.getHeader(requuestUUID).isEmpty() && req.getHeader(consumerName)!= null && !req.getHeader(consumerName).isEmpty()) {
	        	// Putting the uuid and consumer name to MDC for better tractability 
	        	MDC.put(requuestUUID, req.getHeader(requuestUUID)); 
	        	MDC.put(consumerName, req.getHeader(consumerName));
	        }
	        
	        String reqQueryString = request.getQueryString();
	        String reqBody = new String(req.getContentAsByteArray() , StandardCharsets.UTF_8);
	        
	        logger.info("Request Method: {} , Endpoint URL : {}" , req.getMethod() , req.getRequestURI());
	        if(reqQueryString !=null && !reqQueryString.isEmpty())
	        logger.debug("Request Query String: {}" , req.getQueryString());
	        if(reqBody!=null && !reqBody.isEmpty())
	        logger.debug("Request Body: {}" , reqBody);
	        
	        filterChain.doFilter(req, resp);
	        
	        String resBody = new String(resp.getContentAsByteArray() , StandardCharsets.UTF_8);
	        
	        logger.info("Response Content Type {}" , resp.getContentType());
	        if(resBody!=null && !resBody.isEmpty())
	        logger.debug("Response Body: {}" ,resBody);
	        
	        MDC.clear(); // clearing the MDC
	        resp.copyBodyToResponse(); // respond with the cached data
		
	}
}
