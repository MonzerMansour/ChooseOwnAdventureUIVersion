import Classes.Actors.Player;
import Classes.Items.Items;
import Classes.UI;
import Classes.Utility;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Player mainPlayer = new Player();
        Utility.onBeginPlay(mainPlayer);
        UI.runUI(mainPlayer);
        Utility.explainRules(Boolean.TRUE);


//        mainPlayer.takeAction();
    }
}