package edu.gatech.seclass.prj2;

public class CreditCard {

  private String firstName;
  private String lastName;
  private String type;
  private String accountNumber;
  private String expirationDate;
  private String securityCode;
  
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

  public String getType() {
	    return type;
  }

  public void setType(String type) {
	    this.accountNumber = type;
  }
  
  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getSecurityCode() {
    return securityCode;
  }

  public void setSecurityCode(String securityCode) {
    this.securityCode = securityCode;
  }

  public CreditCard(String firstName, String lastName, String accountNumber, String expirationDate, String securityCode) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.accountNumber = accountNumber;
    this.expirationDate = expirationDate;
    this.securityCode = securityCode;
  }
  
  public String toString() {
    return "Name: " + firstName + " " + lastName + "\nCredit Card Number: " + accountNumber + "\nExpiration Date: " + expirationDate + "\nSecurity Code: " + securityCode;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
}
