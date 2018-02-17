package com.iqmsoft.springaop;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.iqmsoft.springaop.model.Client;
import com.iqmsoft.springaop.model.ClientService;


@SpringBootApplication
public class SpringAopApp
{
	
	public static void main(final String[] args)
	{
		SpringApplication.run(SpringAopApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx)
	{
		return args -> {

			System.out.println("Beans provided by Spring Boot:");

			final String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (final String beanName : beanNames)
			{
				System.out.println(beanName);
			}

			final Client client = (Client) ctx.getBean("client");
			final ClientService clientService = (ClientService) ctx.getBean("clientService");

			System.out.println("First Name is:" + clientService.getFirstName(client));
			System.out.println("Last Name is:" + clientService.getLastName(client));
			System.out.println("Age is:" + clientService.getAge(client));

		};
	}
}
