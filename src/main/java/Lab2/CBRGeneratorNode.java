package Lab2;

import Sim.*;

import java.text.DecimalFormat;

public class CBRGeneratorNode extends Node {
    public CBRGeneratorNode(int network, int node) {
        super(network, node);
    }
    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void recv(SimEnt src, Event ev) {
        if (ev instanceof TimerEvent) {
            if (_stopSendingAfter > _sentmsg) {
                double time = SimEngine.getTime();
                _sentmsg++;
                send(_peer, new Message(_id, new NetworkAddr(_toNetwork, _toHost),_seq),0);
                send(this, new TimerEvent(),_timeBetweenSending);
                System.out.println("GeneratorNode sent message with seq: " +
                                _seq +
                                " at time " +
                                time +
                                " at " + df.format(_sentmsg / time) + " msg/timeUnit"
                );
                _seq++;
            }
        }
    }
}
