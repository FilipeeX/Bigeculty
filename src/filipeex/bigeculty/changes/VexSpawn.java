package filipeex.bigeculty.changes;

import filipeex.bigeculty.Bigeculty;
import filipeex.bigeculty.classes.Chat;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class VexSpawn {

    public static void spawnVex(Player p, Location loc) {
        p.getWorld().spawnEntity(loc, EntityType.VEX);

        String title = Chat.c(Bigeculty.messageConfig.getString("vex-title")
                .replace("%player%", p.getName()
                .replace("%prefix%", Bigeculty.prefix)));

        String subtitle = Chat.c(Bigeculty.messageConfig.getString("vex-subtitle")
                .replace("%player%", p.getName()
                .replace("%prefix%", Bigeculty.prefix)));

        p.sendTitle(title, subtitle, 5, 10, 5);
    }

}
