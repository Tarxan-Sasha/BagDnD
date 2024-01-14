package com.example.bagdnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Bag_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        TextView forceCharacter = findViewById(R.id.ForceCharacter);
        getCarryingCharacter(forceCharacter.getText().toString());

        
        HeroDnD hero1 = new HeroDnD("Arno", "Bard");
        TextView textView = findViewById(R.id.textTest);
        textView.setText(hero1.getName());


    }

    public void getCarryingCharacter(String force){
        TextView carryingCharacter = findViewById(R.id.CarryingLimitCharacter);
        int carrying = Integer.parseInt(force)*15;

        carryingCharacter.setText("/"+carrying+" фунтов");

    }

}