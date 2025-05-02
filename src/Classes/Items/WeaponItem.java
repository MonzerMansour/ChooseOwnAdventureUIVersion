package Classes.Items;

import java.awt.*;

public class WeaponItem extends Item{
    int damageMultiplier;
    private String attackSequence;
    public WeaponItem(String name, String description, boolean unbreakable, int damageMultiplier, String attackSequence) {
        super(name, description, unbreakable);
        this.damageMultiplier = damageMultiplier;
        this.attackSequence = attackSequence;
    }
    public int getDamageMultiplier(){
        return damageMultiplier;
    }

    public String getAttackSequence() {
        return attackSequence;
    }
}