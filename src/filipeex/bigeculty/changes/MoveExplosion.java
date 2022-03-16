package filipeex.bigeculty.changes;

import filipeex.bigeculty.Bigeculty;
import filipeex.bigeculty.classes.Chat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MoveExplosion {

    public static void explode(Player p) {
        for (int i = 0; i < 1; i++) {
            p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
        }

        String title = Chat.c(Bigeculty.messageConfig.getString("explosion-title")
                .replace("%player%", p.getName()
                .replace("%prefix%", Bigeculty.prefix)));

        String subtitle = Chat.c(Bigeculty.messageConfig.getString("explosion-subtitle")
                .replace("%player%", p.getName()
                .replace("%prefix%", Bigeculty.prefix)));

        p.sendTitle(title, subtitle, 5, 10, 5);
    }

}
