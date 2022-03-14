package filipeex.bigeculty.cmds;

import filipeex.bigeculty.changes.MoveExplosion;
import filipeex.bigeculty.changes.VexSpawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCmd implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("explosion")) {
                    MoveExplosion.explode(p);
                }
                if (args[0].equalsIgnoreCase("vex")) {
                    VexSpawn.spawnVex(p, p.getLocation());
                }
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            List<String> suggestions = new ArrayList<String>();
            suggestions.add("enable");
            suggestions.add("disable");
            suggestions.add("help");
            suggestions.add("explosion");
            suggestions.add("vex");

            String arg = args[0];

            if (!arg.equalsIgnoreCase("")) {
                List<String> completions = new ArrayList<String>();
                StringUtil.copyPartialMatches(args[0], suggestions, completions);
                Collections.sort(completions);
                if (completions.size() == 1) {
                    if (completions.get(0).equalsIgnoreCase(arg)) {
                        completions.remove(0);
                    }
                }
                return completions;
            } else {
                return suggestions;
            }
        }

        if (args.length >= 2) {
            return new ArrayList<>();
        }

        return null;
    }
}
