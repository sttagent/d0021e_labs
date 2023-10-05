package Lab4;

import Sim.Event;
import Sim.Message;
import Sim.NetworkAddr;
import Sim.SimEnt;

public class AddressReqMsg implements Event {
    private SimEnt link;
    public AddressReqMsg(SimEnt link) {
        this.link = link;
    }

    public SimEnt getLink() {
        return link;
    }
    @Override
    public void entering(SimEnt e) {
    }
}
