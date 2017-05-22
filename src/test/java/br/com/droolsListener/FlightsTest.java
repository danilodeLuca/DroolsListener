package br.com.droolsListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FlightsTest {
	private static final String OUT_CHANNEL_NAME = "out";

	private static KieContainer kieContainer;
	private KieSession kieSession;
	private MockChannel channel;

	@BeforeClass
	public static void initKieContainer() {
		KieServices kieServices = KieServices.Factory.get();
		kieContainer = kieServices.getKieClasspathContainer();
	}

	@Before
	public void initKieSession() {
		kieSession = kieContainer.newKieSession();
		channel = new MockChannel();
		kieSession.getChannels().put(OUT_CHANNEL_NAME, channel);
		AgendaEventListener myAgendaEventListener = new MyAgendaEventListener();
		kieSession.addEventListener(myAgendaEventListener);
	}

	@Test
	public void testRG_FLT_03_One() {
		Flight flight = new Flight();
		flight.setAirline("TAM");
		kieSession.insert(flight);

		Flight flight2 = new Flight();
		flight2.setAirline("XXXX");
		kieSession.insert(flight2);

		kieSession.fireAllRules();

		Assert.assertEquals("Two last event must be in working memory", 1, kieSession.getFactHandles().size());
	}

}
