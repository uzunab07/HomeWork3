package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SetGender.GListener,AddDrink.ADListener, ViewDrink.VDListener {
    TextView currentWeight, numOfDrinks, BACLevel, BACStatus;

    private static final DecimalFormat df = new DecimalFormat("0.000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerView, new Main(),"MainFragment")
                .commit();
    }

    @Override
    public void sendGenderAndWeight(String gender, int weight) {
        Log.d("TAG", "sendGenderAndWeight: "+gender+" ; "+weight);
       Main mainFragment = (Main) getSupportFragmentManager().findFragmentByTag("MainFragment");

        mainFragment.settingValuesFromGenderFragment(new Profile(weight,gender));
        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void cancelSetGender() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendDrink(Drink drink) {
        Main mainFragment = (Main) getSupportFragmentManager().findFragmentByTag("MainFragment");

        Log.d("TAG", "sendDrink: "+drink);
        mainFragment.settingValuesFromAddDrinkFragment(drink);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelAddDrink() {
        getSupportFragmentManager().popBackStack();
    }


    @Override
    public void sendDrinkList(ArrayList<Drink> drinks) {
        Main mainFragment = (Main) getSupportFragmentManager().findFragmentByTag("MainFragment");

        mainFragment.settingNewDrinkList(drinks);
        getSupportFragmentManager().popBackStack();
    }
}