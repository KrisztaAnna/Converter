package app.mzperx.hmcConverter.dao.implementation.memory;

import app.mzperx.hmcConverter.dao.NodeDao;
import app.mzperx.hmcConverter.hmcgraph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NodeDaoMem implements NodeDao {
    private static final Logger logger = LoggerFactory.getLogger(NodeDaoMem.class);

    private static List<Node> NODES = new ArrayList<>();
    private static NodeDaoMem instance = null;

    private NodeDaoMem() {
    }

    public static NodeDaoMem getInstance() {
        if (instance == null) {
            instance = new NodeDaoMem();
        }
        return instance;
    }

    @Override
    public List<Node> getAllNodes(){
        return NODES;
    }

    @Override
    public void addNode(Node node){
        NODES.add(node);
    }

    @Override
    public Node getBy(String id){
//        logger.debug("Looking for node: " + id);
        for (Node n : NODES){
            if (n.getId().equals(id)){
//                logger.info("Returning node: " + id);
                return n;
            }
        }
        logger.warn("NOT FOUND: node " + id);
        return null;
    }

}
