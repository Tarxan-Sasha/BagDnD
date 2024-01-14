package com.example.bagdnd;

import java.io.Serializable;
import java.util.Objects;

public class HeroDnD implements Serializable {
    private String name;
    private String classOfHero;//Как вариант потом заменить на Enum
    private int resourceForImage = R.drawable.first_lvl_hero;//тестовая версия, по базе обычная картинка
    public HeroDnD(String name, String classOfHero){
        this.name=name;
        this.classOfHero=classOfHero;
    }

    public String getName() {
        return name;
    }
    public int getResourceForImage(){
        return resourceForImage;
    }

    @Override
    public String toString(){
        return name+" "+classOfHero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroDnD heroDnD = (HeroDnD) o;
        return Objects.equals(name, heroDnD.name) && Objects.equals(classOfHero, heroDnD.classOfHero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, classOfHero);
    }
}
