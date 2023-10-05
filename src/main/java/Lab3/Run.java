package Lab3;

// An example of how to build a topology and starting the simulation engine

import Sim.Link;
import Sim.Node;
import Sim.SimEngine;

public class Run {
	public static void main (String [] args)
	{
 		//Creates two links
 		Link link1 = new Link();
		Link link2 = new Link();
		
		// Create two end hosts that will be
		// communicating via the router
		Node host1 = new Node(1,1);
		MobileNode host2 = new MobileNode(2,2);

		//Connect links to hosts
		host1.setPeer(link1);
		host2.setPeer(link2);

		// Creates as router and connect
		// links to it. Information about 
		// the host connected to the other
		// side of the link is also provided
		// Note. A switch is created in same way using the Switch class
		MobileRouter routeNode = new MobileRouter(3);
		routeNode.connectInterface(0, link1, host1);
		routeNode.connectInterface(1, link2, host2);

		// Generate some traffic
		// host1 will send 3 messages with time interval 5 to network 2, node 1. Sequence starts with number 1
		host1.startSending(2, 2, 5, 5, 1);
		host2.switchLinkAfter(10);
		// host2 will send 2 messages with time interval 10 to network 1, node 1. Sequence starts with number 10

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
