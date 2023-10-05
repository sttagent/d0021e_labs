package Lab4;

import Sim.Event;
import Sim.Message;
import Sim.NetworkAddr;
import Sim.SimEnt;

public class BindingAckMsg extends Message {
    public BindingAckMsg(NetworkAddr from, NetworkAddr to, int seq) {
        super(from, to, seq);
    }
    @Override
    public void entering(SimEnt e) {
    }
}
