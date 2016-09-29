package br.ufg.iiisea.sea.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.AdapterViewPager;
import br.ufg.iiisea.sea.control.InitialConfig;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView tvAppName = (TextView) mCustomView.findViewById(R.id.tvCustomBarAppName);
        tvAppName.setText("3º SEA");

        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        configuraTabs();
    }

    private void configuraTabs(){
        //Configura os Fragments e Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        AdapterViewPager adapter = new AdapterViewPager(getSupportFragmentManager());

        for(int i = 0; i < 6; i++){
            adapter.addFrag(ListFragment.newInstance(i), "Teste " + i);
        }
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }
}
