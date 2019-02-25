package app.mzperx.hmcConverter.hmcgraph;

import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import app.mzperx.hmcConverter.model.ArchEdContext;
import java.util.List;

public class HmcNode {
    ArchedContextDaoMem archedContextDaoMem = ArchedContextDaoMem.getInstance();
    List<ArchEdContext> allContexts = archedContextDaoMem.getAllContexts();

    private String bookmarked = "false";
    private String description="";
    private String id;
    private int index;
    private String layer = "0";
    private String name;
    private String type = "DEPOSIT";
    private String valid = "true";
    private String x = "0.0";
    private String y = "0.0";
    private String xmlns = "";

    public HmcNode(ArchEdContext context){
        this.index = allContexts.indexOf(context);
        this.name = context.getDescription();
        this.id = context.getName();
        if(context.getEqualTo().size()>0) {
            this.description = "Context equals to: ";
            for (ArchEdContext c : context.getEqualTo()) {
                this.description += c.getName()+ " ";
            }
        }
    }

    public String getId(){
        return  this.id;
    }

    public String toString(){
        String hmcNode =
                "\t\t\t\t<hmcnode bookmarked=\"" + this.bookmarked + "\" " +
                        "description=\"" + this.description + "\" " +
                        "id=\"" + this.id + "\" " +
                        "index=\"" + this.index + "\" " +
                        "layer=\"" + this.layer + "\" " +
                        "name=\"" + this.name + "\" " +
                        "type=\"" + this.type + "\" " +
                        "valid=\"" + this.valid + "\" " +
                        "x=\"" + this.x + "\" " +
                        "y=\"" + this.y + "\" " +
                        "xmlns=\"" + this.xmlns + "\"/>";
        return hmcNode;
    }
}
