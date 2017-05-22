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
		AgendaEventListener myAgendaEventListener = new FirsRuleAcceptAgendaEventListener();
		kieSession.addEventListener(myAgendaEventListener);
	}

	@Test
	public void test2Objects() {
		Flight flight = new Flight();
		flight.setAirline("TAM");
		flight.setRuleConvertionRate(1D);
		kieSession.insert(flight);

		Flight flight2 = new Flight();
		flight2.setRuleConvertionRate(2D);
		flight2.setAirline("TAM");
		flight2.setOrigin("GRU");
		kieSession.insert(flight2);

		kieSession.fireAllRules();

		Assert.assertEquals("Two facts must be in kiesession", 2, kieSession.getFactHandles().size());
	}

	@Test
	public void testForHaltShouldIncrementTheHigherSalience() {
		Flight flight = new Flight();
		flight.setAirline("TAM");
		flight.setRuleConvertionRate(1D);
		kieSession.insert(flight);

		Flight flight2 = new Flight();
		flight2.setRuleConvertionRate(2D);
		flight2.setAirline("TAM");
		flight2.setOrigin("GRU");
		kieSession.insert(flight2);

		kieSession.fireAllRules();

		Assert.assertEquals("Two facts must be in kiesession", 2, kieSession.getFactHandles().size());
		Assert.assertEquals(new Double(0.5), flight.getConvertionRate());
		Assert.assertEquals(new Double(0.51), flight2.getConvertionRate());
	}

	@Test
	public void testForHaltShouldIncrementTheHigherSalience2() {
		Flight flight = new Flight();
		flight.setAirline("TAM");
		flight.setRuleConvertionRate(1D);
		kieSession.insert(flight);

		Flight flight2 = new Flight();
		flight2.setRuleConvertionRate(2D);
		flight2.setAirline("TAM");
		flight2.setOrigin("GRU");
		kieSession.insert(flight2);

		// low TAM-GRU-FLN has low salience than TAM-GRU, then it should keep
		// the higher
		Flight flight3 = new Flight();
		flight3.setRuleConvertionRate(2D);
		flight3.setAirline("TAM");
		flight3.setOrigin("GRU");
		flight3.setArrival("FLN");
		kieSession.insert(flight3);

		kieSession.fireAllRules();

		Assert.assertEquals("Two facts must be in kiesession", 3, kieSession.getFactHandles().size());
		Assert.assertEquals(new Double(0.5), flight.getConvertionRate());
		Assert.assertEquals(new Double(0.51), flight2.getConvertionRate());
		Assert.assertEquals(new Double(0.51), flight3.getConvertionRate());
	}

}
