package app.mzperx.matrices;

import java.util.List;

public class ArchEdContext {

    private String name;
    private List<String> equalTo;
    private List<String> above;
    private List<String> contemporaryWith;
    private List<String> below;


    public ArchEdContext(String name,
                         List<String> equalTo,
                         List<String> above,
                         List<String> contemporaryWith,
                         List<String> below){
        this.name = name;
        this.equalTo = equalTo;
        this.above = above;
        this.contemporaryWith = contemporaryWith;
        this.below = below;
    }


    public ArchEdContext(String name){
        this.name = name;

    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return "The context number is: " + this.name + ".\n";
    }


}

