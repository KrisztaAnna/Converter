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

    public void setInformationToSort(String informationToSort){
        this.informationToSort = informationToSort;
    }


    private void setDescription(){
        String[] info =  this.informationToSort.split("above:");
        String description = info[0].trim();
        this.description = description;

        // remove the description part from the information yet to be sorted
        this.setInformationToSort(info[1]);
    }
    

    public void sortInformation(){
        setDescription();
    }


    public String toString(){
        return "Name: " + this.name + "\n"  +
                "Description: " + this.description + "\n" +
                "Equal to: " + this.equalTo + "\n" +
                "Above: " + this.above + "\n" +
                "Contemporary with: " + this.contemporaryWith + "\n" +
                "Below: " + this.below + "\n" +
                "Information to sort inside the context: \n" + this.informationToSort + "\n\n";
    }


}

