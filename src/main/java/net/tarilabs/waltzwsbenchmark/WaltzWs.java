package net.tarilabs.waltzwsbenchmark;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.drools.benchmark.waltz.Line;
import org.drools.runtime.StatefulKnowledgeSession;

import net.tarilabs.waltzwsbenchmark.model.WaltzDTO;

/**
 * This webservice performs the reasoning service by making use of the {@link net.tarilabs.waltzwsbenchmark.WaltzKb} Knowledge base.
 * 
 * @see org.drools.benchmark.waltz.WaltzBenchmark
 * @author tari
 *
 */
@Stateless
@WebService
public class WaltzWs {
	@EJB
	WaltzKb waltzKb;

	@WebMethod
	public String waltz(@WebParam(name="WaltzDTO")WaltzDTO dto) {

		StatefulKnowledgeSession session = waltzKb.getKbase().newStatefulKnowledgeSession();

		for (Line l : dto.getLine()) {
			session.insert(l);
		}
		session.insert(dto.getStage());
		long start = System.currentTimeMillis();
		session.setGlobal( "time", start );

		session.fireAllRules();
		long time = System.currentTimeMillis() - start;
		System.err.println( time );

		session.dispose();
		return "time: "+time;
	}
}
