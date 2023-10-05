package Lab4;

// An example of how to build a topology and starting the simulation engine

import Sim.Link;
import Sim.SimEngine;

public class Run {
	public static void main (String [] args)
	{
 		//Creates two links
 		Link linkR1toR2 = new Link();
		Link linkR1toCN = new Link();
		Link linkR2toHA = new Link();
		Link linkR2toR3 = new Link();
		Link linkR2toMN = new Link();
		Link linkR3toMN = new Link();

		// Create two end hosts that will be
		// communicating via the router
		CorespondentNode corespondentHost = new CorespondentNode(1,1);
		HomeAgent homeAgent = new HomeAgent(2,1);
		MobileNode mobileHost = new MobileNode(2,2, homeAgent.getAddr());

		//Connect links to hosts
		corespondentHost.setPeer(linkR1toCN);
		homeAgent.setPeer(linkR2toHA);
		mobileHost.setPeer(linkR2toMN);

		// Creates as router and connect
		// links to it. Information about 
		// the host connected to the other
		// side of the link is also provided
		// Note. A switch is created in same way using the Switch class
		MobileRouter router1 = new MobileRouter(2, 1);
		MobileRouter router2 = new MobileRouter(4, 2);
		MobileRouter router3 = new MobileRouter(2, 3);

		router1.connectInterface(0, linkR1toR2, router2);
		router1.connectInterface(1, linkR1toCN, corespondentHost);

		router2.connectInterface(0, linkR1toR2, router1);
		router2.connectInterface(1, linkR2toR3, router3);
		router2.connectInterface(2, linkR2toHA, homeAgent);
		router2.connectInterface(3, linkR2toMN, mobileHost);

		router3.connectInterface(0, linkR2toR3, router2);


		// Generate some traffic
		// host1 will send 3 messages with time interval 5 to network 2, node 1. Sequence starts with number 1
		// corespondentHost.startSending(2, 2, 1, 5, 1);
		mobileHost.startSending(1, 1, 5, 5, 10);
		mobileHost.moveTo(linkR3toMN, router3, 12);
		// host2 will send 2 messages with time interval 10 to network 1, node 1. Sequence starts with number 10
		// host2.StartSending(1, 1, 2, 10, 10);
		
		// Start the simulation engine and of we go!
		Thread t=new Thread(SimEngine.instance());
	
		t.start();
		try
		{
			t.join();
		}
		catch (Exception e)
		{
			System.out.println("The motor seems to have a problem, time for service?");
		}		



	}
}
