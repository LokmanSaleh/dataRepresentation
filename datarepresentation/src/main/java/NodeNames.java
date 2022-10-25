

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.core.audit.WorkingMemoryInMemoryLogger;
import org.drools.core.audit.event.LogEvent;
import org.drools.core.audit.event.RuleFlowNodeLogEvent;
import org.jbpm.test.JBPMHelper;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class NodeNames {

	public static void main (String arg[]) {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieBase kbase = kContainer.getKieBase("kbase");

		RuntimeManager manager = createRuntimeManager(kbase);
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		TaskService taskService = engine.getTaskService();
 
		WorkingMemoryInMemoryLogger inMemoryLogger = new WorkingMemoryInMemoryLogger((StatefulKnowledgeSession) engine.getKieSession());

		ProcessInstance processInstance = ksession.startProcess("com.bpmn.process");
		
		List<String> lastNode = new ArrayList<String>();

        for (LogEvent event : inMemoryLogger.getLogEvents()) {
            if (event instanceof RuleFlowNodeLogEvent) {
                String nodeName = ((RuleFlowNodeLogEvent) event).getNodeName();
 				if (!lastNode.contains(nodeName) && lastNode!=null) {
					lastNode.add(nodeName);
					System.out.println(nodeName);
				}
            }
        }
        
		manager.disposeRuntimeEngine(engine);
		manager.close();
	}
	
	private static RuntimeManager createRuntimeManager(KieBase kbase) {
		JBPMHelper.startH2Server();
		JBPMHelper.setupDataSource();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
			.newDefaultBuilder().entityManagerFactory(emf)
			.knowledgeBase(kbase);
		return RuntimeManagerFactory.Factory.get()
			.newSingletonRuntimeManager(builder.get(), "com.sample:example:1.0");
	}
}
