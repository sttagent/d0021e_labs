package Lab1;

import Sim.Event;
import Sim.Link;
import Sim.Message;
import Sim.SimEnt;

import java.util.Random;

public class LossyLink extends Link {
    private double delay;
    private double loss;
    private double jitter;
    private Random rand;

    private SimEnt connectorA;
    private SimEnt connectorB;

    public LossyLink(double delay, double loss, double jitter) {
        super();
        this.delay = delay;
        this.loss = loss;
        this.jitter = jitter;
        this.rand = new Random();
    }

    @Override
    public void setConnector(SimEnt connectTo) {
        if (connectorA == null) {
            connectorA = connectTo;
        }
        else {
            connectorB = connectTo;
        }
    }

    boolean isLost() {
        return rand.nextInt(101) < loss;
    }

    @Override
    public void recv(SimEnt src, Event ev) {
        if(isLost()) {
            System.out.println("Link recv msg, drops it");
            return;
        }

        if(ev instanceof Message) {
            System.out.println("Link recv msg, passes it through");
            double delay = this.delay + rand.nextDouble() * jitter;
            if (src == connectorA) {
                send(connectorB, ev, delay);
            }
            else {
                send(connectorA, ev, delay);
            }
        }

    }
}
