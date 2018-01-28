package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums;

public enum Style implements MenuElement{
    RED("red", "red.css"),
    BLUE("blue", "blue.css"),
    GREEN("green", "green.css"),
    YELLOW("yellow", "yellow.css");

    String id;
    String fileName;

    Style(String id, String fileName){
        this.id = id;
        this.fileName = fileName;
    }

    @Override
    public String toString(){
        return id;
    }

    public String getFileName(){
        return fileName;
    }
}
