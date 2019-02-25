package app.mzperx.hmcConverter.hmcgraph;

public class Node {
    private String id;
    private String data = "key=\"d2\"";
    private HmcNode hmcNode;

    public Node(HmcNode hmcNode){
        this.hmcNode = hmcNode;
        this.id = hmcNode.getId();
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return  "\n\t\t<node id=\"" + this.id + "\">\n" +
                "\t\t\t<data " + this.data + ">\n" +
                this.hmcNode.toString() +
                "\n\t\t\t</data>" +
                "\n\t\t</node>";
    }
}
