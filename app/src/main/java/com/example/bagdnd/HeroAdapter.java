package com.example.bagdnd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HeroAdapter extends ArrayAdapter<HeroDnD> {
    private List<HeroDnD> heroDnDList;
    private int resource;
    private LayoutInflater layoutInflater;//обьект класса который привратит XML разметку в обьект View

    public HeroAdapter(Context context, int resource, List<HeroDnD> heroDnDList){
        super(context, resource, heroDnDList);
        this.heroDnDList = heroDnDList;
        this.resource = resource;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View contextView, ViewGroup viewGroup){
        View view = layoutInflater.inflate(resource, viewGroup, false);

        ImageView imageView = view.findViewById(R.id.imgHero);
        TextView textView = view.findViewById(R.id.infoHero);

        HeroDnD heroDnD = heroDnDList.get(position);

        imageView.setImageResource(heroDnD.getResourceForImage());
        textView.setText(heroDnD.toString());

        return view;
    }
}
