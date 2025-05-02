package Classes.Rooms;

import Classes.Actors.NPCs;
import Classes.Items.Item;
import Classes.Items.Items;
import Classes.Actors.NPC;

import java.util.ArrayList;
import java.util.List;

import static Enums.Direction.*;

public class Rooms {
    public static final List<Room> allRooms = new ArrayList<>();
    public static final Room BUNKER = new Room("A dark underground bunker filled with old supplies.", "Bunker");
    public static final Room SAFEHOUSE = new Room("A reinforced building where survivors gather.", "Safehouse");
    public static final Room MARKET = new Room("An abandoned market, looted but still has some supplies.", "Market");

    public static final Room CITY_GATE = new Room("The entrance to the ruined city, blocked by wreckage.", "City Gate");
    public static final Room CITY_CENTER = new Room("The heart of the city, but it's eerily empty.", "City Center");
    public static final Room POLICE_STATION = new Room("A destroyed police station with abandoned weapons.", "Police Station");

    public static final Room ZOMBIE_ALLEY = new Room("An alley crawling with zombies. The stench of death fills the air.", "Zombie Alley");
    public static final Room HOSPITAL = new Room("A ruined hospital. Some medical supplies might remain.", "Hospital");
    public static final Room MILITARY_BASE = new Room("A sealed military base. Who knows what’s inside?", "Military Base");
    public static final Room ZOMBIE_CASTLE_GATES = new Room("The entrance to a castle filled with zombies. " +
            "Is this where the zombies are coming from?", "Zombie Castle Gates");
    public static final Room ZOMBIE_CASTLE_MAIN_HALL = new Room("The center of the castle. It seems empty.", "Zombie Castle Main Hall");
    public static final Room ZOMBIE_CASTLE_KITCHEN = new Room("A kitchen stocked with plenty of food", "Zombie Castle Kitchen");
    public static final Room ZOMBIE_CASTLE_PRISON = new Room("A prison where captured civilians collect", "Zombie Castle Prison");
    public static final Room ZOMBIE_CASTLE_GRAND_HALL = new Room("The hall that leads to the throne of the King Zombie. BE CAREFUL!", "Zombie Castle Grand Hall");
    public static final Room ZOMBIE_CASTLE_THRONE_ROOM = new Room("You've reached the heart of darkness—where the Zombie King lurks.", "Zombie Castle Throne Room");
    public static final Room ZOMBIE_CASTLE_SNACK_ROOM = new Room("An old snack room with scattered food wrappers and a strange lingering smell.", "Zombie Castle Snack Room");
    public static final Room ZOMBIE_CASTLE_WEAPON_ROOM = new Room("A weapon room filled with various blades, guns, and ammo, ready for battle.", "Zombie Castle Weapon Room");
    public static final Room MORGUE = new Room("A morgue filled with cold drawers and the heavy silence of the dead.", "Morgue");
    public static final Room BEDROOM = new Room("A dark, crumbling bedroom filled with broken beds where zombies toss and groan in restless sleep. BE QUITE!", "Bedroom");


    public static void buildRooms() {
        BUNKER.addOption(EAST, SAFEHOUSE);
        BUNKER.addOption(SOUTH, CITY_GATE);



        SAFEHOUSE.addOption(WEST, BUNKER);
        SAFEHOUSE.addOption(EAST, POLICE_STATION);
        SAFEHOUSE.addOption(SOUTH, CITY_CENTER);

        SAFEHOUSE.addItem(Items.firstAidKit);
        SAFEHOUSE.addNPC(new NPC("Survivor", "Please, bring me a flashlight!", Items.flashlight, Items.rustyPistol, false));



        MARKET.addOption(WEST, CITY_CENTER);
        MARKET.addOption(SOUTH, MILITARY_BASE);
        MARKET.addOption(NORTH, POLICE_STATION);

        MARKET.addItem(Items.cannedFood);
        MARKET.addNPC(new NPC("Trader", "I'll trade you something good for some canned food.", Items.cannedFood, Items.glassBottle, false));



        CITY_GATE.addOption(NORTH, BUNKER);
        CITY_GATE.addOption(EAST, CITY_CENTER);
        CITY_GATE.addOption(SOUTH, ZOMBIE_ALLEY);



        CITY_CENTER.addOption(NORTH, SAFEHOUSE);
        CITY_CENTER.addOption(WEST, CITY_GATE);
        CITY_CENTER.addOption(EAST, MARKET);
        CITY_CENTER.addOption(SOUTH, HOSPITAL);




        POLICE_STATION.addOption(WEST, SAFEHOUSE);
        POLICE_STATION.addOption(SOUTH, MARKET);

        POLICE_STATION.addItem(Items.flashlight);



        ZOMBIE_ALLEY.addOption(NORTH, CITY_GATE);
        ZOMBIE_ALLEY.addOption(EAST, HOSPITAL);

        ZOMBIE_ALLEY.addOption(SOUTH, ZOMBIE_CASTLE_GATES);
        ZOMBIE_ALLEY.addNPC(NPCs.zombieLv1i1);
        ZOMBIE_ALLEY.lockDirection(SOUTH);
        NPCs.zombieLv1i1.addLockedDirection(SOUTH);



        HOSPITAL.addOption(NORTH, CITY_CENTER);
        HOSPITAL.addOption(WEST, ZOMBIE_ALLEY);
        HOSPITAL.addOption(EAST, MILITARY_BASE);
        HOSPITAL.addOption(SOUTH, MORGUE);

        HOSPITAL.addItem(Items.firstAidKit);



        MILITARY_BASE.addOption(NORTH, MARKET);
        MILITARY_BASE.addOption(WEST, HOSPITAL);

        MILITARY_BASE.addItem(Items.glassBottle);



        ZOMBIE_CASTLE_GATES.addOption(NORTH, ZOMBIE_ALLEY);
        ZOMBIE_CASTLE_GATES.addOption(SOUTH, ZOMBIE_CASTLE_MAIN_HALL);

        ZOMBIE_CASTLE_GATES.addNPC(NPCs.zombieLv1i2);
        ZOMBIE_CASTLE_GATES.addNPC(NPCs.zombieLv2i1);
        ZOMBIE_CASTLE_GATES.lockDirection(SOUTH);
        NPCs.zombieLv2i1.addLockedDirection(SOUTH);



        ZOMBIE_CASTLE_MAIN_HALL.addOption(NORTH, ZOMBIE_CASTLE_GATES);
        ZOMBIE_CASTLE_MAIN_HALL.addOption(WEST, ZOMBIE_CASTLE_KITCHEN);
        ZOMBIE_CASTLE_MAIN_HALL.addOption(SOUTH, ZOMBIE_CASTLE_GRAND_HALL);
        ZOMBIE_CASTLE_MAIN_HALL.addOption(EAST, ZOMBIE_CASTLE_SNACK_ROOM);




        ZOMBIE_CASTLE_KITCHEN.addOption(WEST, ZOMBIE_CASTLE_PRISON);
        ZOMBIE_CASTLE_KITCHEN.addOption(EAST, ZOMBIE_CASTLE_MAIN_HALL);

        ZOMBIE_CASTLE_KITCHEN.addItem(Items.meat);
        ZOMBIE_CASTLE_KITCHEN.addItem(Items.wheat);
        ZOMBIE_CASTLE_KITCHEN.addNPC(NPCs.zombieLv2i2);
        ZOMBIE_CASTLE_KITCHEN.lockDirection(WEST);
        NPCs.zombieLv2i2.addLockedDirection(WEST);

        ZOMBIE_CASTLE_PRISON.addOption(EAST, ZOMBIE_CASTLE_KITCHEN);

        ZOMBIE_CASTLE_PRISON.addNPC(new NPC("Cook", "I'll make you a nice meal if you give me meat and rice.", Items.meat, Items.wheat, Items.cookedMeal, false));
        ZOMBIE_CASTLE_PRISON.addNPC(new NPC("Thirsty maid", "Please bring me something to scoop up water I need to get some water from the well", Items.glassBottle, Items.AK47, false));



        ZOMBIE_CASTLE_GRAND_HALL.addOption(NORTH, ZOMBIE_CASTLE_MAIN_HALL);
        ZOMBIE_CASTLE_GRAND_HALL.addOption(SOUTH, ZOMBIE_CASTLE_THRONE_ROOM);
        ZOMBIE_CASTLE_GRAND_HALL.addOption(EAST, BEDROOM);

        ZOMBIE_CASTLE_GRAND_HALL.addNPC(NPCs.zombieLv2i3);
        ZOMBIE_CASTLE_GRAND_HALL.addNPC(NPCs.zombieLv2i4);
        ZOMBIE_CASTLE_GRAND_HALL.addNPC(NPCs.zombieLv2i5);
        ZOMBIE_CASTLE_GRAND_HALL.addNPC(NPCs.zombieLv2i6);
        ZOMBIE_CASTLE_GRAND_HALL.addNPC(NPCs.zombieLv3i1);
        ZOMBIE_CASTLE_GRAND_HALL.lockDirection(SOUTH);
        NPCs.zombieLv3i1.addLockedDirection(SOUTH);

        ZOMBIE_CASTLE_THRONE_ROOM.addOption(NORTH, ZOMBIE_CASTLE_GRAND_HALL);

        ZOMBIE_CASTLE_THRONE_ROOM.addNPC(NPCs.zombieKing);
        ZOMBIE_CASTLE_THRONE_ROOM.lockDirection(NORTH);
        NPCs.zombieKing.addLockedDirection(NORTH);



        ZOMBIE_CASTLE_SNACK_ROOM.addOption(WEST, ZOMBIE_CASTLE_MAIN_HALL);
        ZOMBIE_CASTLE_SNACK_ROOM.addOption(EAST, ZOMBIE_CASTLE_WEAPON_ROOM);
        ZOMBIE_CASTLE_SNACK_ROOM.addOption(SOUTH, BEDROOM);

        ZOMBIE_CASTLE_SNACK_ROOM.addNPC(NPCs.zombieLv1i4);
        ZOMBIE_CASTLE_SNACK_ROOM.addNPC(NPCs.zombieLv1i3);
        ZOMBIE_CASTLE_SNACK_ROOM.addNPC(NPCs.zombieLv2i7);
        ZOMBIE_CASTLE_SNACK_ROOM.addNPC(NPCs.zombieLv3i2);
        ZOMBIE_CASTLE_SNACK_ROOM.lockDirection(EAST);
        NPCs.zombieLv3i2.addLockedDirection(EAST);

        ZOMBIE_CASTLE_SNACK_ROOM.addItem(Items.takis);
        ZOMBIE_CASTLE_SNACK_ROOM.addItem(Items.oreos);
        ZOMBIE_CASTLE_SNACK_ROOM.addItem(Items.popsicle);

        ZOMBIE_CASTLE_WEAPON_ROOM.addOption(WEST, ZOMBIE_CASTLE_SNACK_ROOM);
        ZOMBIE_CASTLE_WEAPON_ROOM.addItem(Items.AK47);
        ZOMBIE_CASTLE_WEAPON_ROOM.addItem(Items.knife);


        MORGUE.addOption(NORTH, HOSPITAL);

        MORGUE.addItem(Items.deathalizer);
        MORGUE.addItem(Items.blazeMist);
        MORGUE.addNPC(new NPC("GraveReaper", "I need more souls kill 2 zombies and bring me there souls to recieve a reward.", Items.soul, Items.soul, Items.boneSawBlade, false));


        BEDROOM.addOption(NORTH, ZOMBIE_CASTLE_SNACK_ROOM);
        BEDROOM.addOption(WEST, ZOMBIE_CASTLE_GRAND_HALL);

        BEDROOM.addNPC(new NPC("Scientist","I have devoted my life to finding a deathalizer and now I am trapped here. Please bring it to me!", Items.deathalizer, Items.lifePack, false));
        BEDROOM.addItem(Items.smokeBomb);
    }
}