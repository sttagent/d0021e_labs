package Lab2;

import Sim.*;

import java.text.DecimalFormat;
import java.util.Random;

public class PoissonGeneratorNode extends Node {
    private DecimalFormat df;
    double delay;
    double lambda;
    Random rand = new Random();
    public PoissonGeneratorNode(int network, int node, double lambda) {
        super(network, node);
        this.lambda = lambda;
        df = new DecimalFormat("#.##");

        rand = new Random();
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if (ev instanceof TimerEvent) {
            if (_stopSendingAfter > _sentmsg) {
                _sentmsg++;
                double time = SimEngine.getTime();
                double delay = getPoisonRandom(rand, lambda);
                send(_peer, new Message(_id, new NetworkAddr(_toNetwork, _toHost), _seq), 0);
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

    private static int getPoisonRandom(Random rand, double lambda) {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1;
        do {
            k = k + 1;
            p = p * rand.nextDouble();
        } while (p > L);
        return k - 1;
    }
}
