package filipeex.bigeculty.changes;

import filipeex.bigeculty.classes.Chat;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class VexSpawn {

    public static void spawnVex(Player p, Location loc) {
        p.getWorld().spawnEntity(loc, EntityType.VEX);
        p.sendTitle(Chat.c("&b&lTHERES a VEX"), Chat.c("You better run.."));
    }

}
