package intercom.InvisteCustomers;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTestDistance {
	
	int customerId = 17;
	String customerName = "Georgina Gallagher";
	double latitude2 = 54.180238;
	double longitude2 = -5.920898;
	@Test
	 public void test_JUnit() {
		assertEquals(17,CustomersList.calculateDistance(customerId, customerName, latitude2, longitude2));  
        
}
}
