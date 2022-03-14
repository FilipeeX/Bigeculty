package filipeex.bigeculty.cmds;

import filipeex.bigeculty.changes.MoveExplosion;
import filipeex.bigeculty.changes.VexSpawn;
import filipeex.bigeculty.changes.WaterLaunch;
import filipeex.bigeculty.classes.ArgumentTabCompleter;
import filipeex.bigeculty.classes.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainCmd implements TabExecutor {

    private String[] argumentArray = {"enable", "disable", "help", "changes", "manuelevent"};
    private List<String> arguments = new ArrayList<String>(Arrays.asList(argumentArray));

    private String[] eventArray = {"explosion", "vex", "launch"};
    private List<String> events = new ArrayList<String>(Arrays.asList(eventArray));

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("enable")) {
                    // enable plugin
                }
                else if (args[0].equalsIgnoreCase("disable")) {
                    // disable plugin
                }
                else if (args[0].equalsIgnoreCase("help")) {
                    // display chat help message
                }
                else if (args[0].equalsIgnoreCase("changes")) {
                    // display list of changes in chat
                }
                else {
                    p.sendMessage(Chat.c("&cInvalid Command Argument! &rTry &b/bigeculty help&r!"));
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("manualevent")) {

                    if (args[1].equalsIgnoreCase("explosion")) {
                        MoveExplosion.explode(p);
                    } else if (args[1].equalsIgnoreCase("vex")) {
                        VexSpawn.spawnVex(p, p.getLocation());
                    } else if (args[1].equalsIgnoreCase("launch")) {
                        WaterLaunch.launch(p);
                    } else {
                        p.sendMessage(Chat.c("&cInvalid Event! &rTry &b/bigeculty manualevent list&r!"));
                    }

                } else {
                    p.sendMessage(Chat.c("&cInvalid Command Arguments! &rTry &b/bigeculty help&r!"));
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("manualevent")) {

                    Player target = Bukkit.getServer().getPlayer(args[2]);
                    if (target != null) {
                        if (args[1].equalsIgnoreCase("explosion")) {
                            MoveExplosion.explode(target);
                            target.sendMessage(Chat.c("&fPlayer &c%p%&f spawned a tnt next to u using manual event command!".replace("%p%", p.getName())));
                            p.sendMessage(Chat.c("&fPlayer &a%p%&f got a tnt on him, cuz why not!".replace("%p%", target.getName())));
                        } else if (args[1].equalsIgnoreCase("vex")) {
                            VexSpawn.spawnVex(target, target.getLocation());
                            target.sendMessage(Chat.c("&fPlayer &c%p%&f spawned a vex on u using manual event command!".replace("%p%", p.getName())));
                            p.sendMessage(Chat.c("&fPlayer &a%p%&f got a vex, cuz why not!".replace("%p%", target.getName())));
                        } else if (args[1].equalsIgnoreCase("launch")) {
                            WaterLaunch.launch(target);
                            target.sendMessage(Chat.c("&fPlayer &c%p%&f launched u into the sky using manual event command!".replace("%p%", p.getName())));
                            p.sendMessage(Chat.c("&fPlayer &a%p%&f was launched into the sky, cuz he cant swim!".replace("%p%", target.getName())));
                        } else {
                            p.sendMessage(Chat.c("&cInvalid Event! &rTry &b/bigeculty manualevent list&r!"));
                        }
                    } else {
                        p.sendMessage(Chat.c("&fPlayer &c%p%&f Not Fount!".replace("%p%", target.getName())));
                    }

                } else {
                    p.sendMessage(Chat.c("&cInvalid Command Arguments! &rTry &b/bigeculty help&r!"));
                }
            } else if (args.length == 0) {
                // display chat help message
            } else {
                p.sendMessage(Chat.c("&cInvalid Command Arguments! &rTry &b/bigeculty help&r!"));
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            List<String> suggestions = new ArrayList<String>();
            for (String s : arguments) {
                suggestions.add(s);
            }
            return ArgumentTabCompleter.getCompletions(suggestions, args[0]);
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("manualevent")) {
                List<String> suggestions = new ArrayList<String>();
                for (String s : events) {
                    suggestions.add(s);
                }
                return ArgumentTabCompleter.getCompletions(suggestions, args[1]);
            } else if (args[0].equalsIgnoreCase("help")) {
                List<String> suggestions = new ArrayList<String>();
                for (String s : arguments) {
                    suggestions.add(s);
                }
                for (String s : events) {
                    suggestions.add(s);
                }
                return ArgumentTabCompleter.getCompletions(suggestions, args[1]);
            } else {
                List<String> suggestions = new ArrayList<String>();
                return ArgumentTabCompleter.getCompletions(suggestions, args[1]);
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("manualevent")) {
                List<String> suggestions = new ArrayList<String>();
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    suggestions.add(onlinePlayer.getName());
                }
                return ArgumentTabCompleter.getCompletions(suggestions, args[1]);
            }
        }

        if (args.length >= 4) {
            return new ArrayList<>();
        }

        return null;
    }
}
