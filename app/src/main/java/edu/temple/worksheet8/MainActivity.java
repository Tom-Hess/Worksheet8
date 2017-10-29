package edu.temple.worksheet8;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private FrameLayout web;
    private ArrayList<WebFragment> browserFragments;
    private int count;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (FrameLayout) findViewById(R.id.web);
        fm = getFragmentManager();
        browserFragments = new ArrayList<>();
        count = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case (R.id.action_new_tab):
                WebFragment tab = WebFragment.newInstance();
                browserFragments.add(tab);
                count++;
                loadTab(R.id.web, tab);
                return true;
            case (R.id.action_prev_tab):
                if(count > 1){
                    count--;
                    loadTab(R.id.web, browserFragments.get(count-1));
                }
                return true;
            case (R.id.action_next_tab):
                if(count < browserFragments.size()){
                    loadTab(R.id.web, browserFragments.get(count));
                    count++;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadTab(int frame, Fragment frag){
        FragmentTransaction fragTran = fm.beginTransaction();
        fragTran.replace(frame, frag);

        fragTran.commit();
        fm.executePendingTransactions();
    }
}