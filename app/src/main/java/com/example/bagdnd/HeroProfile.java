package com.example.bagdnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroProfile extends AppCompatActivity {
    HeroDnD heroDnD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            heroDnD = (HeroDnD) bundle.getSerializable(HeroDnD.class.getSimpleName());
        }

        ImageView avatarHero = findViewById(R.id.AvatarHero);
        TextView textView = findViewById(R.id.TestInfoHero);

        avatarHero.setImageResource(heroDnD.getResourceForImage());
        textView.setText(heroDnD.toString());
    }

    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

    }


}