package app.mzperx.hmcConverter.dao;

import app.mzperx.hmcConverter.hmcgraph.Edge;

import java.util.List;

public interface EdgeDao {
    void addEdge(Edge edge);
    List<Edge> getAllEdges();
    Edge getBy(String id);
}
