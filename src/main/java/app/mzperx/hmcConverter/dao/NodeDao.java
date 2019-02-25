package app.mzperx.hmcConverter.dao;

import app.mzperx.hmcConverter.hmcgraph.Node;
import java.util.List;

public interface NodeDao {
    void addNode(Node node);
    List<Node> getAllNodes();
    Node getBy(String id);
}
