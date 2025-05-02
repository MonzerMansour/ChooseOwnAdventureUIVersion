package Classes;
import Classes.Actors.Player;
import Classes.Items.Item;
import Classes.Items.Items;
import Classes.Rooms.Rooms;

import java.util.*;



public class Utility {
    public static String promptUser(String promptMessage) {
        say(promptMessage);
//        String result;
//        Scanner s = new Scanner(System.in);
//        result = s.nextLine();
        return "";
    }



    public static void say(String text) {
        //System.out.println(text);
        UI.getDescriptionArea().append("\n" + text);
        //System.out.println(UI.getDescriptionArea().getText());
    }

    public static double getRandomPercentage() {
        Random rand = new Random();
        return rand.nextDouble();
    }


    public static void onBeginPlay(Player mainPlayer) {
        Items.buildItems();
        Rooms.buildRooms();
        mainPlayer.setLocation(Rooms.CITY_CENTER);
    }

    public static void explainRules(Boolean firstUse) {
        if (firstUse) {
            say("Welcome to the Adventure Game here are all the things you can do: " +
                    "\n 1. You can press 'look around' to see all the contents of the room" +
                    "\n 2. You can type a name of a NPC in the room and press 'interact' to talk to them" +
                    "\n 3. You can type a direction ex.East or North and press 'move' to move that way" +
                    "\n 4. You can type a name of an item in the room and press 'pick up' to pick up an item" +
                    "\n 5. You can press 'help' to hear the instructions again at a later time");
        } else {
            say("Here are your options: " +
                    "\n 1. You can press 'look around' to see all the contents of the room" +
                    "\n 2. You can type a name of a NPC in the room and press 'interact' to talk to them" +
                    "\n 3. You can type a direction ex.East or North and press 'move' to move that way" +
                    "\n 4. You can type a name of an item in the room and press 'pick up' to pick up an item" +
                    "\n 5. You can press 'help' to hear the instructions again at a later time");
        }

    }

    public static void explainRules() {
        say("Here are your options: " +
                "\n 1. You can press 'look around' to see all the contents of the room" +
                "\n 2. You can type a name of a NPC in the room and press 'interact' to talk to them" +
                "\n 3. You can type a direction ex.East or North and press 'move' to move that way" +
                "\n 4. You can type a name of an item in the room and press 'pick up' to pick up an item" +
                "\n 5. You can press 'help' to hear the instructions again at a later time");

    }

    public static final List<Item> worstItems = List.of(
            Items.glassBottle,
            Items.meat,
            Items.wheat
    );

    public static final List<Item> midTierItems = List.of(
            Items.glassBottle,
            Items.meat,
            Items.wheat,
            Items.cannedFood,
            Items.popsicle,
            Items.oreos
    );

    public static final List<Item> bestItems = List.of(
            Items.cannedFood,
            Items.popsicle,
            Items.oreos,
            Items.takis,
            Items.cookedMeal,
            Items.firstAidKit
    );

    public static Item getRandomItemByLevel(int level) {
        Random rand = new Random();
        if (level == 1) {
            return worstItems.get(rand.nextInt(worstItems.size()));
        } else if (level == 2) {
            return midTierItems.get(rand.nextInt(midTierItems.size()));
        } else if (level >= 3) {
            return bestItems.get(rand.nextInt(bestItems.size()));
        }
        return null;
    }

}