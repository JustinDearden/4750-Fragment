package com.example.fragmenttransaction;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Manage thr life cycle of fragments
        //Only create new fragments if they are not already made - preserves the buffer for saving state
        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_a, new FragmentA(), "fragmentA");
            ft.replace(R.id.container_b, new FragmentB(), "fragmentB");
            ft.commitNow();
        }

        fragmentA = (FragmentA) getSupportFragmentManager().findFragmentByTag("fragmentA");
        fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag("fragmentB");
    }

    //Input to send data from FragmentA to FragmentB
    @Override
    public void onInputASent(CharSequence input) {
        fragmentB.updateEditText(input);
    }

    //Input to send data from FragmentB to FragmentA
    @Override
    public void onInputBSent(CharSequence input) {
        fragmentA.updateEditText(input);
    }
}