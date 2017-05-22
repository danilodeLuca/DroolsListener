package br.com.droolsListener;

import java.util.List;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;

public class FirsRuleAcceptAgendaEventListener implements AgendaEventListener {

	@Override
	public void afterMatchFired(AfterMatchFiredEvent arg0) {
	}

	@Override
	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
	}

	@Override
	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
	}

	@Override
	public void agendaGroupPopped(AgendaGroupPoppedEvent arg0) {
	}

	@Override
	public void agendaGroupPushed(AgendaGroupPushedEvent arg0) {
	}

	@Override
	public void beforeMatchFired(BeforeMatchFiredEvent arg0) {
		List<Object> matchedObjects = arg0.getMatch().getObjects();
		for (Object nextMatchedObject : matchedObjects) {
			if (nextMatchedObject instanceof Flight) {
				if (((Flight) nextMatchedObject).hasConvertionRate()) {
					((Flight) nextMatchedObject).setAlreadyRun(true);
				}

				System.out.println("beforeMatchFired:" + ((Flight) nextMatchedObject).toString());
			}
		}
	}

	@Override
	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
	}

	@Override
	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void matchCancelled(MatchCancelledEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
		// matchCreatedEvent.getKieRuntime().halt();
	}

}
