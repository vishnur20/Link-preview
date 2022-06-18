package filterpack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ResponseSpeedPrinter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		long start = System.currentTimeMillis();
		
		// calling servlet
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		
		System.out.println((end - start) + "ms");
	}
	
	@Override
	public void destroy() {}
}
