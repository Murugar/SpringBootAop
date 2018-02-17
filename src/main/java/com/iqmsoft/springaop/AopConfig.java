package com.iqmsoft.springaop;

import java.time.LocalDate;
import java.time.Month;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.iqmsoft.springaop.model.PerfInterceptor;
import com.iqmsoft.springaop.model.Client;
import com.iqmsoft.springaop.model.ClientService;


@Configuration
@EnableAspectJAutoProxy
public class AopConfig
{

	@Pointcut("execution(public String com.iqmsoft.springaop.model.ClientService.getFirstName(..))")
	public void monitor()
	{
	}

	@Pointcut("execution(public String com.iqmsoft.springaop.model.ClientService.getLastName(..))")
	public void monitorAgain()
	{
	}
	
	@Pointcut("execution(public int com.iqmsoft.springaop.model.ClientService.getAge(..))")
	public void myMonitor()
	{
	}

	@Bean
	public PerformanceMonitorInterceptor performanceMonitorInterceptor()
	{
		return new PerformanceMonitorInterceptor(true);
	}

	@Bean
	public Advisor performanceMonitorAdvisor()
	{
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("com.iqmsoft.springaop.AopConfig.monitorAgain()");
		return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
	}

	@Bean
	public Client client()
	{
		return new Client("Test1", "Test2", LocalDate.of(1980, Month.MARCH, 12));
	}

	@Bean
	public ClientService clientService()
	{
		return new ClientService();
	}

	@Bean
	public PerfInterceptor myPerformanceMonitorInterceptor()
	{
		return new PerfInterceptor(true);
	}

	@Bean
	public Advisor myPerformanceMonitorAdvisor()
	{
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("com.iqmsoft.springaop.AopConfig.myMonitor()");
		return new DefaultPointcutAdvisor(pointcut, myPerformanceMonitorInterceptor());
	}

}