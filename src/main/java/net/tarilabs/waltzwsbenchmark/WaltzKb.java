package net.tarilabs.waltzwsbenchmark;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Singleton EJB constructs the Knowledge Base of the Waltz benchmark used by the webservice.
 * 
 * @see org.drools.benchmark.waltz.WaltzBenchmark
 * @author tari
 *
 */
@Singleton
@Startup
public class WaltzKb {
	private static final transient Logger logger = LoggerFactory.getLogger(WaltzKb.class);
	private KnowledgeBase kbase;

	@PostConstruct
	public void init() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource("waltz.drl", WaltzKb.class), ResourceType.DRL );
        if (kbuilder.hasErrors()) {
        	for (KnowledgeBuilderError error : kbuilder.getErrors()) {
        		logger.error("DRL Error "+error);
        	}
        }
        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( pkgs );
	}

	public KnowledgeBase getKbase() {
		return kbase;
	}
}
