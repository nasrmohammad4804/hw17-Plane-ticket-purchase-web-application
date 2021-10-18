package service.servlet.filter;

import service.impl.CompanyAccountServiceImpl;
import service.util.ApplicationContext;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

public class AddDefaultCompany implements Filter {

    CompanyAccountServiceImpl companyAccountService= ApplicationContext.getApplicationContext()
            .getCompanyAccountService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            if(companyAccountService.countOfCompanyRegistered()==0){
                companyAccountService.addDefaultCompany();
            }
            filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
