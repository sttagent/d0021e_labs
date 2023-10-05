package Lab1;

import Sim.Message;
import Sim.NetworkAddr;

public class MessageWithTimestamp extends Message {
    private double timestamp;

    public MessageWithTimestamp(NetworkAddr from, NetworkAddr to, int seq, double timestamp) {
        super(from, to, seq);
        this.timestamp = timestamp;
    }

    public double getTimestamp() {
        return timestamp;
    }
}
