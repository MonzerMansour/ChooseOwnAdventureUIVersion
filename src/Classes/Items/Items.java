package Classes.Items;

public class Items {
    public static Item glassBottle = new WeaponItem("Glass Bottle" ,"A glass bottle found on the ground", false, 80, "🫙 You smash the glass bottle into the zombie's face! Risky, but effective.");
    public static Item flashlight = new Item("Flashlight", "A flashlight with dim batteries", true);
    public static Item cannedFood = new HealingItem("Canned Food", "A can of preserved food", false, 30);
    public static Item firstAidKit = new HealingItem("First Aid Kit", "A first aid kit with medical supplies", false, 80);
    public static Item rustyPistol = new WeaponItem("Rusty Pistol", "A rusty pistol with only a few bullets", true, 50, "💥 You shoot the Rusty Pistol and it barely gets the job done.");
    public static Item meat = new HealingItem("Meat", "A uncooked piece of meat.", false, 10);
    public static Item wheat = new HealingItem("Wheat", "A wheat crop ready to be turned into rice", false, 5);
    public static Item cookedMeal = new HealingItem("Cooked Meal", "A cooked meal ready to be consumed", false, 70);
    public static Item AK47 = new WeaponItem("AK47", "An AK47 WOW!! A military grade weapon and very affective towards zombies", true, 20, "💥 You spray the AK47, and the zombie is shredded");
    public static Item takis = new HealingItem("Takis", "A spicy bag of Takis. Might give you a boost... or heartburn", false, 30);
    public static Item oreos = new HealingItem("Oreos", "A classic pack of Oreos. Comfort food, even during an apocalypse", false, 20);
    public static Item popsicle = new HealingItem("Popsicle", "A half-melted popsicle. Still kind of refreshing.", false, 20);
    public static Item knife = new WeaponItem("Knife", "A sharp knife, perfect for close combat", true, 70, "💥 A swift stab, the zombie is down.");
    public static Item deathalizer = new Item("Deathalizer", "A mysterious object called a deathalizer", true);
    public static Item blazeMist = new WeaponItem("Blazemist", "Something called a blazemist, it looks lije a hairspray rigged with a lighter, it blasts a quick burst of flame when sprayed and lit", true, 40, "🔥 You ignite the Blazemist! The flames engulf the zombie.");
    public static Item lifePack = new Item( "Life Pack", "A life pack that will revive you once after your death", true);
    public static Item smokeBomb = new Item("Smoke Bomb", "A small bomb that explodes into thick smoke, distracting the zombies and letting you slip past into other rooms unseen", true);
    public static Item soul = new Item("Soul", "A soul of a zombie, a faint gray mist carrying traces of its former life, now drifting weakly without purpose", true);
    public static Item boneSawBlade = new WeaponItem("BoneSawBlade", "A bone saw once used for grim work, now hums faintly with the leftover souls of the undead it once touched", true, 30, "💥 You strike with your weapon.");


    public static void buildItems() {
    }
}