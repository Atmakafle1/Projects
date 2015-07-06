package edu.gatech.seclass.prj2;

import java.io.IOException;

import edu.gatech.seclass.prj2.Manager;
import edu.gatech.seclass.prj2.TabsAdapter;
import edu.gatech.seclass.prj2.R;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
  
  private ViewPager viewPager;
  private TabsAdapter mAdapter;
  private ActionBar actionBar;
  // Tab titles
  private String[] tabs = { "Add Customer", "Edit Customer", "Checkout", "View Transaction" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Initialization
    viewPager = (ViewPager) findViewById(R.id.pager);
    actionBar = getActionBar();
    mAdapter = new TabsAdapter(getSupportFragmentManager());

    viewPager.setAdapter(mAdapter);
    actionBar.setHomeButtonEnabled(false);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    // Lets load the Manager
    try {
      String retCode = Manager.getInstance().init(this);
      if (retCode.toUpperCase().contains("ERROR")) {
        ShowMessage(retCode);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Adding Tabs
    for (String tab_name : tabs) {
      actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
    }

    /**
     * on swiping the ViewPager make respective tab selected
     * */
    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

      @Override
      public void onPageSelected(int position) {
        // on changing the page
        // make respected tab selected
        actionBar.setSelectedNavigationItem(position);
      }

      @Override
      public void onPageScrolled(int arg0, float arg1, int arg2) {
      }

      @Override
      public void onPageScrollStateChanged(int arg0) {
      }
    });
  }

  @Override
  public void onTabReselected(Tab tab, FragmentTransaction ft) {
  }

  @Override
  public void onTabSelected(Tab tab, FragmentTransaction ft) {
    // on tab selected
    // show respected fragment view
    viewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(Tab tab, FragmentTransaction ft) {
  }

  @Override
  public void onBackPressed() {
    finish();
  }

  public void ShowMessage(String msg) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
}
