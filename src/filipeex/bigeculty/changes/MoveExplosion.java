package filipeex.bigeculty.changes;

import filipeex.bigeculty.classes.Chat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MoveExplosion {

    public static void explode(Player p) {
        for (int i = 0; i < 1; i++) {
            p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
        }
        p.sendTitle(Chat.c("&c&lBOOM"), "Not as easy as it seems :)", 20, 20, 20);
    }

}
