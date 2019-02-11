package app.mzperx.hmcConverter.dao.implementation.memory;

import app.mzperx.hmcConverter.dao.ArchEdContextDao;
import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ArchedContextDaoMem implements ArchEdContextDao {
    private static final Logger logger = LoggerFactory.getLogger(ArchEdContext.class);

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
        logger.debug("Looking for context: " + name);
        for (ArchEdContext c : CONTEXTS){
            if (c.getName().equals(name)){
                logger.info("Returning context: " + name);
                return c;
            }
        }
        logger.warn("NOT FOUND: context " + name);
        return null;
    }

}
