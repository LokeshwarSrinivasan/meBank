import java.time.LocalDateTime;

public class Transaction {
	   private String transactionID;
	   private String toAccountID;
	   private String fromAccountID;
	   private LocalDateTime createdAt;
	   private long amount;
	   private TransactionType transactionType;
	   private String relatedPayment;

	    public Transaction(String[] tran_data) {
	        this.transactionID = tran_data[0].trim();
	        this.fromAccountID =tran_data[1].trim();
	        this.toAccountID = tran_data[2].trim();
	        this.createdAt = Formater.getLocalDateTimeFromString(tran_data[3].trim());
	        this.amount = Formater.convertValuetoDoubleValue(tran_data[4].trim());
	        this.transactionType = TransactionType.valueOf(tran_data[5].trim());
	        if(this.transactionType.equals(TransactionType.REVERSAL)) {
	            this.relatedPayment = tran_data[6].trim();
	        }
	    }

	    public String getTransactionID() {
	        return transactionID;
	    }

	    public String getFromAccountID() {
	        return fromAccountID;
	    }

	    public String getToAccountID() {
	        return toAccountID;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public long getAmountInCents() {
	        return amount;
	    }

	    public TransactionType getTransactionType() {
	        return transactionType;
	    }

	    public String getRelatedPayment() {
	        return relatedPayment;
	    }
}
