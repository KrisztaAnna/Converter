package app.mzperx.hmcConverter.hmcgraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Edge {
    private static final Logger logger = LoggerFactory.getLogger(Edge.class);

    private String id;
    private Node source;
    private Node target;
    private String data = "key=\"d2\">";
    private HmcEdge hmcEdge;

    public Edge(Node source, Node target, HmcEdge hmcEdge){
        this.source = source;
        this.target = target;
        this.hmcEdge = hmcEdge;
    }

    public void setId(int i){
        this.id = "e" + i;
    }

    public String getId(){
        return this.id;
    }

    public Node getSource(){
        return this.source;
    }

    public Node getTarget(){
        return this.target;
    }

    public String toString(){
        return
                        "\n\t\t<edge id=\"" + this.id + "\" source=\"" + this.source.getId() + "\" target=\"" + this.target.getId()  + "\">\n" +
                        "\t\t\t<data " + this.data +
                              this.hmcEdge.toString()+ "\n" +
                        "\t\t\t</data>" + "\n" +
                        "\t\t</edge>";
    }
}
