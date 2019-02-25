package app.mzperx.hmcConverter;

import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import app.mzperx.hmcConverter.dao.implementation.memory.EdgeDaoMem;
import app.mzperx.hmcConverter.dao.implementation.memory.NodeDaoMem;
import app.mzperx.hmcConverter.hmcgraph.Edge;
import app.mzperx.hmcConverter.hmcgraph.HmcNode;
import app.mzperx.hmcConverter.hmcgraph.Node;
import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArchEdContextToNodesAndEdges {
    private static final Logger logger = LoggerFactory.getLogger(ArchEdContextToNodesAndEdges.class);

    private ArchedContextDaoMem archedContextDaoMem = ArchedContextDaoMem.getInstance();
    private NodeDaoMem nodeDaoMem = NodeDaoMem.getInstance();
//    private EdgeDaoMem edgeDaoMem = EdgeDaoMem.getInstance();


    private void contextsToNodes(){
        logger.info("Converting ArchEd contexts into HMC nodes...");
        List<Node> allNodes = nodeDaoMem.getAllNodes();
        for (ArchEdContext context : archedContextDaoMem.getAllContexts()){
            Node node = new Node(new HmcNode(context));
            allNodes.add(node);
        }
    }

//    private void creatingEdges(){
//        List<Edge> allEdges = edgeDaoMem.getAllEdges();
//        List<Node> allNodes = nodeDaoMem.getAllNodes();
//
//    }

    public void createNodesandEdges(){
        contextsToNodes();
    }

}

