import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainApp {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String args[]) {
		String fileName;
		
		System.out.print("Enter the input filename : ");
	    fileName= scanner.nextLine(); //reads string.
	    Stream<String> transactions = null;
		try {
			transactions = Files.lines(Paths.get(fileName));
		}catch(IOException e) {
			e.printStackTrace();
            System.exit(1);
		}
		
		List<Transaction> transactionList = convertToTransactions(transactions);
		
		
		RelativeAccountBalance r= new RelativeAccountBalance(transactionList);

		System.out.println("Balance Calculation, Enter the Account ID : ");
        String accountID = scanner.nextLine();
        System.out.println("Enter the From Date and Time : ");
        LocalDateTime fromDate = Formater.getLocalDateTimeFromString(scanner.nextLine());
        System.out.println("Enter the To Date and Time : ");
        LocalDateTime toDate = Formater.getLocalDateTimeFromString(scanner.nextLine());
        System.out.println(r.calculateRelativeBalance(accountID, fromDate, toDate));
        System.out.println(r.numofTranscations(accountID, fromDate, toDate));
        System.exit(0);
		
	}
	
	public static List<Transaction> convertToTransactions(Stream<String> transactions) {
		List<Transaction> transactionList = new ArrayList<>();
		transactions.forEach(transaction ->  transactionList.add(new Transaction(transaction.split(","))));
		return transactionList;
	
	}

}
