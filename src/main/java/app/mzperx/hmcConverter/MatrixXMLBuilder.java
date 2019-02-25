package app.mzperx.hmcConverter;

import app.mzperx.hmcConverter.dao.implementation.memory.NodeDaoMem;
import app.mzperx.hmcConverter.hmcgraph.Graph;
import app.mzperx.hmcConverter.hmcgraph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MatrixXMLBuilder {
    private static final Logger logger = LoggerFactory.getLogger(MatrixXMLBuilder.class);
    private Graph graph;
    private NodeDaoMem nodeDaoMem = NodeDaoMem.getInstance();

    private String header =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns/graphml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns/graphml http://www.yworks.com/xml/schema/graphml/1.0/graphml-attributes.xsd\">\n" +
                    "  <key extension.type=\"hmc\" for=\"node\" id=\"d0\"/>\n" +
                    "  <key extension.type=\"hmc\" for=\"edge\" id=\"d1\"/>\n" +
                    "  <key extension.type=\"hmc\" for=\"graph\" id=\"d2\"/>\n" +
                    "  <key for=\"graphml\" id=\"d3\" yfiles.type=\"resources\"/>";

    private String footer =
            "\n  <data key=\"d3\">\n" +
                    "    <Resources xmlns=\"http://www.yworks.com/xml/graphml\"/>\n" +
                    "  </data>\n" +
                    "</graphml>";

    private String nodesToString(){
        String nodesString = "";
        List<Node> allNodes = nodeDaoMem.getAllNodes();
        for (Node node : allNodes){
            nodesString += node.toString();
        }
        return nodesString;
    }

    public String buildGraph(){
        logger.info("Building the matrix...");
        graph = new Graph();
        return header + graph.openingToString() + nodesToString() + graph.closingToString() + footer;
    }

}

