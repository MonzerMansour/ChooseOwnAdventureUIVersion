package Classes.Rooms;

import Classes.Items.Item;
import Classes.Actors.NPC;
import Classes.Utility;
import Enums.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    private List<Item> Storage;
    private List<NPC> InRoom;
    private String description;
    private HashMap<Direction, Room> hm = new HashMap<>();
    private HashMap<Direction, Boolean> hmLocked = new HashMap<>();
    private String name;
    private int x, y;
    private boolean isVisited = false;

    public Room(String description, String name) {
        this.Storage = new ArrayList<>();
        this.InRoom = new ArrayList<>();
        this.description = description;
        this.name = name;
        Rooms.allRooms.add(this);
    }

    public String getDescription() {
        return description;
    }

    public void showDetails() {
        if (!Storage.isEmpty()) {
            Utility.say("🛠️ You see the following items:");
            for (Item item : Storage) {
                Utility.say("- " + item.description);
            }
        } else {
            Utility.say("There are no items here.");
        }

        if (!InRoom.isEmpty()) {
            Utility.say("👥 You see some people:");
            for (NPC npc : InRoom) {
                Utility.say("- " + npc.getName() + ": " + npc.getDescription());
            }
        } else {
            Utility.say("There is no one else here.");
        }
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setVisited(){
        this.isVisited = true;
    }


    public void addItem(Item item) {
        this.Storage.add(item);
    }

    public void addNPC(NPC npc) {
        this.InRoom.add(npc);
    }

    public void removeNPC(NPC npc) {
        this.InRoom.remove(npc);
    }

    public void addOption(Direction direction, Room room) {
        hmLocked.put(direction, false);
        hm.put(direction, room);
    }

    public boolean isLocked(Direction direction) {
        Boolean isLocked = hmLocked.get(direction);
        return isLocked != null && isLocked;
    }

    public void lockDirection(Direction direction) {
        hmLocked.put(direction, true);
    }

    public void unlockDirection(Direction direction) {
        hmLocked.put(direction, false);
    }

    public HashMap<Direction, Room> getAdjecent() {
        return hm;
    }

    public void removeItem(Item item) {
        this.Storage.remove(item);
    }

    public List<Item> getItems() {
        return Storage;
    }

    public List<NPC> getNPCs() {
        return InRoom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public String getName() {
        return name;
    }
}