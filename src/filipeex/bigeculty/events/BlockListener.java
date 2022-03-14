package filipeex.bigeculty.events;

import filipeex.bigeculty.changes.VexSpawn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BlockListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockBreak(BlockBreakEvent e) {
        Random r = new Random();
        if (r.nextInt(49) == r.nextInt(49)) {
            VexSpawn.spawnVex(e.getPlayer(), e.getBlock().getLocation());
        }
    }

}
