package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums;

import java.util.Locale;

public enum Language implements MenuElement{
    POLISH(new Locale("pl", "PL"), "polish"),
    ENGLISH(new Locale("en", "EN"), "english");

    private Locale locale;
    private String id;

    Language(Locale locale, String id){
        this.locale = locale;
        this.id = id;
    }

    public Locale asLocale(){
        return locale;
    }

    @Override
    public String toString(){
        return id;
    }
}
