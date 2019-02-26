package app.mzperx.hmcConverter.dao.implementation.memory;

import app.mzperx.hmcConverter.dao.EdgeDao;
import app.mzperx.hmcConverter.hmcgraph.Edge;
import app.mzperx.hmcConverter.hmcgraph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class EdgeDaoMem implements EdgeDao {
    private static final Logger logger = LoggerFactory.getLogger(NodeDaoMem.class);

    private static List<Edge> EDGES = new ArrayList<>();
    private static EdgeDaoMem instance = null;

    private EdgeDaoMem() {
    }

    public static EdgeDaoMem getInstance() {
        if (instance == null) {
            instance = new EdgeDaoMem();
        }
        return instance;
    }

    public boolean doesContemporaryEdgeExist(Node source, Node target){
        if (source.getId().equals(target.getId())) {
            return true;
        }
        for (Edge e : getAllEdges()) {
            String s = e.getSource().getId();
            String t = e.getTarget().getId();

            if(    (s.equals(source.getId()) ||
                    s.equals(target.getId())) &&
                    (t.equals(source.getId()) ||
                    t.equals(target.getId()))) {
                return true;
            }
        }return false;

    }

    @Override
    public  List<Edge> getAllEdges(){
        return EDGES;
    }

    @Override
    public void addEdge(Edge edge){
        EDGES.add(edge);
    }

    @Override
    public Edge getBy(String id){
        logger.info("Returning edge: " + id);
        for (Edge edge : EDGES){
            if (edge.getId().equals(id)){
                logger.info("Returning node: " + id);
                return edge;
            }
        }
        logger.warn("NOT FOUND: node " + id);
        return null;
    }


}
