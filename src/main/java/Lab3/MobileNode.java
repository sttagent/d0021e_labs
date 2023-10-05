package Lab3;

import Sim.*;

public class MobileNode extends Node {
    public MobileNode(int network, int node) {
        super(network, node);
    }

    public void switchLinkAfter(int switchInterfaceAfter) {
        send(((Link) _peer).getConnectorB(), new SwitchLinkEvent(2, this), switchInterfaceAfter);
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if(ev instanceof Message){
            System.out.println("Node "+_id.networkId()+ "." + _id.nodeId() +" receives message with seq: "+((Message) ev).seq() + " at time "+ SimEngine.getTime());
        }
    }


}
