import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class MainAppTest {
	
	private MainApp transactionMainApp;
	
	List<Transaction> transactionList;
	RelativeAccountBalance r;
	@BeforeEach
	public void setUp() {
		transactionMainApp = new MainApp();
		Stream<String> transactions = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("fileName").getFile());
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
		
		assertEquals(1, r.numofTranscations("ACC334455", Formater.getLocalDateTimeFromString("20/10/2018 12:00:00"),  Formater.getLocalDateTimeFromString("23/07/2021 19:00:00")));

	}

}
