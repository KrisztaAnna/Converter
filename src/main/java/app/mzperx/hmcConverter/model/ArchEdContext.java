package app.mzperx.hmcConverter.model;

import app.mzperx.hmcConverter.dao.ArchEdContextDao;
import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import java.util.ArrayList;
import java.util.List;

public class ArchEdContext {
    ArchEdContextDao archEdContextDao = ArchedContextDaoMem.getInstance();

    private String name;
    private String description;
    private List<ArchEdContext> equalTo = new ArrayList<>();
    private List<ArchEdContext> above = new ArrayList<>();
    private List<ArchEdContext> contemporaryWith = new ArrayList<>();
    private List<String> below;
    private String informationToSort;

    public ArchEdContext(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<ArchEdContext> getEqualTo(){
        return this.equalTo;
    }

    public void setInformationToSort(String informationToSort){
        this.informationToSort = informationToSort;
    }

    private void setDescription(){
        String[] info =  this.informationToSort.split("equal to:");
        String description = info[0].trim();
        this.description = description;
        // remove the description part from the information field
        this.setInformationToSort(info[1]);
    }

    private void setEqualTo(){
        List<ArchEdContext> allContexts = archEdContextDao.getAllContexts();
        String[] info =  this.informationToSort.split("above:");
        String[] contextNames =  info[0].split("\\s|,\\s");
        List<String> contextsToAdd = new ArrayList<>();

        for (String s : contextNames){
            contextsToAdd.add(s.replaceAll("[^0-9]", ""));
        }

        for (String contextName : contextsToAdd) {
            for (ArchEdContext context : allContexts){
                if (contextName.equals(context.getName())){
                    this.equalTo.add(context);
                }
            }
        }
        // remove the equalTo part from the information field
        this.setInformationToSort(info[1]);
    }

    public void sortInformation(){
        setDescription();
        setEqualTo();
    }

    public String toString(){
        String equalToToString = equalToToString();
        try {
            return "Name: " + this.name + "\n" +
                    "Description: " + this.description + "\n" +
//                    "Equal to: " + this.equalTo + "\n" +
                    "Equal to: " + equalToToString + "\n" +
                    "Above: " + this.above + "\n" +
                    "Contemporary with: " + this.contemporaryWith + "\n" +
                    "Below: " + this.below + "\n" +
                    "Information to sort: \n" + this.informationToSort + "\n+++++++++++++++++++++++++++++++++++++++++++\n";
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String equalToToString() {
        String equalTo = "";
        for (ArchEdContext c : this.equalTo) {
            equalTo = equalTo + c.getName() + " ";
        }
        return equalTo;
    }
}