package com.iqmsoft.springaop.model;

import java.time.LocalDate;
import java.time.Period;


public class ClientService
{
	public String getFirstName(final Client person)
	{
		return person.getFirstName();
	}

	public String getLastName(final Client person)
	{
		return person.getLastName();
	}
	
	public int getAge(final Client person)
	{
		final Period period = Period.between(person.getDateOfBirth(),
				LocalDate.now());
		return period.getYears();
	}
}
