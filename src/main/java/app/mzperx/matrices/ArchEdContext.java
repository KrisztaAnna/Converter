package app.mzperx.matrices;

import java.util.List;

public class ArchEdContext {

    private String name;
    private String description;
    private List<String> equalTo;
    private List<String> above;
    private List<String> contemporaryWith;
    private List<String> below;
    private String informationToSort;


    public ArchEdContext(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


    public void setDescription(String description){
        this.description = description;
    }

    public void setInformationToSort(String informationToSort){
        this.informationToSort = informationToSort;
    }


    public String toString(){
        return "Name: " + this.name + "\n"  +
                "Description: " + this.description + "\n" +
                "Equal to: " + this.equalTo + "\n" +
                "Above: " + this.above + "\n" +
                "Contemporary with: " + this.contemporaryWith + "\n" +
                "Below: " + this.below + "\n" +
                "Information to sort inside the context: " + this.informationToSort + "\n\n";
    }


}

