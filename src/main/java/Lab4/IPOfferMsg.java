package Lab4;

import Sim.NetworkAddr;

public class IPOfferMsg implements Sim.Event{
    private NetworkAddr networkAddr;
    public IPOfferMsg(NetworkAddr networkAddr) {
        this.networkAddr = networkAddr;
    }
    public NetworkAddr getNetworkAddr() {
        return networkAddr;
    }

    @Override
    public void entering(Sim.SimEnt locale) {
    }
}
