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
 
public class AddCustomerFragment extends Fragment implements OnClickListener {
 
	View view;
	Button buttonAdd, buttonCan;
	EditText etFirstName, etLastName, etZipCode, etEmail;
	
	//Regular Expression to match the US Zip-Code
	static final String regex = "^\\d{5}(-\\d{4})?$";
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        view = inflater.inflate(R.layout.fragment_add_customer, container, false);
        buttonAdd = (Button) view.findViewById(R.id.buttonAAdd);
        buttonAdd.setOnClickListener(this);
        buttonCan = (Button) view.findViewById(R.id.buttonACancel);
        buttonCan.setOnClickListener(this);
        etFirstName = (EditText) view.findViewById(R.id.editTextAFirstName);
        etLastName = (EditText) view.findViewById(R.id.editTextALastName);
        etZipCode = (EditText) view.findViewById(R.id.editTextAZipCode);
        etEmail = (EditText) view.findViewById(R.id.editTextAEmailAddress);
        
        etFirstName.requestFocus();
         
        return view;
    }
    
    @Override
    public void onClick(View v) {
    	
    	switch(v.getId()){
        case R.id.buttonAAdd:
             AddCustomer();
        break;
        case R.id.buttonACancel:
             CancelCustomer();
        break;
    	}
    }
        
    public void AddCustomer() {
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
    	else if (email.isEmpty() || !isValidEmail(email) )
    	{
    		ShowMessage("Please enter a valid email address.");
    	}
    	else
    	{
	    	//Change customer ID to an actual customer ID
	    	Customer cust = new Customer(firstName, lastName, email, Integer.parseInt(zipCode));
	    	String retCode = Manager.getInstance().addCustomer(this.getActivity(), cust);
	    	if (retCode.toUpperCase().contains("ERROR"))
	    	{
	    		ShowMessage(retCode);
	    	}
	    	else
	    	{
	    		//ShowMessage("Customer added successfully.");
	    		Toast.makeText(view.getContext(), "Customer added successfully.",
	    				   Toast.LENGTH_LONG).show();
	    	}
    	}
    }
    
    public void CancelCustomer() {
    	etFirstName.setText("");
    	etLastName.setText("");
    	etZipCode.setText("");
    	etEmail.setText("");
    }

    public void ShowMessage(String msg) { 
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
