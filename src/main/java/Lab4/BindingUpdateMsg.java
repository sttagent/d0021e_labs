package Lab4;

import Sim.Event;
import Sim.Message;
import Sim.NetworkAddr;
import Sim.SimEnt;

public class BindingUpdateMsg extends Message {
    private NetworkAddr hoa;
    public BindingUpdateMsg(NetworkAddr from, NetworkAddr to, NetworkAddr hoa, int seq) {
        super(from, to, seq);
        this.hoa = hoa;
    }

    public NetworkAddr getHOA() {
        return hoa;
    }
}
