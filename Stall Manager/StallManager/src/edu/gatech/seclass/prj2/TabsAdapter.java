package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.AddCustomerFragment;
import edu.gatech.seclass.prj2.EditCustomerFragment;
import edu.gatech.seclass.prj2.CheckoutFragment;
import edu.gatech.seclass.prj2.ViewTransactionFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsAdapter extends FragmentPagerAdapter {
 
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            return new AddCustomerFragment();
        case 1:
            return new EditCustomerFragment();       
        case 2:
        	return new CheckoutFragment();
        case 3:
            return new ViewTransactionFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}