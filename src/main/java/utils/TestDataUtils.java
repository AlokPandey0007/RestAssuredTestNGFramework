package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataUtils {

	Random random ;
	 public TestDataUtils()
	 {
		random = new Random();
	 }

	public String getFirstName()
	{
		String[] firstNames = {"John", "Alice", "Michael", "Emily", "David", "Sophia", "Daniel", "Olivia"};
		return firstNames[random.nextInt(firstNames.length)];
	}
	
	public String getLastName()
	{
		String[] lastNames = {"Smith", "Johnson", "Brown", "Lee", "Williams", "Davis", "Miller", "Wilson"};
		return lastNames[random.nextInt(lastNames.length)];
	}
	
	public Integer getPrice()
	{
		return random.nextInt(1000, 10000);
	}
	
	public boolean getBooleanFlag()
	{
		return	random.nextBoolean();
	}
	
	public String getStartDate()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate date= LocalDate.now();
		return date.format(formatter);
		
	}
	
	public String getEndDate(String checkInDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(checkInDate, formatter);
        
        localDate.plusDays(2);
       
		
		return	 localDate.format(formatter);
		
	}
	
	public String getAdditionalNeeds()
	{
		String[] needs = {"breakfast", "dinner", "lunch", "gyser", "wifi", "petfriendly", "balcony", "tv"};
		return needs[random.nextInt(needs.length)];
	}
}
