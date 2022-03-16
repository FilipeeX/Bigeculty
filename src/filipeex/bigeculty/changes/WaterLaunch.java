package filipeex.bigeculty.changes;

import filipeex.bigeculty.Bigeculty;
import filipeex.bigeculty.classes.Chat;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WaterLaunch {

    public static void launch(Player p) {
        Vector v = new Vector();
        v.setY(100);
        p.setVelocity(v);

        String title = Chat.c(Bigeculty.messageConfig.getString("launch-title")
                .replace("%player%", p.getName()
                .replace("%prefix%", Bigeculty.prefix)));

        String subtitle = Chat.c(Bigeculty.messageConfig.getString("launch-subtitle")
                .replace("%player%", p.getName()
                .replace("%prefix%", Bigeculty.prefix)));

        p.sendTitle(title, subtitle, 5, 10, 5);
    }

}
