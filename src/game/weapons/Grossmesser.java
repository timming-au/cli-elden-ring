package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attackActions.SpinAttackAction;
import game.actions.transActions.SellAction;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Sellable;

/**
 * A portable weapon primarily held by HeavySkeletalSwordsman.<br>
 * It deals 115 damage with 85% hit rate<br>
 * Skill: Spin Attack<br>
 * Modified by: Wee Jun Lin
 * @see SpinAttackAction
 * @author Kok Tim Ming
 */
public class Grossmesser extends WeaponItem implements Sellable {

    private final SellAction sellAction = new SellAction(this);

    /**
     * Constructor.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slashes", 85);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellingPrice() {
        return 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // check if there is a trader nearby, if yes add SellAction else remove
        // will not show up on the first turn... it is a limitation
        this.removeAction(sellAction);
        if(canSell(currentLocation,actor)) {
            this.addAction(sellAction);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sell(Actor seller) {
        seller.removeWeaponFromInventory(this);
        RuneManager.getInstance().updateRune(seller, getSellingPrice());
    }

    /**
     * Returns SpinAttackAction
     * @see SpinAttackAction
     */
    public Action getSkill(Actor target, String direction) {
        return new SpinAttackAction(this);
    }
}
