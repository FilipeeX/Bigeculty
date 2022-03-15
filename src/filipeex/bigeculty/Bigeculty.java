package filipeex.bigeculty;

import filipeex.bigeculty.cmds.MainCmd;
import filipeex.bigeculty.events.BlockListener;
import filipeex.bigeculty.events.DeathListener;
import filipeex.bigeculty.events.MoveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Bigeculty extends JavaPlugin {

    // This Will Be Called Every Time This Plugin is Started
    @Override
    public void onEnable() {
        getCommand("bigeculty").setExecutor(new MainCmd());
        getCommand("bigeculty").setExecutor(new MainCmd());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MoveListener(), this);
        pm.registerEvents(new BlockListener(), this);
        pm.registerEvents(new DeathListener(), this);
    }
}
