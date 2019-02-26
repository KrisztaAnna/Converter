package app.mzperx.hmcConverter.hmcgraph;

public class HmcEdge {

    // eg. "ABOVE", "LATER", "CONTEMPORARY"
    private String type;

    private String valid = "true";
    private String xmlns = "";

    private String source = "\t\t\t\t\t<source x=\"0.0\" y=\"0.0\"/>";
    private String target = "\t\t\t\t\t<target x=\"0.0\" y=\"0.0\"/>";


    public HmcEdge(String type){
        this.type = type;
    }

    public String toString(){
        return "\n\t\t\t\t<hmcedge type=\"" + this.type + "\" valid=\"" + this .valid +"\" xmlns=\"" + this.xmlns + "\">"  + "\n" +
                this.source + "\n" +
                this.target + "\n" +
                "\t\t\t\t</hmcedge>";
    }

}
