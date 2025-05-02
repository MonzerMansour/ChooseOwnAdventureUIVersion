package Classes.Actors;

import Classes.Items.Item;
import Classes.Items.Items;
import Classes.Items.WeaponItem;
import Classes.Rooms.Room;
import Classes.UI;
import Classes.Utility;
import Enums.Direction;
import Interphases.Hostile;
import Interphases.InteractAble;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPC implements InteractAble, Hostile {
    private int level;
    private String name;
    private String dialogue;
    private Item requestedItem1;
    private Item requestedItem2;
    private Item rewardItem;
    private boolean hostile;
    private Direction lockedDirection = null;

    public NPC(String name, String dialogue, Item requestedItem, Item rewardItem, boolean hostile) {
        this.name = name;
        this.dialogue = dialogue;
        this.requestedItem1 = requestedItem;
        this.requestedItem2 = null;
        this.rewardItem = rewardItem;
        this.hostile = hostile;
        this.level = 0;
    }
    public NPC(String name, String dialogue, Item requestedItem1, Item requestedItem2, Item rewardItem, boolean hostile) {
        this.name = name;
        this.dialogue = dialogue;
        this.requestedItem1 = requestedItem1;
        this.requestedItem2 = requestedItem2;
        this.rewardItem = rewardItem;
        this.hostile = hostile;
        this.level = 0;
    }
    public NPC(String name, String dialogue, Item requestedItem, Item rewardItem, boolean hostile, int level) {
        this(name, dialogue, requestedItem, rewardItem, hostile);
        this.level = level;
    }

    public void addLockedDirection(Direction direction){
        this.lockedDirection = direction;
    }

    private void attemptQuestCompletion(Player player) {
        if (requestedItem2 == null) {
            Item requiredItem = this.getRequestedItem1();
            if (player.inventory.contains(requiredItem)) {
                player.inventory.remove(requiredItem);
                Utility.say("You gave " + requiredItem.description + " to " + getName() + ". Quest complete!");
                this.completeQuest(player);
            } else {
                Utility.say(this.getName() + " still needs " + requiredItem.description + "!");
            }
        }else{
            Item requiredItem1 = this.getRequestedItem1();
            Item requiredItem2 = this.getRequestedItem2();
            if (player.inventory.contains(requiredItem1) && player.inventory.contains(requiredItem2) ) {
                player.inventory.remove(requiredItem1);
                player.inventory.remove(requiredItem2);
                Utility.say("You gave " + requiredItem1.description + " and " + requiredItem2.description + " to " + getName() + ". Quest complete!");
                this.completeQuest(player);
            } else {
                Utility.say(this.getName() + " still needs " + requiredItem1.description + " and " + requiredItem2.description + "!");
            }
        }
    }
    public void interact(Player instigator) {
        if (hostile) {
            fight(instigator);
        } else {
            Utility.say(name + " says: \"" + dialogue + "\"");
            if (hasQuest()) {
                if (requestedItem2 == null) {
                    Utility.say(name + " says: \"I need " + requestedItem1.description + ". Can you bring it to me?\"");
                    attemptQuestCompletion(instigator);
                }else {
                    Utility.say(name + " says: \"I need " + requestedItem1.description + " and " + requestedItem2.description + ". Can you bring it to me?\"");
                    attemptQuestCompletion(instigator);
                }
            }
        }
    }

    public boolean hasQuest() {
        return requestedItem1 != null;
    }

    public Item getRequestedItem1() {
        return requestedItem1;
    }

    public Item getRequestedItem2() {
        return requestedItem2;
    }

    public String getName() {
        return name;
    }

    public Item getRewardItem() {
        return rewardItem;
    }

    public void completeQuest(Player instigator) {
        if (rewardItem != null) {
            Utility.say(name + " says: \"Thank you! Here's your reward: " + rewardItem.description + ".\"");
            instigator.receiveItem(rewardItem);
        } else {
            Utility.say(name + " says: \"Thank you! I have nothing else to give you.\"");
        }
        requestedItem1 = null;
        requestedItem2 = null;
    }


    public void fight(Player Instigator) {
        Utility.say("⚠️ " + name + " attacks you!");
        List<WeaponItem> weapons = new ArrayList<>();
        for (Item item : Instigator.inventory) {
            if (item instanceof WeaponItem) {
                weapons.add((WeaponItem) item);
            }
        }

        if (weapons.isEmpty()) {
            Utility.say("❌ You have no weapon! The " + name + " attacks and you barely escape!");
        }else if(weapons.size() == 1){
            startFight(Instigator, weapons.getFirst());
        } else {
            Utility.say("Here are all the weapons you have:");
            for (WeaponItem weapon : weapons) {
                Utility.say(weapon.toString());
            }
            String input = JOptionPane.showInputDialog(null,"Which weapon?", "Input needed", JOptionPane.QUESTION_MESSAGE);

            if (input != null) {
                for (WeaponItem items : weapons) {
                    if (items.toString().equalsIgnoreCase(input)){
                        startFight(Instigator, items);
                        return;
                    }
                }
            }
        }
    }

    public boolean isHostile(){
        return hostile;
    }

    public void removeFromLocation(Room room) {
        room.removeNPC(this);
    }

    public String getDescription() {
        return "You see " + name + ", " + dialogue;
    }

    public String toString() {
        return this.name;
    }

    private void startFight(Player Instigator, WeaponItem selectedWeapon){
        Random r = new Random();
        Instigator.changeHealth((int) (r.nextDouble() * -1 * selectedWeapon.getDamageMultiplier() * (level * 0.3 + 1)));
        Utility.say("🔫 You attack the " + name + " with your " + selectedWeapon + "!");
        Utility.say(selectedWeapon.getAttackSequence());
        Utility.say("💀 " + name + " has been defeated!");
        if(lockedDirection != null) {
            Instigator.getLocation().unlockDirection(lockedDirection);
        }
        this.removeFromLocation(Instigator.getLocation());

        Item drop = Utility.getRandomItemByLevel(level);
        if (drop != null) {
            Utility.say("🎁 You found: " + drop.description + " " + Items.soul.description);
            Instigator.receiveItem(drop);
        }
        Instigator.receiveItem(Items.soul);

        if (Utility.getRandomPercentage() < 0.3) { // 30% chance
            Instigator.inventory.remove(selectedWeapon);
            Utility.say("💔 Your " + selectedWeapon.toString() + " broke during the fight.");
        }
    }
}