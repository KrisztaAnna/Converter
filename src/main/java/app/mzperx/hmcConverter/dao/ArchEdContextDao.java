package app.mzperx.hmcConverter.dao;

import app.mzperx.hmcConverter.model.ArchEdContext;

import java.util.List;

public interface ArchEdContextDao {

    void addContext(ArchEdContext context);
    List<ArchEdContext> getAllContexts();
    ArchEdContext getBy(String name);

}
