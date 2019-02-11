package app.mzperx.hmcConverter.model;

import app.mzperx.hmcConverter.dao.ArchEdContextDao;
import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArchEdContext {
    ArchEdContextDao archEdContextDao = ArchedContextDaoMem.getInstance();

    private String regexEqualTo = "above:";
    private String regexAbove = "contemporary with:";
    private String regexContemporaryWith = "below:";
    private String regexBelow = "last field";

    private String name;
    private String description;
    private List<ArchEdContext> equalTo = new ArrayList<>();
    private List<ArchEdContext> above = new ArrayList<>();
    private List<ArchEdContext> contemporaryWith = new ArrayList<>();
    private List<ArchEdContext> below = new ArrayList<>();
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
        String[] info =  this.informationToSort.split("equal to:");
        String description = info[0].trim();
        this.description = description;
        // remove the description part from the information field
        this.setInformationToSort(info[1]);
    }

    private void setBelow(){
        List<ArchEdContext> allContexts = archEdContextDao.getAllContexts();
        List<String> contextsToAdd = new ArrayList<>();

            String info = this.informationToSort;

            Pattern pattern = Pattern.compile("\\d{5}");
            Matcher matcher = pattern.matcher(info);

            while (matcher.find()){
                String name = matcher.group(0);
                contextsToAdd.add(name);
            }
            
            for (String contextName : contextsToAdd) {
                for (ArchEdContext context : allContexts){
                    if (contextName.equals(context.getName())){
                        this.below.add(context);
                    }
                }
            }
        this.setInformationToSort("");
    }


    private void setField(String fieldRegex){
        List<ArchEdContext> allContexts = archEdContextDao.getAllContexts();
        List<String> contextsToAdd = new ArrayList<>();

        String[] info =  this.informationToSort.split(fieldRegex);
        String[] contextNames =  info[0].split("\\s|,\\s");

        for (String s : contextNames){
            contextsToAdd.add(s.replaceAll("[^0-9]", ""));
        }

        if (fieldRegex.equals(this.regexEqualTo)){
            for (String contextName : contextsToAdd) {
                for (ArchEdContext context : allContexts){
                    if (contextName.equals(context.getName())){
                        this.equalTo.add(context);
                    }
                }
            }
        }else if(fieldRegex.equals(this.regexAbove)){
            for (String contextName : contextsToAdd) {
                for (ArchEdContext context : allContexts){
                    if (contextName.equals(context.getName())){
                        this.above.add(context);
                    }
                }
            }
        }else if(fieldRegex.equals(this.regexContemporaryWith)){
            for (String contextName : contextsToAdd) {
                for (ArchEdContext context : allContexts){
                    if (contextName.equals(context.getName())){
                        this.contemporaryWith.add(context);
                    }
                }
            }
        }
        // remove the processed part from the information field
        this.setInformationToSort(info[1]);
    }

    public void sortInformation(){
        setDescription();
        setField(this.regexEqualTo);
        setField(this.regexAbove);
        setField(this.regexContemporaryWith);
        setBelow();
    }

    public String toString(){
        try {
            return "Name: " + this.name + "\n" +
                    "Description: " + this.description + "\n" +
                    "Equal to: " + fieldToString(this.regexEqualTo) + "\n" +
                    "Above: " + fieldToString(this.regexAbove) + "\n" +
                    "Contemporary with: " + fieldToString(this.regexContemporaryWith) + "\n" +
                    "Below: " + fieldToString(this.regexBelow) + "\n" +
                    "Information to sort: \n" + this.informationToSort + "\n+++++++++++++++++++++++++++++++++++++++++++\n";
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String fieldToString(String fieldRegex){
        String contextNames = "";

        if (fieldRegex == this.regexEqualTo){
            for (ArchEdContext c : this.equalTo) {
                contextNames = contextNames + c.getName() + " ";
            }
        }else if (fieldRegex == this.regexAbove){
            for (ArchEdContext c : this.above) {
                contextNames = contextNames + c.getName() + " ";
            }
        }else if(fieldRegex == this.regexContemporaryWith){
            for (ArchEdContext c : this.contemporaryWith) {
                contextNames = contextNames + c.getName() + " ";
            }
        }else if(fieldRegex == this.regexBelow){
            for (ArchEdContext c : this.below) {
                contextNames = contextNames + c.getName() + " ";
            }
        }
        return contextNames;
    }
}