package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.R;
import edu.gatech.seclass.prj2.Transaction;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
 
public class ViewTransactionFragment extends Fragment implements OnClickListener {
 
	View view;
	Button buttonSearch, buttonClear;
	EditText etSearch, etDate, etTotal, etGoldDiscount, etRewardDiscount, etCreditType, etCreditNum;
	Spinner sTransID;
	
	ArrayList<String> transIDList;
	ArrayAdapter<String> adp;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        view = inflater.inflate(R.layout.fragment_view_transaction, container, false);
        buttonSearch = (Button) view.findViewById(R.id.buttonVSearch);
        buttonSearch.setOnClickListener(this);
        buttonClear = (Button) view.findViewById(R.id.buttonVClear);
        buttonClear.setOnClickListener(this);
        etSearch = (EditText) view.findViewById(R.id.editTextVSearch);
        etDate = (EditText) view.findViewById(R.id.editTextVDate);
        etTotal = (EditText) view.findViewById(R.id.editTextVTotal);
        etGoldDiscount = (EditText) view.findViewById(R.id.editTextVRewardDiscount);
        etRewardDiscount = (EditText) view.findViewById(R.id.editTextVGoldDiscount);
        etCreditNum = (EditText) view.findViewById(R.id.editTextVCreditCardNum); 
        
        transIDList = new ArrayList<String>();
        adp = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, transIDList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTransID = (Spinner) view.findViewById(R.id.spinnerVTransID);
        sTransID.setAdapter(adp);
        sTransID.setOnItemSelectedListener(new OnItemSelectedListener() {
            
        	@Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
            	String transID = arg0.getItemAtPosition(position).toString();
    			Transaction trans = Manager.getInstance().getTransaction(transID);
    			String total = Double.toString(trans.getAmount());
    			String gold = Double.toString(trans.getAmountDiscountedByGold());
    			String reward = Double.toString(trans.getRewardAmountUsed());
    			CreditCard ccard = trans.getCard();
    			String ccnum = ccard.getAccountNumber();
    			String date = trans.getDate();
    			
    			etTotal.setText(total);
    			etDate.setText(date);
    			etGoldDiscount.setText(gold);
    			etRewardDiscount.setText(reward);
    			etCreditNum.setText(ccnum);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        
        return view;
    }
    
    @Override
    public void onClick(View v) {
    	
    	switch(v.getId()){ 	
        case R.id.buttonVSearch:
    		SearchForTransaction();
    	break;
        case R.id.buttonVClear:
    		Clear();
        break;
    	}
    }
    
    public void SearchForTransaction() {
    	//Get email address from EditText
    	String email = etSearch.getText().toString();
    	
    	if (email.isEmpty() || !isValidEmail(email)) {
    		ShowMessage("Please enter a valid email address.");
    	}
    	else
    	{
	    	//Loop through the transactions to get the transactionIDs associated with the email address   	
    		TransactionHistory transHist = Manager.getInstance().getTransactionHistory(email);
    		if (transHist != null) {
		        for (Transaction trans:transHist.getTransactions()) {
		        	String transId = trans.getTransID();
		        	adp.add(transId);
		        }
		        buttonSearch.setEnabled(false);
    		}
    		else { 
    			ShowMessage("The customer you are searching for does not exist.");
    		}
    	}
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
    
    public void Clear() {
    	transIDList.clear();
    	adp.notifyDataSetChanged();
    	etSearch.setText("");
    	etDate.setText("");
    	etTotal.setText("");
    	etGoldDiscount.setText("");
    	etRewardDiscount.setText("");
    	etCreditNum.setText("");
    	
    	buttonSearch.setEnabled(true);
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