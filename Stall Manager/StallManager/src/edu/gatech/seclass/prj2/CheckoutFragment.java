package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.Manager.PaymentMethod;
import edu.gatech.seclass.prj2.R;
import edu.gatech.seclass.prj2.Transaction;
import edu.gatech.seclass.prj2.TransactionHistory;
import edu.gatech.seclass.prj2.CreditCard;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class CheckoutFragment extends Fragment implements OnClickListener {
 
	View view;
	Button buttonSearch, buttonSwipe, buttonManual, buttonCan;
	EditText etSearch, etTotalBefore, etCreditNum, etExpDate, etSecCode;
	Transaction trans;
	CreditCard creditCard;
	TransactionHistory transHist;
	String firstName, lastName, custEmail;
	double zipCode, reward;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        view = inflater.inflate(R.layout.fragment_checkout, container, false);
        buttonSwipe = (Button) view.findViewById(R.id.buttonCSwipe);
        buttonSwipe.setOnClickListener(this);
        buttonManual = (Button) view.findViewById(R.id.buttonCManual);
        buttonManual.setOnClickListener(this);
        buttonCan = (Button) view.findViewById(R.id.buttonCCancel);
        buttonCan.setOnClickListener(this);
        etSearch = (EditText) view.findViewById(R.id.editTextCSearch);
        etTotalBefore = (EditText) view.findViewById(R.id.editTextCTotalBefore);
        etCreditNum = (EditText) view.findViewById(R.id.editTextCCreditCardNum);
        etExpDate = (EditText) view.findViewById(R.id.editTextCExpDate);
        etSecCode = (EditText) view.findViewById(R.id.editTextCSecCode);
         
        return view;
    }
    
    @Override
    public void onClick(View v) {
    	
    	switch(v.getId()){
        case R.id.buttonCSwipe:
            SwipeCreditCard();
        break;
        case R.id.buttonCManual:
             EnterManual();
        break;
        case R.id.buttonCCancel:
             CancelCustomer();
        break;
    	}
    }
    
    public boolean SearchCustomer() {
    	String email = etSearch.getText().toString();
    	
    	if (email.isEmpty() || !isValidEmail(email)) {
    		ShowMessage("Please enter a valid email address.");
    		return false;
    	}
    	else {
	    	Customer cust = Manager.getInstance().getCustomer(email);
	    	
	    	if (cust != null) {
		    	String first, last, zip;
		    	first = cust.getFirstName();
		    	last = cust.getLastName();
		    	zip = Integer.toString(cust.getZipCode());
		    	if (!first.isEmpty() || !last.isEmpty() || !zip.isEmpty() ) {
			    	firstName = cust.getFirstName();
			    	lastName = cust.getLastName();
			    	zipCode = cust.getZipCode();
			    	custEmail = cust.getEmailAddress();
			    	reward = cust.getCurrentCredit();
			    	return true;
		    	}
		    	else {
		    		ShowMessage("The customer that you are searching for does not exist.");
		    		return false;
		    	}
	    	}
	    	else {
	    		ShowMessage("The customer that you are searching for does not exist.");
	    		return false;
	    	}
    	}
    }
    
    public void SwipeCreditCard() {
    	boolean foundCust = SearchCustomer();
    	
    	if (foundCust) {
    		String tempTotal = etTotalBefore.getText().toString();
    		if (!tempTotal.isEmpty()) {
		    	double total = Double.parseDouble(tempTotal);
		    	String ccNum = etCreditNum.getText().toString();
		    	String expDate = etExpDate.getText().toString();
		    	String secCode = etSecCode.getText().toString();
		    	
		    	//Do this for CeditCard Swipe
		    	if (ccNum.isEmpty() || expDate.isEmpty() || secCode.isEmpty()) {
		    		creditCard = new CreditCard("", "", "", "", "");    	
			    	String retCode = Manager.getInstance().checkout(this.getActivity(), custEmail, total, reward, PaymentMethod.CREDITCARDSWIPE, creditCard);
			    	if (retCode.toUpperCase().contains("ERROR")) {
			    		ShowMessage(retCode);
			    	}
			    	else {
			    		//ShowMessage("The Purchase has completed successfully.");
			    		Toast.makeText(view.getContext(), "The Purchase has completed successfully.",
			    				   Toast.LENGTH_LONG).show();
			    	}
		    	}
		    	else {
		    		ShowMessage("Did you want to hit Manual?");
		    	}
		    		
    		}
    		else {
    			ShowMessage("Enter a value for total.");
    		}
    	}
    }
    
    public void EnterManual() {
    	boolean foundCust = SearchCustomer();
    	
    	if (foundCust) {
    		String tempTotal = etTotalBefore.getText().toString();
    		if (!tempTotal.isEmpty()) {
		    	double total = Double.parseDouble(tempTotal);
		    	String ccNum = etCreditNum.getText().toString();
		    	String expDate = etExpDate.getText().toString();
		    	String secCode = etSecCode.getText().toString();
	    	
		    	//Do this for CeditCard Swipe
		    	if (!ccNum.isEmpty() || !expDate.isEmpty() || !secCode.isEmpty()) {
		    		if (ccNum.contains(" ")) {
		    			String tempNum = ccNum.replaceAll("(?<=\\d) +(?=\\d)", "");
		    			ccNum = tempNum;
		    		}
		    		
		    		if ( ccNum.length() != 16) {
		    			ShowMessage("Please enter a valid credit card number.");
		    		}
		    		else if (!expDate.contains("/")) {
			    		ShowMessage("Please enter a valid expiration date for the credit card.");
			    	}
			    	else if (secCode.length() != 3) {
			    		ShowMessage("Please enter a valid security code for the credit card.");
			    	}
			    	else {
				    	creditCard = new CreditCard(firstName, lastName, ccNum, expDate, secCode);    	
				    	String retCode = Manager.getInstance().checkout(this.getActivity(), custEmail, total, reward, PaymentMethod.THIRDPARTY, creditCard);
				    	if (retCode.toUpperCase().contains("ERROR")) {
				    		ShowMessage(retCode);
				    	} 
				    	else {
				    		//ShowMessage("The Purchase has completed successfully.");
				    		Toast.makeText(view.getContext(), "The Purchase has completed successfully.",
				    				   Toast.LENGTH_LONG).show();
				    	}
			    	}
		    	}
		    	else {
		    		ShowMessage("Did you want to hit Swipe?");
		    	}
    		}
    		else {
    			ShowMessage("Enter a value for total.");
    		}
    	}
    }
    
    public void CancelCustomer() {
    	etSearch.setText("");
    	etTotalBefore.setText("");
    	etCreditNum.setText("");
    	etExpDate.setText("");
    	etSecCode.setText("");
    }
    
    public void ShowMessage(String msg){ 
    	AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
    	builder.setMessage(msg);
    	builder.setCancelable(true);
    	builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			dialog.cancel();
    		}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
	}
    
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}