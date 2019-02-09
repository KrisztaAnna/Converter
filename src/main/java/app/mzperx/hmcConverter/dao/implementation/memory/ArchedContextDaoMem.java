package app.mzperx.hmcConverter.dao.implementation.memory;

import app.mzperx.hmcConverter.dao.ArchEdContextDao;
import app.mzperx.matrices.ArchEdContext;
import java.util.ArrayList;
import java.util.List;


public class ArchedContextDaoMem implements ArchEdContextDao {

    private static List<ArchEdContext> CONTEXTS = new ArrayList<>();
    private static ArchedContextDaoMem instance = null;
    private ArchedContextDaoMem() {
    }

    public static ArchedContextDaoMem getInstance() {
        if (instance == null) {
            instance = new ArchedContextDaoMem();
        }
        return instance;
    }

    @Override
    public List<ArchEdContext> getAllContexts(){
        return CONTEXTS;
    }

    @Override
    public void addContext(ArchEdContext context){
        CONTEXTS.add(context);
    }

    @Override
    public ArchEdContext getBy(String name){
        for (ArchEdContext c : CONTEXTS){
            if (c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

}
