package game.actions.transActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.economy.transactions.Exchangeable;

/**
 * Exchange Action, assumed to be weapon for now.
 * @author Kok Tim Ming
 */
public class ExchangeAction extends Action {
    private Exchangeable in;
    private Exchangeable out;

    /**
     * Constructor
     * @param in Exchangable to exchange in
     * @param in Exchangable to exchange out
     */
    public ExchangeAction(Exchangeable in, Exchangeable out){
        this.in = in;
        this.out = out;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        in.exchangeIn(actor);
        out.exchangeOut(actor);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s exchanges %s for %s",actor,out,in);
    }
}
