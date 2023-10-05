package Lab1;// An example of how to build a topology and starting the simulation engine

import Sim.Link;
import Sim.Router;
import Sim.SimEngine;

public class Run {
	public static void main (String [] args)
	{
 		//Creates two links
 		Link lossyLink1 = new LossyLink(1, 10, 0.5);
		Link lossyLink2 = new LossyLink(1, 20, 0.5);
		
		// Create two end hosts that will be
		// communicating via the router
		NodeWithJitterInference host1 = new NodeWithJitterInference(1,1);
		NodeWithJitterInference host2 = new NodeWithJitterInference(2,1);

		//Connect links to hosts
		host1.setPeer(lossyLink1);
		host2.setPeer(lossyLink2);

		// Creates as router and connect
		// links to it. Information about 
		// the host connected to the other
		// side of the link is also provided
		// Note. A switch is created in same way using the Switch class
		Router routeNode = new Router(2);
		routeNode.connectInterface(0, lossyLink1, host1);
		routeNode.connectInterface(1, lossyLink2, host2);
		
		// Generate some traffic
		// host1 will send 3 messages with time interval 5 to network 2, node 1. Sequence starts with number 1
		host1.startSending(2, 1, 3, 5, 1);
		// host2 will send 2 messages with time interval 10 to network 1, node 1. Sequence starts with number 10
		host2.startSending(1, 1, 2, 10, 10);
		
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
