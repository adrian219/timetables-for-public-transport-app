package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.controller;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.model.HomeModel;

/**
 * Created by Adrian Wieczorek on 11/22/2017.
 */
public class HomeController {
    private final HomeModel model;

    public HomeController(HomeModel model) {
        this.model = model;
    }

    public void onClickButton(){
        System.out.println("klikniecie");
        model.reaction();
    }
}
