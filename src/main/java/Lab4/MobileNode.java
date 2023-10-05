package Lab4;

import Sim.*;

public class MobileNode extends Node {
    private NetworkAddr homeAgentAddress;
    private NetworkAddr hoa;
    private NetworkAddr coa;
    public MobileNode(int network, int node, NetworkAddr homeAgentAddress) {
        super(network, node);

        this.homeAgentAddress = homeAgentAddress;
        this.hoa = this._id;
    }

    public void moveTo(SimEnt link, SimEnt router, int moveAfter) {
        send(this, new MoveEvent(link, router), moveAfter);
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if (ev instanceof MoveEvent) {
            System.out.println("Node "+_id.networkId()+ "." + _id.nodeId() +" moves to r3 at time "+SimEngine.getTime());

            setPeer(((MoveEvent) ev).getLink());
            send(((MoveEvent) ev).getRouter(), new AddressReqMsg(((MoveEvent) ev).getLink()), 0);
        }

        if (ev instanceof IPOfferMsg){
            _id = ((IPOfferMsg) ev).getNetworkAddr();
            coa = _id;
            System.out.println("Node "+_id.networkId()+ "." + _id.nodeId() +" sent binding update at time "+ SimEngine.getTime());
            send(_peer, new BindingUpdateMsg(_id, homeAgentAddress, 0), 0);
        }


        super.recv(src, ev);
    }
}
