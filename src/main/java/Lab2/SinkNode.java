package Lab2;

import Sim.*;

import java.text.DecimalFormat;

public class SinkNode extends Node {
    private int receivedMsg = 0;
    private DecimalFormat df = new DecimalFormat("#.##");
    public SinkNode(int network, int node) {
        super(network, node);
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if(ev instanceof Message) {
            double time = SimEngine.getTime();
            receivedMsg++;
            System.out.println("SinkNode recv msg with seq: " +
                    ((Message) ev).seq() +
                    " at time " +
                    time +
                    " at " + df.format(receivedMsg / time) + " msg/timeUnit"
            );
        }
    }
}
