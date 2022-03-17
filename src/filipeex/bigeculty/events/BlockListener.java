package filipeex.bigeculty.events;

import filipeex.bigeculty.Bigeculty;
import filipeex.bigeculty.changes.VexSpawn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Random;

public class BlockListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockBreak(BlockBreakEvent e) {
        if (Bigeculty.isRunning()) {
            Random r = new Random();
            if (r.nextInt(49) == r.nextInt(49)) {
                VexSpawn.spawnVex(e.getPlayer(), e.getBlock().getLocation());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockPlace(BlockPlaceEvent e) {
        if (Bigeculty.isRunning()) {
            Random r = new Random();
            if (r.nextInt(49) == r.nextInt(49)) {
                VexSpawn.spawnVex(e.getPlayer(), e.getBlock().getLocation());
            }
        }
    }

}
