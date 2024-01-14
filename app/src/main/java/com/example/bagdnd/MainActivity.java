package com.example.bagdnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<HeroDnD> heroesList = new ArrayList<HeroDnD>();
    HeroDnD heroDnD;
    Intent intent;
    boolean isHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Данный код пишу в методе onResume(), потому как в жизненном списке активити можно сказать это более частое место что ли.
    Этот метод будет срабатывать и после паузы(onPause()) когда например переходим на новую активити
    и после отсановки(onStop()) когда более серьезной...паузы...ну понятно в общем.
    А метод onCreate то беж состояние создания, сработает один раз в начале.
     */
    @Override
    protected void onResume(){
        super.onResume();
        Bundle bundle = getIntent().getExtras();

        ListView heroesListView = findViewById(R.id.ListOfHeroes);//Привязываемся к нашему виджету в XML
        /*Создаем адаптер для управления списком,
          this - это наша активити, второе - это шаблон элементов списка, третией - источник
          ndroid.R.layout.simple_list_item_1 - это шаблон разметки элементов списка в виджете, его можно и самому создать
          третий - это источник данных списка, это может быть масив и колекция
         */
        //К слову это мой собственный адаптер, и здесь указан мой собственный шаблон
        HeroAdapter heroAdapter = new HeroAdapter(this, R.layout.layout_for_item_from_hero_list, heroesList);
        heroesListView.setAdapter(heroAdapter);//Подключаем к нашему виджету адаптер

        if(bundle != null){
            heroDnD = (HeroDnD) bundle.getSerializable(HeroDnD.class.getSimpleName());//HeroDnD.class.getSimpleName()
            //Делаю проверку на поступающие элементы, так как в список добавляеться лишнее
            isHere = heroesList.stream().anyMatch(x ->{
                boolean y = x.hashCode() == heroDnD.hashCode();//сравниваю хешкод каждого элемента списка с поступающим обьектом
                if(y==true){
                    y=x.equals(heroDnD);//проверяем дополнительно, на случай колизии
                }
                return y;
            });

            //Делаю проверку посколько без неё приложение на тлфн не запускаетсья, и выдается ошибка.
            //Адаптер хочет добавить null и вылитает NullPointException
            if(heroDnD != null && isHere == false){
                heroAdapter.add(heroDnD);
                heroAdapter.notifyDataSetChanged();//Это метод что бы изменения вступили не только в источнике данных адаптера (heroesList), а и в самом виджете
            }
        }
        //Создаю слушаетль при нажатии на элемент списка ListView
        intent = new Intent(this, HeroProfile.class);//определяю intent для перехода на новую активити
        /*
        Интерфейс OnItemClickListener находиться внутри класса AdapterView, потому пишем через точку
        И сразу записываем в анонимный класс
         */
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            /*
            Единственный метод который есть у внутренего интерфейса OnItemClickListener это onItemClick, его и переопределяем:
            В аргументах:
            1) parent - это место где произошло нажатие, то есть наш ListView. (AdapterView являеться родителем ListView)
            2) view - это наш элемент на который произошло нажатие
            3) position - это позиция в списке элемента на который нажимают
            4) id - это уникальный идентификатор, для БД или если адаптере его использует.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Получаем наш обьект с нажатого элемента, мы получаем данные.
                Буквально что тут происходит:
                Наш ListView отдает нам наш обьект из своего списка(виджета, ну того что на экране) по позиции.
                нажмем на второй элемент, будет позиция два, нажмем на первый будет позиция один
                */
                HeroDnD selectedHero = (HeroDnD) parent.getItemAtPosition(position);
                intent.putExtra(HeroDnD.class.getSimpleName(), selectedHero);
                startActivity(intent);

            }
        };
        heroesListView.setOnItemClickListener(itemClickListener);

    }

    //Кнопка для создания персонажа
    public void createNewHero(View view){
        intent = new Intent(this, CreateHero.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


}
