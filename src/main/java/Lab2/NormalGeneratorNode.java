package Lab2;

import Sim.*;

import java.text.DecimalFormat;
import java.util.Random;

public class NormalGeneratorNode extends Node {
    private DecimalFormat df = new DecimalFormat("#.##");
    Random rand = new Random();
    double mean;
    double deviation;
    double delay;
    public NormalGeneratorNode(int network, int node, double mean, double deviation) {
        super(network, node);
        this.mean = mean;
        this.deviation = deviation;

        rand = new Random();
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if (ev instanceof TimerEvent) {
            if (_stopSendingAfter > _sentmsg) {
                double time = SimEngine.getTime();
                this.delay = rand.nextGaussian() * deviation + mean;
                _sentmsg++;
                send(_peer, new Message(_id, new NetworkAddr(_toNetwork, _toHost),_seq), 0);
                send(this, new TimerEvent(), delay);
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
