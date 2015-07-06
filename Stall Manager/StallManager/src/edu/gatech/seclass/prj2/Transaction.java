package edu.gatech.seclass.prj2;

public class Transaction {
  private String custID;
  private String transID;
	private String date;
	private double amount;
	private double rewardAmountUsed;
	private double amountDiscountedByGold;	
	private CreditCard card;
	
	
	public static Transaction constructTransaction(String[] tokens) {
	  String custID = tokens[1];
	  String transID = tokens[2];
	  String date = tokens[3];
	  double amount = Double.valueOf(tokens[4]);
	  double rewardAmountUsed = Double.valueOf(tokens[5]);
	  double amountDiscountedByGold = Double.valueOf(tokens[6]);
	  String firstName = tokens[7];
	  String lastName = tokens[8];
	  String accountNumber = tokens[9];
	  String expirationDate = tokens[10];
	  String securityCode = tokens[11];
	  CreditCard card = new CreditCard(firstName, lastName, accountNumber, expirationDate, securityCode);
	  Transaction trans = new Transaction(custID, transID, date, amount, rewardAmountUsed, amountDiscountedByGold, card);
	  return trans;
	}
	
	public String toString() {
	  return custID + "\t" + transID + "\t" + date + "\t" + amount + "\t" + rewardAmountUsed + "\t" + amountDiscountedByGold + "\t" + card.getFirstName() 
	      + "\t" + card.getLastName() + "\t" + card.getAccountNumber() + "\t" + card.getExpirationDate() + "\t" + card.getSecurityCode();  
	}
	
  public Transaction(String custID, String transID, String date, double amount, double rewardAmountUsed,
      double amountDiscountedByGold, CreditCard card) {
    this.custID = custID;
    this.transID = transID;
    this.date = date;
    this.amount = amount;
    this.rewardAmountUsed = rewardAmountUsed;
    this.amountDiscountedByGold = amountDiscountedByGold;
    this.card = card;
  }
  
  public String getCustID() {
    return custID;
  }

  public void setCustID(String custID) {
    this.custID = custID;
  }

  public String getTransID() {
    return transID;
  }

  public void setTransID(String transID) {
    this.transID = transID;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getRewardAmountUsed() {
    return rewardAmountUsed;
  }

  public void setRewardAmountUsed(double rewardAmountUsed) {
    this.rewardAmountUsed = rewardAmountUsed;
  }

  public double getAmountDiscountedByGold() {
    return amountDiscountedByGold;
  }

  public void setAmountDiscountedByGold(double amountDiscountedByGold) {
    this.amountDiscountedByGold = amountDiscountedByGold;
  }

  public CreditCard getCard() {
    return card;
  }

  public void setCard(CreditCard card) {
    this.card = card;
  }
	
}
