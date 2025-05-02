package Classes.Items;

import Classes.Actors.Player;
import Classes.Utility;
import java.util.Random;

public class HealingItem extends Item{
    private int amountHealed;
    public HealingItem(String name, String description, boolean unbreakable, int amountHealed) {
        super(name, description, unbreakable);
        this.amountHealed = amountHealed;
    }

    @Override
    public void useItem(Player instigator) {
        if(toString() == "takis"){
            Random rand = new Random();
            double chance = rand.nextDouble();

            if (chance < 0.2) {
                instigator.changeHealth(-15);
                Utility.say("You took 15 damage because of heartburn from the taxis.");
                return;
            }
        }
        instigator.changeHealth(amountHealed);
        instigator.inventory.remove(this);

    }
}