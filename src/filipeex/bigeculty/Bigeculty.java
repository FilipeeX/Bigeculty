package filipeex.bigeculty;

import filipeex.bigeculty.classes.Chat;
import filipeex.bigeculty.cmds.MainCmd;
import filipeex.bigeculty.events.BlockListener;
import filipeex.bigeculty.events.DeathListener;
import filipeex.bigeculty.events.MoveListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.stream.events.Characters;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class Bigeculty extends JavaPlugin {

    public static Bigeculty i;

    public static FileConfiguration config;
    public static FileConfiguration messageConfig;

    public static String prefix;

    private static File messageConfigFile;

    // This Will Be Called Every Time This Plugin is Started
    @Override
    public void onEnable() {
        i = this;

        getConfig().options().copyDefaults(true);
        saveConfig();
        config = getConfig();

        createMessagesConfig();
        saveMessageConfig();

        getCommand("bigeculty").setExecutor(new MainCmd());
        getCommand("bigeculty").setExecutor(new MainCmd());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MoveListener(), this);
        pm.registerEvents(new BlockListener(), this);
        pm.registerEvents(new DeathListener(), this);

        prefix = Chat.c(messageConfig.getString("prefix"));

        if (config.getBoolean("enabled")) {
            enable();
        } else {
            disable();
            Bukkit.getConsoleSender().sendMessage(messageConfig.getString("disabled-config".replace("%prefix%", prefix)));
        }
    }

    public void enable() {
        config.set("enabled", true);
        saveConfig();

        Bukkit.getConsoleSender().sendMessage(Chat.c(messageConfig.getString("plugin-enabled").replace("%prefix%", prefix)));
    }

    public void disable() {
        config.set("enabled", false);
        saveConfig();

        Bukkit.getConsoleSender().sendMessage(Chat.c(messageConfig.getString("plugin-disabled").replace("%prefix%", prefix)));
    }

    public static boolean isRunning() {
        return config.getBoolean("enabled");
    }

    public void reload() {
        reloadConfig(); config = getConfig();
        messageConfig = YamlConfiguration.loadConfiguration(messageConfigFile);

        String message = Chat.c(messageConfig.getString("plugin-reloaded"))
                .replace("%prefix%", prefix);
        Bukkit.getConsoleSender().sendMessage(message);
    }

    private void createMessagesConfig() {
        messageConfigFile = new File(getDataFolder(), "messages.yml");
        if (!messageConfigFile.exists()) {
            messageConfigFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messageConfig = new YamlConfiguration();
        try {
            messageConfig.load(messageConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveMessageConfig() {
        try {
            messageConfig.save(messageConfigFile);
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage(Chat.c(messageConfig.getString("unable-to-save").replace("%prefix%", prefix).replace("%file%", messageConfigFile.getName())));
        }
    }
}
