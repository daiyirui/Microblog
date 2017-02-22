package com.microblog.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetEncodingFilter implements Filter {
	private String endcoding;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		request.setCharacterEncoding(endcoding);

		chain.doFilter(request, response);

	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.endcoding = filterConfig.getInitParameter("endcoding");
		
	}


}
