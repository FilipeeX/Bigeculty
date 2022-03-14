package filipeex.bigeculty.events;

import filipeex.bigeculty.changes.WaterLaunch;
import filipeex.bigeculty.classes.Chat;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class MoveListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void move(PlayerMoveEvent e) {
        Random r = new Random();
        if (r.nextInt(999) == r.nextInt(999)) {
            for (int i = 0; i < 100; i++) {
                e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.PRIMED_TNT);
            }
            e.getPlayer().sendTitle(Chat.c("&c&lBOOM"), "Not as easy as it seems :)", 20, 20, 20);
        }

        if (e.getPlayer().getWorld().getBlockAt(e.getPlayer().getLocation()).getType() == Material.WATER) {
            WaterLaunch.launch(e.getPlayer());
        }
    }

}
