package app.mzperx.hmcConverter.hmcgraph;

public class HmcEdge {

    // eg. "ABOVE", "LATER", "CONTEMPORARY"
    private String type;

    private String valid = "true";
    private String xmlns = "";

    private String source = "     <source x=\"0.0\" y=\"0.0\"/>";
    private String target = "     <target x=\"0.0\" y=\"0.0\"/>";


    public HmcEdge(String type){
        this.type = type;
    }

    public String toString(){
        return "\n        <hmcedge type=\"ABOVE\" " +
                                  "valid=\"" + this.valid + "\" " +
                                  "xmlns=\"" + this.xmlns + "\">\n" +
                                   this.source +
                                   this.target +
                "        </hmcedge>";
    }

}
