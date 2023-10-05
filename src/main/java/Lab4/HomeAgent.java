package Lab4;

import Sim.Event;
import Sim.NetworkAddr;
import Sim.Node;
import Sim.SimEnt;

import java.util.HashMap;

public class HomeAgent extends Node {
    private HashMap<NetworkAddr, NetworkAddr> routingTable = new HashMap<>();
    public HomeAgent(int network, int node) {
        super(network, node);
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if (ev instanceof BindingUpdateMsg) {
            BindingUpdateMsg msg = (BindingUpdateMsg) ev;
            routingTable.put(msg.getHOA(), msg.source());
            send(_peer, new BindingAckMsg(_id, msg.source(), 0), 0);

            System.out.println("Home agent received binding update message");
        }
        super.recv(src, ev);
    }
}
