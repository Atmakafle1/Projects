package edu.gatech.seclass.prj2;

import java.util.List;

public class TransactionHistory {

  private List<Transaction> transactions;
  private String custID;
  
  public void addTransaction(Transaction trans) {
    transactions.add(trans);
  }
  
  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public String getCustID() {
    return custID;
  }

  public void setCustID(String custID) {
    this.custID = custID;
  }

  public TransactionHistory(List<Transaction> transactions, String custID) {
    super();
    this.transactions = transactions;
    this.custID = custID;
  }

}
