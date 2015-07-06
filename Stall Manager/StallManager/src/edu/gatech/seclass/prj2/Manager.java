package edu.gatech.seclass.prj2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class Manager {
  private static final Manager INSTANCE = new Manager();
  //mapping from custID to Customer
  private HashMap<String, Customer> customers = new HashMap<String, Customer>();
  //mapping from custID to TransactionHistory
  private HashMap<String, TransactionHistory> transactions = new HashMap<String, TransactionHistory>();
  
  public static final String CUSTOMER_FILE = "customers.tsv";
  public static final String TRANSACTION_FILE = "transactions.tsv";
  public static final String SUCCESS = "SUCCESS";
  
  enum PaymentMethod { CREDITCARDSWIPE, THIRDPARTY};
  
  private Manager() {
  };
  
  public static Manager getInstance() {
    return INSTANCE;
  }
  
  public HashMap<String, Customer> getCustomers() {
    return customers;
  }
  
  public HashMap<String, TransactionHistory> getTransactions() {
    return transactions;
  }
    
  public Transaction getTransaction(String transID) {
    String[] tokens = transID.split("-");
    String custId = tokens[0];
    int transIdx = Integer.valueOf(tokens[1]);
    return transactions.get(custId).getTransactions().get(transIdx);
  }
  
  public Customer getCustomer(String emailAddress) {
    return customers.get(emailAddress);
  }
    
  public TransactionHistory getTransactionHistory(String emailAddress) {
    return transactions.get(emailAddress);
  }
    
  public boolean writeCustomerToDisk(FragmentActivity fa, String customerFile) {
    HashMap<String, Customer> customers = Manager.getInstance().getCustomers();
    HashMap<String, TransactionHistory> transactions = Manager.getInstance().getTransactions();
    StringBuffer toWrite = new StringBuffer();
    for (String key : customers.keySet()) {
      toWrite.append(customers.get(key).toString() + "\n");
    }

    try {
      OutputStreamWriter osw = new OutputStreamWriter(fa.openFileOutput(customerFile, Context.MODE_PRIVATE));
      osw.write(toWrite.toString());
      osw.close();
    } catch (IOException e) {
      return false;
    }
    return true;
  }
    
  public boolean writeTransactionsToDisk(FragmentActivity fa, String transactionFile) {
    HashMap<String, TransactionHistory> transactions = Manager.getInstance().getTransactions();

    StringBuffer toWrite = new StringBuffer();
    for (String key : transactions.keySet()) {
      TransactionHistory transHist = transactions.get(key);
      for (Transaction trans : transHist.getTransactions()) {
        toWrite.append(key + "\t" + trans + "\n");
      }
    }

    try {
      OutputStreamWriter osw = new OutputStreamWriter(fa.openFileOutput(transactionFile, Context.MODE_PRIVATE));
      osw.write(toWrite.toString());
      osw.close();
    } catch (IOException e) {
      return false;
    }
    return true;
  }
  
  public String addCustomer(FragmentActivity fa, Customer customer) {
    HashMap<String, Customer> customers = Manager.getInstance().getCustomers();
    HashMap<String, TransactionHistory> transactions = Manager.getInstance().getTransactions();
    if (!customers.containsKey(customer.getEmailAddress())) {
      customers.put(customer.getEmailAddress(), customer);
      transactions.put(customer.getEmailAddress(),
          new TransactionHistory(new ArrayList<Transaction>(), customer.getEmailAddress()));
      if (writeCustomerToDisk(fa, Manager.CUSTOMER_FILE)) {
        return Manager.SUCCESS;
      } else {
        return "ERROR: Could not save file to disk";
      }
    } else {
      return "ERROR: Customer email address already exist in the database";
    }
  }
  
  public String updateCustomer(FragmentActivity fa, Customer cust) {
    //right now the system does not support changing email address
    //TODO: implement update email address
    HashMap<String, Customer> customers = Manager.getInstance().getCustomers();
    if (customers.containsKey(cust.getEmailAddress())) {
      Customer updatedCust = customers.get(cust.getEmailAddress());
      updatedCust.setFirstName(cust.getFirstName());
      updatedCust.setLastName(cust.getLastName());
      updatedCust.setZipCode(cust.getZipCode());
      if (writeCustomerToDisk(fa, Manager.CUSTOMER_FILE)) {
        return Manager.SUCCESS;
      } 
      else {
        return "ERROR: Could not save file to disk";
      }
    } 
    else {
      return "ERROR: Could find customer email address";
    }
  }


  public boolean writeToDisk(FragmentActivity fa, String customerFile, String transactionFile) {    
    if (!writeCustomerToDisk(fa, customerFile) || !writeTransactionsToDisk(fa, transactionFile)) {
      return false;
    } else {
      return true;
    }
  }
  
  public boolean fileExistance(FragmentActivity fa, String fname){
    File file = fa.getBaseContext().getFileStreamPath(fname);
    return file.exists();
  }
  
  public boolean loadFromDisk(FragmentActivity fa, String customerFile, String transactionsFile) throws IOException {
    HashMap<String, Customer> customers = Manager.getInstance().getCustomers();
    HashMap<String, TransactionHistory> transactions = Manager.getInstance().getTransactions();

    try {

      //File f = new File(customerFile);
      if (!fileExistance(fa, customerFile)) {
        FileOutputStream fos = fa.openFileOutput(customerFile, Context.MODE_PRIVATE);
        fos.write("".getBytes());
        fos.close();
      }
      
      InputStream is = fa.openFileInput(customerFile);
      if (is != null) {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
          if (!line.equals("")) {            
            String[] tokens = line.split("\t");
            String id = tokens[0];
            Customer customer = Customer.constructCustomer(tokens);
            customers.put(id, customer);
          }
        }
      }
      is.close();
      
      //f = new File(transactionsFile);
      if (!fileExistance(fa, transactionsFile)){
        FileOutputStream fos = fa.openFileOutput(transactionsFile, Context.MODE_PRIVATE);
        fos.write("".getBytes());
        fos.close();
      }
      is = fa.openFileInput(transactionsFile);
      if (is != null) {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
          if (!line.equals("")) {            
            String[] tokens = line.split("\t");
            Transaction trans = Transaction.constructTransaction(tokens);
            if (transactions.containsKey(tokens[0])) {
              transactions.get(tokens[0]).addTransaction(trans);
            } else {
              ArrayList<Transaction> tmpList = new ArrayList<Transaction>();
              tmpList.add(trans);
              transactions.put(tokens[0], new TransactionHistory(tmpList, tokens[0]));
            }
          }
        }
      }
      
    } catch (IOException e) {
      e.printStackTrace();      
      return false;
    }
    return true;
  }

  public String init(FragmentActivity fa) throws IOException {
    HashMap<String, Customer> customers = Manager.getInstance().getCustomers();
    HashMap<String, TransactionHistory> transactions = Manager.getInstance().getTransactions();
    if (!loadFromDisk(fa, Manager.CUSTOMER_FILE, Manager.TRANSACTION_FILE)) {
      return "ERROR: Failed to load data from disk";
    }
    
    for (String custID: customers.keySet()) {
      if (transactions.get(custID) == null) {
        transactions.put(custID, new TransactionHistory(new ArrayList<Transaction>(), custID));
      }
    }
    return Manager.SUCCESS;
  }

  
  public String checkout(FragmentActivity fa, String emailAddress, double amount, double cashCredit, PaymentMethod method, CreditCard card) {
    HashMap<String, Customer> customers = Manager.getInstance().getCustomers();

    Customer customer = customers.get(emailAddress);
    String result;
    String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
    result = customer.purchase(date, amount, cashCredit, method, card);
    if (!result.equals(Manager.SUCCESS)) {
      return result;
    }
    if (writeTransactionsToDisk(fa, Manager.TRANSACTION_FILE) && writeCustomerToDisk(fa, Manager.CUSTOMER_FILE)) {
      // right now, if this fail, then memory and disk are out of sync
      // customer has been charged in memory, but not write to disk
      // TODO: Implement roll back
      return Manager.SUCCESS;
    } else {
      return "ERROR: Fail to write to disk";
    }
  }

}
