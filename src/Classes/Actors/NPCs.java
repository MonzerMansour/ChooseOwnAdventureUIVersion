package Classes.Actors;

import java.util.List;
import java.util.Random;
public class NPCs {
    public static List<String> zombieDialogues = List.of(
            "Grrrr...",
            "Brains...",
            "Hungry...",
            "Must feed",
            "Raaah!",
            "No escape",
            "Get back!",
            "Chomp!",
            "Must hunt",
            "Raaaaaah",
            "Stay away!",
            "Feed me!",
            "Rrraaah!",
            "Grrr... help",
            "I’m hungry",
            "Grrrrr...",
            "Find food",
            "Move now!",
            "Must eat",
            "Get lost!"
    );
    private static Random rand = new Random();
    public static String getRandomZombieDialogue() {
        return zombieDialogues.get(rand.nextInt(zombieDialogues.size()));
    }
    public static final NPC zombieKing = new NPC("ZombieKing","I am the King of the Zombies, do you dare to face me?", null, null, true, 5);
    public static final NPC zombieLv1i1 = createGenericZombie(1);
    public static final NPC zombieLv1i2 = createGenericZombie(1);
    public static final NPC zombieLv1i3 = createGenericZombie(1);
    public static final NPC zombieLv1i4 = createGenericZombie(1);
    public static final NPC zombieLv2i1 = createGenericZombie(2);
    public static final NPC zombieLv2i2 = createGenericZombie(2);
    public static final NPC zombieLv2i3 = createGenericZombie(2);
    public static final NPC zombieLv2i4 = createGenericZombie(2);
    public static final NPC zombieLv2i5 = createGenericZombie(2);
    public static final NPC zombieLv2i6 = createGenericZombie(2);
    public static final NPC zombieLv2i7 = createGenericZombie(2);
    public static final NPC zombieLv3i1 = createGenericZombie(3);
    public static final NPC zombieLv3i2 = createGenericZombie(3);

    public static NPC createGenericZombie(int level) {
        return new NPC("ZombieLv" + level, getRandomZombieDialogue(), null, null, true, level-1);

    }
}