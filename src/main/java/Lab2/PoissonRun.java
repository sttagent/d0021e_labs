package Lab2;// An example of how to build a topology and starting the simulation engine

import Sim.Link;
import Sim.Router;
import Sim.SimEngine;

public class PoissonRun {
	public static void main (String [] args)
	{
 		//Creates two links
 		Link Link1 = new Link();
		Link Link2 = new Link();
		
		// Create two end hosts that will be
		// communicating via the router
		PoissonGeneratorNode host1 = new PoissonGeneratorNode(1,1, 1);
		SinkNode host2 = new SinkNode(2,1);

		//Connect links to hosts
		host1.setPeer(Link1);
		host2.setPeer(Link2);

		// Creates as router and connect
		// links to it. Information about 
		// the host connected to the other
		// side of the link is also provided
		// Note. A switch is created in same way using the Switch class
		Router routeNode = new Router(2);
		routeNode.connectInterface(0, Link1, host1);
		routeNode.connectInterface(1, Link2, host2);
		
		// Generate some traffic
		// host1 will send 3 messages with time interval 5 to network 2, node 1. Sequence starts with number 1
		host1.startSending(2, 1, 20, 1, 1);
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
