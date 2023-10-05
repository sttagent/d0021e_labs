package Lab3;

import Sim.Event;
import Sim.NetworkAddr;
import Sim.Node;
import Sim.SimEnt;

public class SwitchLinkEvent implements Event {
    private int newInterface;
    private SimEnt node;
    public SwitchLinkEvent(int newInterface, SimEnt node) {
        this.newInterface = newInterface;
        this.node = node;
    }

    public int getNewInterface() {
        return newInterface;
    }

    public SimEnt getNode() {
        return node;
    }

    @Override
    public void entering(SimEnt locale) {

    }
}
