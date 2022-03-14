package filipeex.bigeculty.changes;

import filipeex.bigeculty.classes.Chat;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WaterLaunch {

    public static void launch(Player p) {
        Vector v = new Vector();
        v.setY(100);
        p.setVelocity(v);
        p.sendTitle(Chat.c("&9&lInto the Sky"), "Well, no water here..");
    }

}
