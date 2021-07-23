import java.time.LocalDateTime;
import java.util.List;

public class RelativeAccountBalance {
	 private List<Transaction> transactionList;
	 
	 
    

	    public RelativeAccountBalance(List<Transaction> transactionList) {
	        this.transactionList = transactionList;
	    }

	    public List<Transaction> getTransactionList() {
	        return transactionList;
	    }


	    public String calculateRelativeBalance(String accountID, LocalDateTime fromDateTime, LocalDateTime toDateTime){
	    	long relativeBalance = 0;
	        long reversalBalance = 0;
	        for (Transaction transaction : transactionList){
	            if(transaction.getCreatedAt().isAfter(fromDateTime) && transaction.getCreatedAt().isBefore(toDateTime) && transaction.getTransactionType().equals(TransactionType.PAYMENT)){
	                if(transaction.getFromAccountID().equals(accountID)){
	                    relativeBalance -= transaction.getAmountInCents();
	                 
	                }else if(transaction.getToAccountID().equals(accountID)){
	                    relativeBalance += transaction.getAmountInCents();
	                    
	                }
	            }
	            if(transaction.getTransactionType().equals(TransactionType.REVERSAL)){
	                if(transaction.getFromAccountID().equals(accountID)){
	                    reversalBalance += transaction.getAmountInCents();
	                    
	                   
	                }else if(transaction.getToAccountID().equals(accountID)){
	                    reversalBalance -= transaction.getAmountInCents();
	                    
	                }
	            }
	        }
	        relativeBalance += reversalBalance;
	       
	        return relativeBalance<0?"Relative Balance -"+Formater.convertCentValueToDollar(relativeBalance):"Relative Balance - "+Formater.convertCentValueToDollar(relativeBalance);
	    }
	    
	    public String numofTranscations(String accountID, LocalDateTime fromDateTime, LocalDateTime toDateTime){
	    	 int transactionCounter = 0;
	    	for (Transaction transaction : transactionList){
	            if(transaction.getCreatedAt().isAfter(fromDateTime) && transaction.getCreatedAt().isBefore(toDateTime) && transaction.getTransactionType().equals(TransactionType.PAYMENT)){
	                if(transaction.getFromAccountID().equals(accountID)){
	                    transactionCounter++;
	                }else if(transaction.getToAccountID().equals(accountID)){
	                    transactionCounter++;
	                }
	            }
	            else if(transaction.getTransactionType().equals(TransactionType.REVERSAL)){
	                if(transaction.getFromAccountID().equals(accountID)){
	                    
	                    transactionCounter--;
	                }else if(transaction.getToAccountID().equals(accountID)){
	                    
	                    transactionCounter--;
	                }
	            }
	        }
	    	
	    	return "No of Transactions - " + transactionCounter;
	    }
}
