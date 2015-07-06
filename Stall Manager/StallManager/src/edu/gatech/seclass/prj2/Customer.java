package edu.gatech.seclass.prj2;

import java.util.List;

import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.EmailService;
import edu.gatech.seclass.services.PaymentService;


public class Customer {
  public static final String ERROR = "Error";
  public static final String GOLD = "Gold";
  public static final int MIN_REWARD_THRESHOLD = 100;
  public static final int MIN_AMOUNT_GOLD = 1000;

  private String emailAddress;  
  private String firstName;
  private String lastName;
  private String memberStatus;
  private int zipCode;
  private double totalDollarPurchaseThisYear;
  private double currentCredit;
  
  public String toString() {
    return emailAddress + "\t" + firstName + "\t" + lastName + "\t" + memberStatus + "\t" + zipCode + "\t" +  totalDollarPurchaseThisYear + "\t" + currentCredit;    
  }
  
  public static Customer constructCustomer(String tokens[]) {
    String emailAddress = tokens[0];
    String firstName = tokens[1];
    String lastName = tokens[2];
    String memberStatus = tokens[3];
    int zipCode = Integer.valueOf(tokens[4]);
    double totalDollarPurchaseThisYear = Double.valueOf(tokens[5]);
    double currentCredit = Double.valueOf(tokens[6]);
    Customer toRet = new Customer(firstName, lastName,emailAddress, zipCode);
    toRet.memberStatus = memberStatus;
    toRet.totalDollarPurchaseThisYear = totalDollarPurchaseThisYear;
    toRet.currentCredit = currentCredit;
    return  toRet;
  }
  
  public Customer(String firstName, String lastName, String emailAddress, int zipCode) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.zipCode = zipCode;
    totalDollarPurchaseThisYear = 0;
    currentCredit = 0;
    this.memberStatus = "";

  }
  
  public String purchase(String date, double amount, double cashCredit, Manager.PaymentMethod method, CreditCard card) {
    if (currentCredit >= cashCredit) {//check whether the cashCredit can be applied
      boolean chargable = true;
      double discountedAmount = amount;
      double remainder = discountedAmount;
      //Check to reset totalDollarPurchaseThisYear
      //get the year of the latest transaction for this customer
      TransactionHistory hist = Manager.getInstance().getTransactions().get(emailAddress);
      List<Transaction> transactionList = null;
      if (hist != null)
        transactionList = hist.getTransactions();
      int preTransYear = (transactionList == null || transactionList.size() == 0 ? 0 : Integer.valueOf(transactionList.get(transactionList.size() -1).getDate().substring(6)));
      //get year for this transaction
      int thisYear = Integer.valueOf(date.substring(6));
      //if the diff is at least a year, then reset amount purchased
      if (thisYear - preTransYear >= 1) {
        totalDollarPurchaseThisYear = 0;
      }
      
      //Step 1: Try to check for membership discount
      if (GOLD.equals(memberStatus)) {
        discountedAmount = 0.95 * amount;
      }
      //Step 2: Apply cashCredit to the purchase
      if (cashCredit >= discountedAmount) { //Apply cashCredt, and customer don't have to pay anything
        remainder = 0;       
      } else {
        remainder = discountedAmount - cashCredit;        
      }  
      //Step 3: Try to charge credit card with remainder amount
      if (remainder > 0) {
        if (method == Manager.PaymentMethod.CREDITCARDSWIPE) {
          String result = CreditCardService.getCardInfo();
          chargable = !result.equals(ERROR);
          card = new CreditCard("", "", "", "", "");
          if (chargable) {
            String[] tokens = result.split("#");
            if (tokens.length >= 5) {
              card = new CreditCard(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            } else {
              card = new CreditCard("N/A", "N/A", "N/A", "N/A", "N/A");
            }                
          }
        } else {
          chargable = PaymentService.processPayment(card.getFirstName(), card.getLastName(), card.getAccountNumber(),card.getExpirationDate(), card.getSecurityCode(), discountedAmount);
        }
      }
      //Step 4: If it is chargable, then commit the transaction
      if (chargable) {      
        //Step 4.1: create a transaction, add it to transaction history in manager object, and update transactionMap
        hist = Manager.getInstance().getTransactions().get(emailAddress);
        int idx = 0;
        if (hist != null) {
          idx = hist.getTransactions().size();
        }
        String transId = emailAddress + "-" + String.valueOf(idx);
        Transaction trans = new Transaction(emailAddress, transId, date, amount, discountedAmount - remainder, amount - discountedAmount, card);
        Manager.getInstance().getTransactions().get(emailAddress).addTransaction(trans);
        //Manager.getInstance().getTransactionMap().put(transId, trans);
        //Step 4.2: update reward of customer
        //update cashCredit        
        currentCredit = currentCredit - (discountedAmount - remainder);
        currentCredit = currentCredit + (remainder >= MIN_REWARD_THRESHOLD ? 10 : 0);        
        //update memberStatus 
        totalDollarPurchaseThisYear += remainder;
        if (!GOLD.equals(memberStatus) && totalDollarPurchaseThisYear >= MIN_AMOUNT_GOLD) {
          memberStatus = GOLD;
        }
        
        //Send out email
        //We should not do this in real life, but since this is just a simulation, we don't care
        while (!EmailService.sendEmail(emailAddress, "Congratulation " + firstName, "You have been upgraded to GoldStatus. All your future purchases will have 5% discount")) {};
      } else {
        return "ERROR: Fail to charge";
      }
    }
    return Manager.SUCCESS;
  }
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getMemberStatus() {
    return memberStatus;
  }

  public void setMemberStatus(String memberStatus) {
    this.memberStatus = memberStatus;
  }
  
  public double getCurrentCredit(){
	  return currentCredit;
  }
  
  public void setCurrentCredit(double cc) {
	  this.currentCredit = cc;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  public double getTotalDollarPurchaseThisYear() {
    return totalDollarPurchaseThisYear;
  }

  public void setTotalDollarPurchaseThisYear(double totalDollarPurchaseThisYear) {
    this.totalDollarPurchaseThisYear = totalDollarPurchaseThisYear;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
}
