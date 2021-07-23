import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainAppTesting {
	
private MainApp transactionMainApp;
	
	List<Transaction> transactionList;
	RelativeAccountBalance r;
	
	@BeforeEach
	public void setUp() {
		transactionMainApp = new MainApp();
		Stream<String> transactions = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("input.csv").getFile());
			transactions = Files.lines(Paths.get(file.getAbsolutePath()));
		}catch(IOException e) {
			e.printStackTrace();
            System.exit(1);
		}
		
		transactionList = transactionMainApp.convertToTransactions(transactions);
		r= new RelativeAccountBalance(transactionList);

	}

	@Test
	public void testGetFilteredTransactions() {
		
		assertEquals(1, r.numofTranscations("ACC334455", Formater.getLocalDateTimeFromString("20/10/2018 12:00:00"),  Formater.getLocalDateTimeFromString("20/10/2018 19:00:00")));

	}
	
	
	
	@Test
	public void testAllAccountTransaction() {
		
		assertEquals(2, r.numofTranscations("ACC334455", Formater.getLocalDateTimeFromString("20/10/2018 12:00:00"),  Formater.getLocalDateTimeFromString("20/10/2021 19:00:00")));

	}
	
	@Test
	public void checkRelativeBalInTransaction() {
		
		assertEquals("-($25.00)", r.calculateRelativeBalance("ACC334455", Formater.getLocalDateTimeFromString("20/10/2018 12:00:00"),  Formater.getLocalDateTimeFromString("20/10/2018 19:00:00")));

	}

}
