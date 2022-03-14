package filipeex.bigeculty.classes;

import org.bukkit.ChatColor;

public class Chat {

    // Returns Colored Message
    public static String c(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
