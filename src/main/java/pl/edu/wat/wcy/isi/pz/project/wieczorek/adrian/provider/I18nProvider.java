package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Language;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public class I18nProvider {
    private static String CONFIG_FILENAME = "i18n";
    private static I18nProvider provider;

    private static Locale defaultLocale = Language.POLISH.asLocale();

    private ResourceBundle bundle;

    private I18nProvider(){
        loadResources(defaultLocale);
    }

    private void loadResources(Locale locale) {
        bundle = ResourceBundle.getBundle(CONFIG_FILENAME, locale);
    }

    public void changeLanguage(Locale locale){
        loadResources(locale);
    }

    public static I18nProvider getInstance(){
        if(provider == null){
            provider = new I18nProvider();
        }
        return provider;
    }

    public String getText(String key){
        String result;
        try{
            result = bundle.getString(key);
        }catch(MissingResourceException e){
            result = key;
        }

        if(result == null){
            result = "";
        }

        return result;
    }
}
