package app.mzperx.hmcConverter;

import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import app.mzperx.hmcConverter.dao.implementation.memory.EdgeDaoMem;
import app.mzperx.hmcConverter.dao.implementation.memory.NodeDaoMem;
import app.mzperx.hmcConverter.hmcgraph.Edge;
import app.mzperx.hmcConverter.hmcgraph.HmcEdge;
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
    private EdgeDaoMem edgeDaoMem = EdgeDaoMem.getInstance();

    private void contextsToNodes(){
        logger.info("Converting ArchEd contexts into HMC nodes...");
        List<Node> allNodes = nodeDaoMem.getAllNodes();
        for (ArchEdContext context : archedContextDaoMem.getAllContexts()){
            Node node = new Node(new HmcNode(context));
            allNodes.add(node);
        }
        logger.info(allNodes.size() + " nodes have been created.");
    }

    private void indexEdges(){
        List<Edge> allEdges = edgeDaoMem.getAllEdges();
        for(Edge e: allEdges){
            e.setId(allEdges.indexOf(e)+1);
        }
    }

    private void createAboveEdges(){
        logger.info("Creating \"ABOVE\" edges... ");
        List<Edge> allEdges = edgeDaoMem.getAllEdges();
        List<ArchEdContext> allContexts = archedContextDaoMem.getAllContexts();

        for (ArchEdContext context : allContexts){
            if(context.getBelow().size()>0){
                for(ArchEdContext targetC : context.getBelow()){
                    Node source = nodeDaoMem.getBy(context.getName());
                    Node target = nodeDaoMem.getBy(targetC.getName());
                    allEdges.add(new Edge(source, target, new HmcEdge("ABOVE")));
                }
            }
        }
        indexEdges();
        logger.info("Number of edges: " + allEdges.size());

    }

    private void createContemporaryEdges(){
        logger.info("Creating \"CONTEMPORAY\" edges... ");
        List<Edge> allEdges = edgeDaoMem.getAllEdges();
        List<ArchEdContext> allContexts = archedContextDaoMem.getAllContexts();

        for (ArchEdContext context : allContexts){
            if(context.getContemporaryWith().size()>0){
                for(ArchEdContext targetC : context.getContemporaryWith()){
                    Node source = nodeDaoMem.getBy(context.getName());
                    Node target = nodeDaoMem.getBy(targetC.getName());
                    if(!edgeDaoMem.doesContemporaryEdgeExist(source, target)){
                        allEdges.add(new Edge(source, target, new HmcEdge("CONTEMPORARY")));
                    }

                }
            }
        }
        indexEdges();
        logger.info("Number of edges: " + allEdges.size());

    }

    public void createNodesAndEdges(){
        contextsToNodes();
        createAboveEdges();
        createContemporaryEdges();
    }

}

