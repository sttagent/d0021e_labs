package Lab3;

import Sim.*;

public class MobileRouter extends Router {
    public MobileRouter(int interfaces) {
        super(interfaces);
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if(ev instanceof SwitchLinkEvent){
            SwitchLinkEvent sle = (SwitchLinkEvent) ev;
            SimEnt link = null;
            NetworkAddr addr = ((Node) src).getAddr();
            System.out.println("Node " +
                    addr.networkId() + "." +
                    addr.nodeId() +" switches link to interface " +
                    sle.getNewInterface()
            );
            for(int i = 0; i < _interfaces; i++) {
                if(_routingTable[i] != null && _routingTable[i].node() == src) {
                    link = _routingTable[i].link();
                    _routingTable[i] = null;
                    break;
                }
            }
            connectInterface(sle.getNewInterface(), link, src);
        }

        if (ev instanceof Message)
        {
            System.out.println("Router handles packet with seq: " +
                    ((Message) ev).seq() +  " from node: " +
                    ((Message) ev).source().networkId() + "." +
                    ((Message) ev).source().nodeId() + " interface:" +
                    getInterfaceNumber(((Message) ev).source().networkId()));
            SimEnt sendNext = getInterface(((Message) ev).destination().networkId());
            System.out.println("Router sends to node: " +
                    ((Message) ev).destination().networkId() + "." +
                    ((Message) ev).destination().nodeId() + " interface: " +
                    getInterfaceNumber(((Message) ev).destination().networkId()));
            send (sendNext, ev, _now);

        }
    }

    public int getInterfaceNumber(int address){
        for(int i = 0; i < _interfaces; i++) {
            if(_routingTable[i] != null && ((Node) _routingTable[i].node()).getAddr().networkId() == address) {
                return i;
            }
        }
        return -1;
    }
}
