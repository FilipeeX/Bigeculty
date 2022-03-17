package filipeex.bigeculty.events;

import filipeex.bigeculty.Bigeculty;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void death(PlayerRespawnEvent e) {
        if (Bigeculty.isRunning()) {
            e.getPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
        }
    }

}
