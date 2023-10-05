package Lab4;

import Sim.*;

import java.util.HashMap;

public class MobileRouter extends Router {
    private int networkId;
    private HashMap<Integer, Integer> routingTable = new HashMap<>();
    public MobileRouter(int interfaces, int networkId) {
        super(interfaces);
        this.networkId = networkId;
    }
    public int getNetworkId() {
        return networkId;
    }

    protected SimEnt getInterface(int networkAddress) {
        SimEnt routerInterface = _routingTable[0].link();
        for (int i = 0; i < _interfaces; i++) {
            if(_routingTable[i] == null) {
                continue;
            }

            SimEnt node = _routingTable[i].node();
            if (node instanceof MobileRouter) {
                if (((MobileRouter) node).getNetworkId() == networkAddress) {
                    routerInterface = _routingTable[i].link();
                }
            }
            if (node instanceof Node) {
                if(((Node) _routingTable[i].node()).getAddr().networkId() == networkAddress) {
                    routerInterface = _routingTable[i].link();
                }
            }
        }
        return routerInterface;
    }

    @Override
    public void recv(SimEnt source, Event event) {
        if (event instanceof Message) {
            System.out.println("Router" + this.networkId + "handles packet with seq: " + ((Message) event).seq()+" from node: "+((Message) event).source().networkId()+"." + ((Message) event).source().nodeId() );
            SimEnt sendNext = this.getInterface(((Message) event).destination().networkId());
            System.out.println("Router" + this.networkId + " sends to node: " + ((Message) event).destination().networkId()+"." + ((Message) event).destination().nodeId());
            send (sendNext, event, _now);
        }

        if (event instanceof AddressReqMsg) {
            for (int i = 0; i < _interfaces; i++) {
                if (_routingTable[i] == null) {
                    connectInterface(i, ((AddressReqMsg) event).getLink(), source);
                    send(source, new IPOfferMsg(new NetworkAddr(this.networkId, i + 1)), 0);
                }

            }
        }
    }

}
