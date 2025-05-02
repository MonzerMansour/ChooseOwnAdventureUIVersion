package Classes.Actors;

import Classes.Items.Item;
import Classes.Items.Items;
import Classes.Items.WeaponItem;
import Classes.Rooms.Room;
import Classes.UI;
import Classes.Utility;
import Enums.Direction;

import java.util.*;

import static Enums.Direction.NORTH;

public class Player {
    public final List<Item> inventory = new ArrayList<>();
    private Room location;
    private final List<String> possibleOptions = List.of("Move", "Pick Up", "Interact", "Look Around", "Inventory", "Use");

    private int health = 100;

    public Room getLocation() {
        return this.location;
    }

    public void changeHealth(int change) {
        health = Math.clamp(change + health, 0, 100);
        if (change > 0) {
            Utility.say("You were healed " + change + "%.");
        } else {
            Utility.say("You have taken " + change * -1 + "% of damage.");
        }
        if (health <= 0) {
            Utility.say("Your health has reached 0!");
            boolean hasLifePack = false;
            for (Item item : inventory) {
                if (item.toString().equalsIgnoreCase("Life Pack")) {
                    hasLifePack = true;
                    break;
                }
            }
            if (hasLifePack) {
                Utility.say("You used a Life Pack to revive yourself. Good thing you had that! ");
                health = 50;
                inventory.removeIf(item -> item.toString().equalsIgnoreCase("Life Pack"));
            }else{
                Utility.say("You have DIED!!!");
                displayInventory();;
            }
        }
        Utility.say("Your health is now " + health + "%.");
        UI.updateHealth();


    }


    public void pickUpItem(Item item) {
        Random rand = new Random();

        if (item.unbreakable || rand.nextDouble() < 0.8) {
            inventory.add(item);
            Utility.say("You stored " + item.description + " for later use.");
            location.removeItem(item);
        } else {
            Utility.say("Oh no! The " + item.description + " broke as you tried to pick it up.");
            location.removeItem(item);
        }
    }


    public void receiveItem(Item item) {
        inventory.add(item);
        Utility.say("You received " + item.description + "!");
    }

    public boolean hasWeaponItem() {
        for (Item item : inventory) {
            if (item instanceof WeaponItem) {
                return true;
            }
        }
        return false;
    }

    public void move(Direction direction) {
        if (location.getAdjecent().containsKey(direction)) {
            if (location.isLocked(direction)) {
                Utility.say("Can't go that way yet");
            } else {
                setLocation(location.getAdjecent().get(direction));
            }
        } else {
            switch (direction) {
                case NORTH:
                    Utility.say("The northern path is blocked by collapsed buildings.");
                    break;
                case SOUTH:
                    Utility.say("A horde of zombies swarms the southern roads. You can't go that way.");
                    break;
                case WEST:
                    Utility.say("The western side is flooded and completely impassable.");
                    break;
                case EAST:
                    Utility.say("Military barricades block the way east. No way through.");
                    break;
                default:
                    Utility.say("You can't go that way.");
                    break;
            }
        }
    }

    public void displayInventory() {

        if (inventory.isEmpty()) {
            Utility.say("Your inventory is empty");
        } else {
            Utility.say("Your inventory:");
            inventory.forEach(Item::displayItem);
        }
    }

    public void takeAction(String[] userInput) {
        if (health == 0) {
            return;
        }
        String action = userInput[0];

        switch (action.toLowerCase().strip()) {
            case "move":
                Direction moveDirection = null;
                if (userInput.length < 2) {
                    String directionInput = Utility.promptUser("Please specify a direction to move (North, South, East, West):");
                    try {
                        moveDirection = Direction.valueOf(directionInput.strip().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        Utility.say("Not a valid direction. Try again.");
                        return;
                    }
                } else {
                    try {
                        moveDirection = Direction.valueOf(userInput[1].toUpperCase());
                    } catch (IllegalArgumentException error) {
                        Utility.say("Not a valid direction. Please say one of the following: North, South, East, or West");
                        return;
                    }
                }
                move(moveDirection);
                break;
            case "interact":
                if (!location.getNPCs().isEmpty()) {
                    List<NPC> avaliableNPC = location.getNPCs();
                    String selectedNPC;
                    if (avaliableNPC.size() > 1) {
                        if (userInput.length == 1) {
                            avaliableNPC.forEach(npc -> Utility.say(npc.toString()));
                            String answer = Utility.promptUser("Which NPC would you like to interact with? ");
                            selectedNPC = answer;
                        } else {
                            selectedNPC = userInput[1];
                        }
                        boolean npcFound = false;
                        NPC npcToInteract = null;
                        for (NPC npc : avaliableNPC) {
                            if (selectedNPC.replaceAll(" ", "").equalsIgnoreCase(npc.getName().replaceAll(" ", "").strip())) {
                                npcToInteract = npc;
                                npcFound = true;
                            }
                        }
                        if (!npcFound) {
                            //Utility.say("No such NPC is Found");
                        }else {
                            npcToInteract.interact(this);
                        }
                    } else {
                        avaliableNPC.getFirst().interact(this);
                    }
                } else {
                    Utility.say("There's no one to interact with here.");
                }
                break;
            case "pick":
                String itemName = null;
                boolean mistakeType = userInput.length == 2 && !userInput[1].equalsIgnoreCase("up");
                if (userInput.length < 2 || mistakeType){
                    String answer = Utility.promptUser("Did you mean 'pick up'?");
                    if (answer.equalsIgnoreCase("yes")) {
                        itemName = Utility.promptUser("What would you like to pick up?");
                    } else if (answer.equalsIgnoreCase("no")) {
                        break;
                    }else {
                        Utility.say("Not a valid response");
                        break;
                    }
                } else if (userInput.length == 2 && userInput[1].strip().equalsIgnoreCase("up")) {
                    itemName = Utility.promptUser("What would you like to pick up?");
                } else if (userInput.length > 2 && !userInput[1].equalsIgnoreCase("up")) {
                    String answer = Utility.promptUser("Did you mean 'pick up'?");
                    if (answer.equalsIgnoreCase("yes")) {
                        itemName = String.join(" ", Arrays.copyOfRange(userInput, 2, userInput.length));
                    } else if (answer.equalsIgnoreCase("no")) {
                        break;
                    }else {
                        Utility.say("Not a valid response");
                        break;
                    }
                } else {
                    itemName = String.join(" ", Arrays.copyOfRange(userInput, 2, userInput.length));
                }
                boolean itemFound = false;
                for (Item item : location.getItems()) {
                    if (item.getName().replaceAll(" ", "").equalsIgnoreCase(itemName.replaceAll(" ", ""))) {
                        pickUpItem(item);
                        itemFound = true;
                        break;
                    }
                }
                if (!itemFound) {
                    Utility.say("That item is not here.");
                }
                break;
            case "look":
                lookAround();
                break;
            case "help":
                Utility.explainRules();
            case "use":
                userInput[0] = "";
                String selectedItem = String.join( " ", userInput).strip();

                for (Item item : inventory) {
                    if (item.toString().replaceAll(" ", "").equalsIgnoreCase(selectedItem.replaceAll(" ", ""))) {
                        item.useItem(this);
                        return;
                    }
                }
                Utility.say("That item is not in your inventory.");


            case "inventory":
                displayInventory();
                break;
            default:
                Utility.say("Options: ");
                possibleOptions.forEach(System.out::println);
                break;
        }
    }

    public void lookAround() {
        if (location != null) {
            Utility.say("🔍 You look around...");
            location.showDetails();
        } else {
            Utility.say("Error: You are not in any location.");
        }
    }


    public void setLocation(Room newRoom) {
        location = newRoom;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }



}