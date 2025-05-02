package Classes.Items;

import Classes.Actors.Player;
import Classes.Rooms.Room;
import Classes.Utility;
import Enums.Direction;
import Interphases.InteractAble;
import Classes.Actors.NPC;
import Classes.Actors.NPCs;

import java.util.Objects;

public class Item implements InteractAble {
    public String description;
    private String name;
    public boolean unbreakable;

    public Item(String name, String description, boolean unbreakable) {
        this.description = description;
        this.unbreakable = unbreakable;
        this.name = name;
    }
    public Item(String description, boolean unbreakable) {
        this.description = description;
        this.unbreakable = unbreakable;
    }

    public Item(String description) {
        this.description = description;
        this.unbreakable = false;
    }
    public Item(String name, String description) {
        this.description = description;
        this.unbreakable = false;
        this.name = name;
    }

    public void useItem(Player instigator){
        if (Objects.equals(name, "Smoke Bomb")) {
            Room currentRoom = instigator.getLocation();
            boolean hasZombies = currentRoom.getNPCs().stream().anyMatch(NPC::isHostile);  // Check hostility of NPCs

            if (hasZombies) {
                Utility.say("💨 You throw the Smoke Bomb! A thick cloud forms...");
                Utility.say("🫥 You are able to sneak by the zombies unnoticed!");
            } else {
                Utility.say("💨 You throw the Smoke Bomb... but nothing happens.");
                Utility.say("😐 Well, looks like that was a waste — there were no zombies to hide from.");
            }

            // Unlock all adjacent directions
            for (Direction direction : currentRoom.getAdjecent().keySet()) {
                currentRoom.unlockDirection(direction);
            }

            // Remove the item from inventory unless unbreakable
            if (!this.unbreakable) {
                instigator.inventory.remove(this);
            }

            return;
        }

        Utility.say("Huh what happened. Nothing???");
    }

    public void displayItem() {
        Utility.say(description);
    }

    public String getName() {
        return name;
    }

    public void interact(Player instigator) {}

    public String toString(){
        return name;
    }
}