package app.mzperx.matrices;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private void setAbove(){
        List<String> above = new ArrayList<>();

        String[] info =  this.informationToSort.split("contemporary with:");
        for (int i = 0; i < info.length; i++){
            System.out.println(i + " " + info[i]);
        }

//        String toAddToAbove = info[0];
//
//        Pattern pattern = Pattern.compile("\\n.\\d{5}.");
//        Matcher matcher = pattern.matcher(toAddToAbove);
//
//        while (matcher.find()) {
//            String number  = matcher.group(0);
//            String contextNumber = number.replaceAll("[^0-9]", "");
//            above.add(contextNumber);
//        }
//
//        this.above = above;
//
//        // remove the "equal to" part from the information yet to be sorted
//        this.setInformationToSort(info[1]);

    }

    public void sortInformation(){
        setDescription();
        setAbove();
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

