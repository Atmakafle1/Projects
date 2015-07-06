package edu.gatech.seclass.prj2;

import java.util.regex.Pattern;
import edu.gatech.seclass.prj2.Manager;
import edu.gatech.seclass.prj2.R;
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
 
public class EditCustomerFragment extends Fragment implements OnClickListener {

	View view;
	Button buttonSearch, buttonSave, buttonCan;
	EditText etSearch, etFirstName, etLastName, etZipCode, etEmail;
	
	//Regular Expression to match the US Zip-Code
	static final String regex = "^\\d{5}(-\\d{4})?$";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        buttonSearch = (Button) view.findViewById(R.id.buttonESearch);
        buttonSearch.setOnClickListener(this);
        buttonSave = (Button) view.findViewById(R.id.buttonESave);
        buttonSave.setOnClickListener(this);
        buttonSave.setEnabled(false);
        buttonCan = (Button) view.findViewById(R.id.buttonECancel);
        buttonCan.setOnClickListener(this);
        etSearch = (EditText) view.findViewById(R.id.editTextESearch);
        etFirstName = (EditText) view.findViewById(R.id.editTextEFirstName);
        etLastName = (EditText) view.findViewById(R.id.editTextELastName);
        etZipCode = (EditText) view.findViewById(R.id.editTextEZipCode);
        etEmail = (EditText) view.findViewById(R.id.editTextEEmailAddress);
        
        etSearch.requestFocus();
         
        return view;
    }
    
    @Override
    public void onClick(View v) {
    	switch(v.getId()){
        case R.id.buttonESearch:
            SearchCustomer();
        break;
        case R.id.buttonESave:
             SaveCustomer();
        break;
        case R.id.buttonECancel:
             CancelCustomer();
        break;
    	}
    }

    public void SearchCustomer() {
    	String email = etSearch.getText().toString();
    	
    	if (email.isEmpty() || !isValidEmail(email)) {
    		ShowMessage("Please enter a valid email address.");
    	}
    	else {
	    	Customer cust = Manager.getInstance().getCustomer(email);
	    	
	    	String first, last, zip;
	    	if (cust != null) {
		    	first = cust.getFirstName();
		    	last = cust.getLastName();
		    	zip = Integer.toString(cust.getZipCode());
		    	if (!first.isEmpty() || !last.isEmpty() || !zip.isEmpty() ) {
		    		etFirstName.setText(first);
		    		etLastName.setText(last);
			    	etEmail.setText(email);
			    	etZipCode.setText(zip);
			    	
			    	buttonSearch.setEnabled(false);
			    	buttonSave.setEnabled(true);
		    	}
		    	else {
		    		ShowMessage("The customer that you are searching for does not exist.");
		    	}
	    	}
	    	else {
	    		ShowMessage("The customer that you are searching for does not exist.");
	    	}
    	}
    }
     
    public void SaveCustomer() {
    	String firstName = etFirstName.getText().toString();
    	String lastName = etLastName.getText().toString();
    	String zipCode = etZipCode.getText().toString();
    	String email = etEmail.getText().toString();
    	
    	if ( firstName.isEmpty() || !(firstName.trim().length() > 0) || firstName.contains("-") )
    	{
    		ShowMessage("Please enter a valid first name");
    	}
    	else if (lastName.isEmpty() || !(lastName.trim().length() > 0) || lastName.contains("-") )
    	{
    		ShowMessage("Please enter a valid last name");
    	}
    	else if (!isAValidZipCode(zipCode) ) //|| (!zipCode.contains("-") && zipCode.length() != 10))
    	{
    		ShowMessage("Please enter a valid zip code.");
    	}
    	else if (email.isEmpty() || !isValidEmail(email)) {
    		ShowMessage("Please enter a valid email address.");
    	}
    	else {
	    	Customer cust = Manager.getInstance().getCustomer(email);
	    	cust.setFirstName(firstName);
	    	cust.setLastName(lastName);
	    	cust.setZipCode(Integer.parseInt(zipCode));
	    	String retCode = Manager.getInstance().updateCustomer(this.getActivity(), cust);
	    	
	    	if (retCode.toUpperCase().contains("ERROR")) {
	    		ShowMessage(retCode);
	    	}
	    	else {
	    		//ShowMessage("Customer has been updated successfully.");
	    		Toast.makeText(view.getContext(), "Customer has been updated successfully.",
	    				   Toast.LENGTH_LONG).show();
	    	}
    	}
    	buttonSearch.setEnabled(true);
    	buttonSave.setEnabled(false);
    }
    
    public void CancelCustomer() {
    	etSearch.setText("");
    	etFirstName.setText("");
    	etLastName.setText("");
    	etZipCode.setText("");
    	etEmail.setText("");
    	
    	buttonSearch.setEnabled(true);
    	buttonSave.setEnabled(false);
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
    
    public static boolean isAValidZipCode(String zip) {
        return Pattern.matches(regex, zip);
    }
}