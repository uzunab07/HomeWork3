package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDrink#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrink extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String DRINK_LIST = "LIST";


    // TODO: Rename and change types of parameters
    private ArrayList<Drink> drinks;

    public ViewDrink() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param drinks Parameter 1.
     * @return A new instance of fragment ViewDrink.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrink newInstance(ArrayList<Drink> drinks) {
        ViewDrink fragment = new ViewDrink();
        Bundle args = new Bundle();
        args.putSerializable(DRINK_LIST, drinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinks = (ArrayList<Drink>) getArguments().getSerializable(DRINK_LIST);
        }
    }

    TextView textEditDrinkNum, textEditDrinkAlco, textEditDrinkSize, textEditDrinkDate;
    int iter = 0;
    Drink drink;
    VDListener vdListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ( (MainActivity) getActivity()).getSupportActionBar().setTitle("View Drinks");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_drink, container, false);

        textEditDrinkAlco = view.findViewById(R.id.textEditDrinkAlco);
        textEditDrinkSize = view.findViewById(R.id.textEditDrinkSize);
        textEditDrinkDate = view.findViewById(R.id.textEditDrinkDate);
        textEditDrinkNum = view.findViewById(R.id.textEditDrinkNum);

        drink = drinks.get(iter);

        textEditDrinkNum.setText((iter+1) + " of " +drinks.size());
        textEditDrinkSize.setText(drink.getSize1()+" oz");
        textEditDrinkAlco.setText(drink.getAlcoholPercentage() + "% Alcohol");
        textEditDrinkDate.setText(" "+drink.getDate());

        //Previous Select
        view. findViewById(R.id.buttonPrevDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iter < 1){
                    iter = drinks.size()-1;
                    drink = drinks.get(iter);
                } else {
                    iter--;
                    drink = drinks.get(iter);
                }
                textEditDrinkNum.setText((iter+1) + " of " +drinks.size());
                textEditDrinkSize.setText(drink.getSize1()+" oz");
                textEditDrinkAlco.setText(drink.getAlcoholPercentage() + "% Alcohol");
                textEditDrinkDate.setText(" "+drink.getDate());
            }
        });

        //Forward Select
        view.findViewById(R.id.buttonNextDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iter++;
                if(iter > drinks.size()-1){
                    iter = 0;
                    drink = drinks.get(iter);
                } else {
                    drink = drinks.get(iter);
                }
                textEditDrinkNum.setText((iter+1) + " of " +drinks.size());
                textEditDrinkSize.setText(drink.getSize1()+" oz");
                textEditDrinkAlco.setText(drink.getAlcoholPercentage() + "% Alcohol");
                textEditDrinkDate.setText(" "+drink.getDate());
            }
        });

        //Delete
        view.findViewById(R.id.buttonTrashDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drinks.size() == 1){
                    drinks.clear();

                    vdListener.sendDrinkList(drinks);

                } else {

                    drinks.remove(iter);
                    if (iter < 1) {
                        iter = drinks.size() - 1;
                    } else {
                        iter--;
                    }
                    drink = drinks.get(iter);
                    textEditDrinkNum.setText((iter + 1) + " of " + drinks.size());
                    textEditDrinkSize.setText(drink.getSize1()+" oz");
                    textEditDrinkAlco.setText(drink.getAlcoholPercentage() + "% Alcohol");
                    textEditDrinkDate.setText(" " + drink.getDate());
                }
            }
        });

        //Close out of view
        view.findViewById(R.id.buttonViewClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vdListener.sendDrinkList(drinks);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof VDListener){
            vdListener = (VDListener) context;
        }else{
            throw  new RuntimeException(context.toString()+" Must Implement VDListener");
        }
    }

    public interface VDListener{
        void sendDrinkList(ArrayList<Drink> drink);
    }
}