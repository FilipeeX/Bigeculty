package filipeex.bigeculty.cmds;

import filipeex.bigeculty.Bigeculty;
import filipeex.bigeculty.changes.MoveExplosion;
import filipeex.bigeculty.changes.VexSpawn;
import filipeex.bigeculty.changes.WaterLaunch;
import filipeex.bigeculty.classes.ArgumentTabCompleter;
import filipeex.bigeculty.classes.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCmd implements TabExecutor {

    private String[] argumentArray = {"enable", "disable", "help", "changes", "manualevent", "reload"};
    private List<String> arguments = new ArrayList<String>(Arrays.asList(argumentArray));

    private String[] eventArray = {"explosion", "vex", "launch"};
    private List<String> events = new ArrayList<String>(Arrays.asList(eventArray));

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("enable")) {
                    if (!Bigeculty.isRunning()) {
                        Bigeculty.i.enable();
                        p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("u-enabled-plugin").replace("%prefix%", Bigeculty.prefix)));
                    } else {
                        String message = Chat.c(Bigeculty.messageConfig.getString("already-enabled")
                                .replace("%prefix%", Bigeculty.prefix)
                                .replace("%player%", p.getName()));
                        p.sendMessage(message);
                    }
                }
                else if (args[0].equalsIgnoreCase("disable")) {
                    if (Bigeculty.isRunning()) {
                        Bigeculty.i.disable();
                        p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("u-disabled-plugin").replace("%prefix%", Bigeculty.prefix)));
                    } else {
                        String message = Chat.c(Bigeculty.messageConfig.getString("already-disabled")
                                .replace("%prefix%", Bigeculty.prefix)
                                .replace("%player%", p.getName()));
                        p.sendMessage(message);
                    }
                }
                else if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(Chat.c("&8(§)&m---------------------------------------------&r&8(§)"));
                    p.sendMessage(Chat.c("&r"));
                    p.sendMessage(Chat.c("&r        &c&lBigeculty"));
                    p.sendMessage(Chat.c("&r"));
                    p.sendMessage(Chat.c("&r        &f/b enable&7 - Enables Difficulty"));
                    p.sendMessage(Chat.c("&r        &f/b disable&7 - Disables Difficulty"));
                    p.sendMessage(Chat.c("&r        &f/b help&7 - Displays This Help"));
                    p.sendMessage(Chat.c("&r        &f/b manualevent <event> [player]"));
                    p.sendMessage(Chat.c("&r        &g/b changes&7 - Displays List of Changes"));
                    p.sendMessage(Chat.c("&r"));
                    p.sendMessage(Chat.c("&8(§)&m---------------------------------------------&r&8(§)"));
                }
                else if (args[0].equalsIgnoreCase("changes")) {
                    p.sendMessage(Chat.c("&8(§)&m---------------------------------------------&r&8(§)"));
                    p.sendMessage(Chat.c("&r"));
                    p.sendMessage(Chat.c("&r        &fExplosion&7 - You Can Explode While Moving"));
                    p.sendMessage(Chat.c("&r        &fVex&7 - A Vex Can Spawn from a Broken Block"));
                    p.sendMessage(Chat.c("&r        &fLaunch&7 - You Cant Swim & Dont Like Water"));
                    p.sendMessage(Chat.c("&r"));
                    p.sendMessage(Chat.c("&8(§)&m---------------------------------------------&r&8(§)"));
                } else if (args[0].equalsIgnoreCase("reload")) {
                    Bigeculty.i.reload();
                    String message = Chat.c(Bigeculty.messageConfig.getString("plugin-reloaded")
                    .replace("%player%", p.getName())
                    .replace("%prefix%", Bigeculty.prefix));
                    p.sendMessage(message);
                } else {
                    p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("invalid-argument")).replace("%prefix%", Bigeculty.prefix));
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("manualevent")) {

                    if (Bigeculty.isRunning()) {

                        if (args[1].equalsIgnoreCase("explosion")) {
                            MoveExplosion.explode(p);
                        } else if (args[1].equalsIgnoreCase("vex")) {
                            VexSpawn.spawnVex(p, p.getLocation());
                        } else if (args[1].equalsIgnoreCase("launch")) {
                            WaterLaunch.launch(p);

                        } else {
                            p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("invalid-event").replace("%prefix%", Bigeculty.prefix)));
                        }

                    } else {
                        String message = Chat.c(Bigeculty.messageConfig.getString("function-unavailable")
                                .replace("%prefix%", Bigeculty.prefix)
                                .replace("%player%", Bigeculty.prefix));
                        p.sendMessage(message);
                    }

                } else {
                    p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("invalid-arguments").replace("%prefix%", Bigeculty.prefix)));
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("manualevent")) {

                    if (Bigeculty.isRunning()) {

                        Player target = Bukkit.getServer().getPlayer(args[2]);
                        if (target != null) {
                            if (args[1].equalsIgnoreCase("explosion")) {
                                MoveExplosion.explode(target);
                                target.sendMessage(Chat.c(Bigeculty.messageConfig.getString("explosion-target-message").replace("%prefix%", Bigeculty.prefix).replace("%player%", p.getName())));
                                p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("explosion-player-message").replace("%prefix%", Bigeculty.prefix).replace("%player%", target.getName())));
                            } else if (args[1].equalsIgnoreCase("vex")) {
                                VexSpawn.spawnVex(target, target.getLocation());
                                target.sendMessage(Chat.c(Bigeculty.messageConfig.getString("vex-target-message").replace("%prefix%", Bigeculty.prefix).replace("%player%", p.getName())));
                                p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("vex-player-message").replace("%prefix%", Bigeculty.prefix).replace("%player%", target.getName())));
                            } else if (args[1].equalsIgnoreCase("launch")) {
                                WaterLaunch.launch(target);
                                target.sendMessage(Chat.c(Bigeculty.messageConfig.getString("launch-target-message").replace("%prefix%", Bigeculty.prefix).replace("%player%", p.getName())));
                                p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("launch-player-message").replace("%prefix%", Bigeculty.prefix).replace("%player%", target.getName())));
                            } else {
                                p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("invalid-event").replace("%prefix%", Bigeculty.prefix)));
                            }
                        } else {
                            p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("player-not-found").replace("%prefix%", Bigeculty.prefix).replace("%player%", args[2])));
                        }

                    } else {
                        String message = Chat.c(Bigeculty.messageConfig.getString("function-unavailable")
                                .replace("%prefix%", Bigeculty.prefix)
                                .replace("%player%", Bigeculty.prefix));
                        p.sendMessage(message);
                    }

                } else {
                    p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("invalid-arguments")).replace("%prefix%", Bigeculty.prefix));
                }
            } else if (args.length == 0) {
                p.sendMessage(Chat.c("&8(§)&m---------------------------------------------&r&8(§)"));
                p.sendMessage(Chat.c("&r"));
                p.sendMessage(Chat.c("&r        &c&lBigeculty"));
                p.sendMessage(Chat.c("&r"));
                p.sendMessage(Chat.c("&r        &f/b enable&7 - Enables Difficulty"));
                p.sendMessage(Chat.c("&r        &f/b disable&7 - Disables Difficulty"));
                p.sendMessage(Chat.c("&r        &f/b help&7 - Displays This Help"));
                p.sendMessage(Chat.c("&r        &f/b manualevent <event> [player]"));
                p.sendMessage(Chat.c("&r        &g/b changes&7 - Displays List of Changes"));
                p.sendMessage(Chat.c("&r"));
                p.sendMessage(Chat.c("&8(§)&m---------------------------------------------&r&8(§)"));
            } else {
                p.sendMessage(Chat.c(Bigeculty.messageConfig.getString("invalid-arguments")).replace("%prefix%", Bigeculty.prefix));
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            List<String> suggestions = new ArrayList<String>(arguments);
            if (!Bigeculty.isRunning()) {
                suggestions.removeAll(suggestions);
                suggestions.add("enable");
            }
            return ArgumentTabCompleter.getCompletions(suggestions, args[0]);
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("manualevent")) {
                List<String> suggestions = new ArrayList<String>(events);
                if (!Bigeculty.isRunning()) {
                    suggestions.removeAll(suggestions);
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
                if (!Bigeculty.isRunning()) {
                    suggestions.removeAll(suggestions);
                }
                return ArgumentTabCompleter.getCompletions(suggestions, args[2]);
            }
        }

        if (args.length >= 4) {
            return new ArrayList<>();
        }

        return null;
    }
}
