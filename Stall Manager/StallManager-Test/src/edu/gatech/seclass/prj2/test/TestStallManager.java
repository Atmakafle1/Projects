package edu.gatech.seclass.prj2.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import edu.gatech.seclass.prj2.MainActivity;

public class TestStallManager extends
    ActivityInstrumentationTestCase2<MainActivity> {

  private Solo solo;

  public TestStallManager() {
    super(MainActivity.class);
  }

  public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
  @Override
  public void tearDown() throws Exception {
    solo.finishOpenedActivities();
  }
  //Add Customer tab
  public void testAddCustomerBadFirstName() throws Exception {
	    // check that we have the right activity
	    solo.assertCurrentActivity("Add Customer", MainActivity.class);
	    
	    // make sure it is on Add Customer tab
	    solo.clickOnText("Add Customer");
	    
	    // add text to text field
	    //EditText FirstNameText = (EditText) solo.getView();
	    solo.enterText(0, "Ma-ry");
	    solo.enterText(1, "Smith");
	    solo.enterText(2, "marysmith@gmail.com");
	    solo.enterText(3, "12345");
	    
	    solo.clickOnButton("Add");
	    
	    solo.waitForText("Please enter a valid first name");
	    solo.clickOnButton("Okay");
  }
//Add Customer tab
  public void testAddCustomerBadLastName() throws Exception {
	    // check that we have the right activity
	    solo.assertCurrentActivity("Add Customer", MainActivity.class);
	    
	    // make sure it is on Add Customer tab
	    solo.clickOnText("Add Customer");
	    
	    // add text to text field
	    //EditText FirstNameText = (EditText) solo.getView();
	    solo.enterText(0, "Mary");
	    solo.enterText(1, "Sm-ith");
	    solo.enterText(2, "marysmith@gmail.com");
	    solo.enterText(3, "12345");
	    
	    solo.clickOnButton("Add");
	    
	    solo.waitForText("Please enter a valid last name");
	    solo.clickOnButton("Okay");
}
//Add Customer tab 
  public void testAddCustomerBadEmail() throws Exception {
	    // check that we have the right activity
	    solo.assertCurrentActivity("Add Customer", MainActivity.class);
	    
	    // make sure it is on Add Customer tab
	    solo.clickOnText("Add Customer");
	    
	    // add text to text field
	    //EditText FirstNameText = (EditText) solo.getView();
	    solo.enterText(0, "Mary");
	    solo.enterText(1, "Smith");
	    solo.enterText(2, "marysmith");
	    solo.enterText(3, "12345");
	    
	    solo.clickOnButton("Add");
	    
	    solo.waitForText("Please enter a valid email address");
	    solo.clickOnButton("Okay");
  }
//Add Customer tab
  public void testAddCustomerBadZip() throws Exception {
	    // check that we have the right activity
	    solo.assertCurrentActivity("Add Customer", MainActivity.class);
	    
	    // make sure it is on Add Customer tab
	    solo.clickOnText("Add Customer");
	    
	    // add text to text field
	    //EditText FirstNameText = (EditText) solo.getView();
	    solo.enterText(0, "Mary");
	    solo.enterText(1, "Smith");
	    solo.enterText(2, "marysmith@gmail.com");
	    solo.enterText(3, "12345234");
	    
	    solo.clickOnButton("Add");
	    
	    solo.waitForText("Please enter a valid zip code");
	    solo.clickOnButton("Okay");
  }
//Add Customer tab
  public void testAddCancel() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Add Customer", MainActivity.class);
    
    // make sure it is on Add Customer tab
    solo.clickOnText("Add Customer");
    
    // add text to text field
    //EditText FirstNameText = (EditText) solo.getView();
    solo.enterText(0, "Mary");
    solo.enterText(1, "Smith");
    solo.enterText(2, "marysmith@gmail.com");
    solo.enterText(3, "12345");
    solo.clickOnText("Cancel");
   //checks to see if fields are empty
    assertTrue("Is not Empty", solo.getEditText(0).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(1).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(2).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(3).getText().toString().equals(""));
	}
//Add Customer tab
public void testAddCustomer() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Add Customer", MainActivity.class);
    
    // make sure it is on Add Customer tab
    solo.clickOnText("Add Customer");
    
    // add text to text field
    //EditText FirstNameText = (EditText) solo.getView();
    solo.enterText(0, "Mary");
    solo.enterText(1, "Smith");
    solo.enterText(2, "marysmith@gmail.com");
    solo.enterText(3, "12345");
    
    solo.clickOnButton("Add");
    solo.waitForText("Customer added successfully.");
}
public void testExistingEmailAddCustomer() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Add Customer", MainActivity.class);
    
    // make sure it is on Add Customer tab
    solo.clickOnText("Add Customer");
    
    // add text to text field
    //EditText FirstNameText = (EditText) solo.getView();
    solo.enterText(0, "John");
    solo.enterText(1, "Doe");
    solo.enterText(2, "johndoe1@gmail.com");
    solo.enterText(3, "12345");
    solo.clickOnButton("Add");
    solo.clearEditText(0);
    solo.clearEditText(1);
    solo.clearEditText(2);
    solo.clearEditText(3);
    solo.enterText(0, "John");
    solo.enterText(1, "Doe");
    solo.enterText(2, "johndoe1@gmail.com");
    solo.enterText(3, "12345");
    solo.clickOnButton("Add");
    solo.waitForText("ERROR: Customer email address already exist in the database");
    
}


//Edit Customer Tab
public void testEditCustomerEmailSearch() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
    //valid email
    solo.enterText(4, "marysmith@gmail.com");
   
    solo.clickOnButton("Search");
   //checks to see if fields are empty
    assertFalse("Is not Empty", solo.getEditText(5).getText().toString().isEmpty());
    assertFalse("Is not Empty", solo.getEditText(6).getText().toString().isEmpty());
    assertFalse("Is not Empty", solo.getEditText(7).getText().toString().isEmpty());
    assertFalse("Is not Empty", solo.getEditText(8).getText().toString().isEmpty());
	}
//Edit Customer Tab
public void testEditCustomerCancel() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    Button cancelBtn = (Button) solo.getCurrentActivity().findViewById(edu.gatech.seclass.prj2.R.id.buttonECancel);
    solo.clickOnView(cancelBtn);
   //checks to see if fields are empty
    assertTrue("Is not Empty", solo.getEditText(4).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(5).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(6).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(7).getText().toString().equals(""));
    assertTrue("Is not Empty", solo.getEditText(8).getText().toString().equals(""));
	}
//Edit Customer Tab
public void validEditCustomer() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    solo.clearEditText(5);
    solo.clearEditText(6);
    solo.clearEditText(8);
    solo.enterText(5, "Marys");
    solo.enterText(6, "Sith"); 
    solo.enterText(8, "01234"); 
    solo.clickOnButton("Save");
    solo.waitForText("Customer has been updated successfully.");
   //checks to see if fields are empty
	}
//Edit Customer tab
public void testInvalidEditCustomerFName() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    solo.clearEditText(5);
    solo.enterText(5, "Mar-ys"); 
    solo.clickOnButton("Save");
    solo.waitForText("Please enter a valid first name");
   //checks to see if fields are empty
	}
//Edit Customer tab
public void testInvalidEditCustomerLName() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    solo.clearEditText(6);
    solo.enterText(6, "Smi-th"); 
    solo.clickOnButton("Save");
    solo.waitForText("Please enter a valid last name");
   //checks to see if fields are empty
	}
//Edit Customer tab
public void testInvalidEditCustomerEAddress() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    solo.clearEditText(7);
    solo.enterText(7, "marysmith"); 
    solo.clickOnButton("Save");
    solo.waitForText("Please enter a valid email address");
   
	}
//Edit Customer tab
public void testInvalidEditCustomerZipCode() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    solo.clearEditText(8);
    solo.enterText(8, "12"); 
    solo.clickOnButton("Save");
    solo.waitForText("Please enter a valid zip code");
 
	}
//Edit Customer tab
public void testMultipleInvalidEditCustomer() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    
    // make sure it is on Edit Customer tab
    solo.clickOnText("Edit Customer");
  
    solo.enterText(4, "marysmith@gmail.com");
    solo.clickOnButton("Search");
    solo.clearEditText(5);
    solo.clearEditText(6);
    solo.enterText(5,"Mar-y");
    solo.enterText(6, "Smit-h"); 
    solo.clickOnButton("Save");
    solo.waitForText("Please enter a valid first name");
 
	}

//Checkout tab
public void testValidCheckoutSwipe() throws Exception {
	//scrolls to edit customer tab
	solo.assertCurrentActivity("Edit Customer", MainActivity.class);
  solo.clickOnText("Edit Customer");
  
  //scrolls to Checkout tab
  solo.assertCurrentActivity("Checkout", MainActivity.class);
  solo.clickOnText("Checkout");
  
  //finds text field by id and enters text
  EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
  solo.enterText(emailAdd,"marysmith@gmail.com");
  
  //finds the swipe button and click it
  Button swipeBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCSwipe);
  solo.clickOnView(swipeBtn);
  
  EditText total = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCTotalBefore);
  solo.enterText(total,"350");
  
  //expected dialogue alert
  solo.waitForText("ERROR: Fail to charge", 1, 2000, true);
  solo.waitForText("The Purchase has completed successfully.");
	}
//Checkout tab
public void testValidCheckoutManual() throws Exception {
	//scrolls to edit customer tab
	solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    solo.clickOnText("Edit Customer");
    
    //scrolls to Checkout tab
    solo.assertCurrentActivity("Checkout", MainActivity.class);
    solo.clickOnText("Checkout");
    
    //finds text field by id and enters text
    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
    solo.enterText(emailAdd,"marysmith@gmail.com");
   
    EditText total = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCTotalBefore);
    solo.enterText(total,"450");
 
    EditText cCNumber = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCCreditCardNum);
    solo.enterText(cCNumber,"1234567890123456");
    
    EditText securityCode = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSecCode);
    solo.enterText(securityCode,"123");
    
    EditText expDate = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCExpDate);
    solo.enterText(expDate,"1/16");
    
    //finds the swipe button and click it
    Button manualBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCManual);
    solo.clickOnView(manualBtn);
    
    //expected dialogue alert
    solo.waitForText("ERROR: Fail to charge", 1, 2000, true);
    solo.waitForText("The Purchase has completed successfully.");
	}
public void testInvalidCheckoutManual() throws Exception {
	//scrolls to edit customer tab
	solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    solo.clickOnText("Edit Customer");
    
    //scrolls to Checkout tab
    solo.assertCurrentActivity("Checkout", MainActivity.class);
    solo.clickOnText("Checkout");
    
    //finds text field by id and enters text
    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
    solo.enterText(emailAdd,"marysmith@gmail.com");
  
    EditText cCNumber = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCCreditCardNum);
    solo.enterText(cCNumber,"123456789012");
    
    EditText securityCode = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSecCode);
    solo.enterText(securityCode,"123");
    
    EditText expDate = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCExpDate);
    solo.enterText(expDate,"1/16");
    
    //finds the swipe button and click it
    Button manualBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCManual);
    solo.clickOnView(manualBtn);
    
    //expected dialogue alert
    solo.waitForText("Enter a value for total.");
	}
//checkout tab
public void testInvalidCheckoutEmail() throws Exception {
	//scrolls to edit customer tab
	solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    solo.clickOnText("Edit Customer");
    
    //scrolls to Checkout tab
    solo.assertCurrentActivity("Checkout", MainActivity.class);
    solo.clickOnText("Checkout");
    
    //finds text field by id and enters text
    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
    solo.enterText(emailAdd,"marysmithgmail");
   
    //finds the swipe button and click it
    Button swipeBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCSwipe);
    solo.clickOnView(swipeBtn);
    solo.waitForText("Please enter a vaild email address.");
   
    
	}
//checkout tab
public void testInvalidCheckoutExpDate() throws Exception {
	//scrolls to edit customer tab
		solo.assertCurrentActivity("Edit Customer", MainActivity.class);
	    solo.clickOnText("Edit Customer");
	    
	    //scrolls to Checkout tab
	    solo.assertCurrentActivity("Checkout", MainActivity.class);
	    solo.clickOnText("Checkout");
	    
	    //finds text field by id and enters text
	    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
	    solo.enterText(emailAdd,"marysmith@gmail.com");
	   
	    EditText total = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCTotalBefore);
	    solo.enterText(total,"450");
	 
	    EditText cCNumber = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCCreditCardNum);
	    solo.enterText(cCNumber,"123456789012");
	    
	    EditText securityCode = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSecCode);
	    solo.enterText(securityCode,"123");
	    
	    EditText expDate = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCExpDate);
	    solo.enterText(expDate,"1");
	    
	    //finds the swipe button and click it
	    Button manualBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCManual);
	    solo.clickOnView(manualBtn);
	    
	    //expected dialogue alert
	    solo.waitForText("Please enter a valid expiration date for the credit card.");
	    
	}
//checkout tab
public void testInvalidCheckoutCreditCard() throws Exception {
	//scrolls to edit customer tab
		solo.assertCurrentActivity("Edit Customer", MainActivity.class);
	    solo.clickOnText("Edit Customer");
	    
	    //scrolls to Checkout tab
	    solo.assertCurrentActivity("Checkout", MainActivity.class);
	    solo.clickOnText("Checkout");
	    
	    //finds text field by id and enters text
	    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
	    solo.enterText(emailAdd,"marysmith@gmail.com");
	   
	    EditText total = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCTotalBefore);
	    solo.enterText(total,"450");
	 
	    EditText cCNumber = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCCreditCardNum);
	    solo.enterText(cCNumber,"1234567812");
	    
	    EditText securityCode = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSecCode);
	    solo.enterText(securityCode,"123");
	    
	    EditText expDate = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCExpDate);
	    solo.enterText(expDate,"1");
	    
	    //finds the swipe button and click it
	    Button manualBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCManual);
	    solo.clickOnView(manualBtn);
	    
	    //expected dialogue alert
	    solo.waitForText("Please enter a valid credit card number.");
	    solo.clickOnButton("Okay");
	}
public void testCheckoutCancel() throws Exception {
	//scrolls to edit customer tab
		solo.assertCurrentActivity("Edit Customer", MainActivity.class);
	    solo.clickOnText("Edit Customer");
	    
	    //scrolls to Checkout tab
	    solo.assertCurrentActivity("Checkout", MainActivity.class);
	    solo.clickOnText("Checkout");
	    
	    //finds text field by id and enters text
	    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
	    solo.enterText(emailAdd,"marysmith@gmail.com");
	   
	    EditText total = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCTotalBefore);
	    solo.enterText(total,"450");
	 
	    EditText cCNumber = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCCreditCardNum);
	    solo.enterText(cCNumber,"1234567812");
	    
	    EditText securityCode = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSecCode);
	    solo.enterText(securityCode,"123");
	    
	    EditText expDate = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCExpDate);
	    solo.enterText(expDate,"1");
	    
	    //finds the swipe button and click it
	    Button cancelBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCCancel);
	    solo.clickOnView(cancelBtn);
	    
	    assertTrue("Is not Empty",total.getText().toString().isEmpty());   
	    assertTrue("Is not Empty",cCNumber.getText().toString().isEmpty()); 
	    assertTrue("Is not Empty",securityCode.getText().toString().isEmpty()); 
	    assertTrue("Is not Empty",expDate.getText().toString().isEmpty()); 
	    
	}
//View Transaction Tab
public void testValidViewTransaction() throws Exception {
		//scrolls to edit customer tab
		solo.assertCurrentActivity("Edit Customer", MainActivity.class);
	    solo.clickOnText("Edit Customer");
	    //scrolls to Checkout tab and makes a purchase
	    solo.assertCurrentActivity("Checkout", MainActivity.class);
	    solo.clickOnText("Checkout");
	    EditText emailAdd = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSearch);
	    solo.enterText(emailAdd,"marysmith@gmail.com");	  
	    EditText total = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCTotalBefore);
	    solo.enterText(total,"100");	 
	    EditText cCNumber = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCCreditCardNum);
	    solo.enterText(cCNumber,"1234567890123456");	    
	    EditText securityCode = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCSecCode);
	    solo.enterText(securityCode,"123");	    
	    EditText expDate = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextCExpDate);
	    solo.enterText(expDate,"1/16");
	    //finds the swipe button and click it
	    Button manBtn = (Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonCManual);
	    solo.clickOnView(manBtn);
	  //scrolls to View Transaction tab
	    solo.assertCurrentActivity("View Transaction", MainActivity.class);
	    solo.clickOnText("View Transaction");
	    EditText emailAdd2 = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVSearch);
	    solo.enterText(emailAdd2,"marysmith@gmail.com");
	    Button searchBtn =(Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonVSearch);
	    solo.clickOnView(searchBtn);
	    EditText total2 = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVTotal);
	    EditText  goldMemDis= (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVGoldDiscount);
	    EditText  rewardDis= (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVRewardDiscount);
	    EditText  cCNum2= (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVCreditCardNum);
	    assertFalse("Is not Empty",total2.getText().toString().isEmpty());   
	    assertFalse("Is not Empty",goldMemDis.getText().toString().isEmpty()); 
	    assertFalse("Is not Empty",rewardDis.getText().toString().isEmpty()); 
	    assertFalse("Is not Empty",cCNum2.getText().toString().isEmpty()); 
	   
	}
public void testValidViewTransactionClear() throws Exception {
	//scrolls to edit customer tab
	solo.assertCurrentActivity("Edit Customer", MainActivity.class);
    solo.clickOnText("Edit Customer"); 
  //scrolls to View Transaction tab
    solo.assertCurrentActivity("View Transaction", MainActivity.class);
    solo.clickOnText("View Transaction");
    //scrolls to Checkout tab 
    solo.assertCurrentActivity("Checkout", MainActivity.class);
    solo.clickOnText("Checkout");
  //scrolls to View Transaction tab
    solo.assertCurrentActivity("View Transaction", MainActivity.class);
    solo.clickOnText("View Transaction");
    
    EditText emailAdd2 = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVSearch);
    solo.enterText(emailAdd2,"marysmith@gmail.com");
    Button searchBtn =(Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonVSearch);
    solo.clickOnView(searchBtn);
    EditText total2 = (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVTotal);
    EditText  goldMemDis= (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVGoldDiscount);
    EditText  rewardDis= (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVRewardDiscount);
    EditText  cCNum2= (EditText) solo.getView(edu.gatech.seclass.prj2.R.id.editTextVCreditCardNum);
    Button clearBtn =(Button) solo.getView(edu.gatech.seclass.prj2.R.id.buttonVClear);
    solo.clickOnView(clearBtn);
    assertTrue("Is not Empty",total2.getText().toString().equals(""));   
    assertTrue("Is not Empty",goldMemDis.getText().toString().equals("")); 
    assertTrue("Is not Empty",rewardDis.getText().toString().equals("")); 
    assertTrue("Is not Empty",cCNum2.getText().toString().equals("")); 
   
	}
}