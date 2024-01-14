package com.example.bagdnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//Аактвити с созданием персонажа
public class CreateHero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hero);
    }
    public void createHeroButton(View view){
        EditText editTextNameHero = findViewById(R.id.nameHero);
        EditText editTextForce = findViewById(R.id.ClassHero);

        String name = editTextNameHero.getText().toString();
        String className = editTextForce.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);//Создаю обьект Intent который отвечает за мою активити

        HeroDnD heroDnD = new HeroDnD(name, className);

        //HeroDnD.class.getSimpleName()
        intent.putExtra(HeroDnD.class.getSimpleName(), heroDnD);//кладу сериализую обьект Hero
        //Этот флаг позволяет нам перейти к уже существующей активити в стеке очищая весь стек оставляя только выбранную, а не создавать новую
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);//запуск нужной активити активити, то есть на этом моменте в стеке активити будет лишь ChooseHero из-за флага который чистит остальные.
    }
}