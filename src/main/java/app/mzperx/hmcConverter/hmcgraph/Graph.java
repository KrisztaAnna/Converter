package app.mzperx.hmcConverter.hmcgraph;

import app.mzperx.hmcConverter.dao.ArchEdContextDao;
import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import app.mzperx.hmcConverter.dao.implementation.memory.NodeDaoMem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graph {
    private static final Logger logger = LoggerFactory.getLogger(Graph.class);

    NodeDaoMem nodeDaoMem = NodeDaoMem.getInstance();

    private String edgedefault = "directed";
    private String id = "g1";
    private int parseEdges;
    private int parseNodes;
    private String parseOrder = "free";
    private String data = "key=\"d2\"";

    public Graph(){
        this.parseNodes = nodeDaoMem.getAllNodes().size();
    }

    private void setParseEdges(){

    }

    public String openingToString() {
        return "\n  <graph edgedefault=\"" + this.edgedefault + "\" " +
                  "id=\"" + this.id + "\" " +
                  "parse.edges=\"" + this.parseEdges + "\" " +
                  "parse.nodes=\"" + this.parseNodes + "\" " +
                  "parse.order=\"" + this.parseOrder + "\">";
    }

    public String closingToString(){
        return "\n    <data " + this.data + "/>\n" +
                "  </graph>";
    }

}
