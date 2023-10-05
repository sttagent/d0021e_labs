package Lab1;

import Sim.*;

public class NodeWithJitterInference extends Node {
    private double prevTransmitTime = 0;
    private double prevJitterVal = 0;

    public NodeWithJitterInference(int network, int node) {
        super(network, node);
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if (ev instanceof TimerEvent)
        {
            if (_stopSendingAfter > _sentmsg)
            {
                _sentmsg++;
                send(
                        _peer,
                        new MessageWithTimestamp(
                                _id,
                                new NetworkAddr(_toNetwork, _toHost),
                                _seq,
                                SimEngine.getTime()
                        ),
                        0
                );
                send(this, new TimerEvent(), _timeBetweenSending);
                System.out.println(
                        "Node " +_id.networkId()+ "." + _id.nodeId() + " sent message with seq: "+_seq + " at time "+SimEngine.getTime());
                _seq++;
            }
        }

        if (ev instanceof Message)
        {
            MessageWithTimestamp msg = (MessageWithTimestamp) ev;
            double transmitTime = SimEngine.getTime() - msg.getTimestamp();
            double jitterVal = prevJitterVal + (Math.abs(transmitTime - prevTransmitTime) - prevJitterVal) / 16;
            this.prevTransmitTime = transmitTime;
            this.prevJitterVal = jitterVal;
            System.out.println(
                    "Node "
                            + _id.networkId()+ "."
                            + _id.nodeId() + " receives message with seq: "
                            + msg.seq()
                            + " at time "
                            + SimEngine.getTime()
                            + " with jitter: " + jitterVal);

        }
    }
}
