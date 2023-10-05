package Lab4;

import Sim.Event;
import Sim.SimEnt;

public class MoveEvent implements Event {
    private SimEnt link;
    private SimEnt router;

    public MoveEvent(SimEnt link, SimEnt router) {
        this.link = link;
        this.router = router;
    }

    public SimEnt getLink() {
        return link;
    }

    public SimEnt getRouter() {
        return router;
    }
    @Override
    public void entering(SimEnt e) {
    }
}
