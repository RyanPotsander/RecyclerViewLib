package com.bearcub.recyclerviewlib;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bearcub.recyclerviewlibrary.ExtRecyclerView;

/**
 * Created by Home on 6/10/2015.
 */
public class MainActivity extends AppCompatActivity implements ExtRecyclerView.OnTouchItemSelectedListener, View.OnClickListener {
    Button button1, button2, button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();

        if (savedInstanceState == null) {
            setFragment(getRecyclerViewFragment());
        }
    }

    public RecyclerViewFragment getRecyclerViewFragment(){
        return RecyclerViewFragment.newInstance();
    }

    public void initButtons(){
        button1 = (Button)findViewById(R.id.button);
        button1.setTag(new MyTag(0));
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setTag(new MyTag(1));
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setTag(new MyTag(2));
        button3.setOnClickListener(this);
    }

    public void setFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void onTouchItemSelected(View view, int position) {
        Toast.makeText(this, "you touched "+ view + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        MyTag tag = (MyTag)v.getTag();
        int test = tag.test;
        switch (test){
            case 0:
                replaceFragment(RecyclerViewFragment.newInstance());
                break;
            case 1:
                replaceFragment(SecondFragment.newInstance());
                break;
            case 2:
                replaceFragment(ThirdFragment.newInstance());
                break;
        }
    }
}
