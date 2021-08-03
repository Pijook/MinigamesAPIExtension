package pl.pijok.apiextension.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.pijok.apiextension.APIExtension;
import pl.pijok.apiextension.essentials.ChatUtils;
import pl.trollcraft.minigamesapi.model.points.AsyncPointsManager;

public class DebugCommand implements CommandExecutor {

    private final AsyncPointsManager pointsManager = APIExtension.getAsyncPointsManager();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("apiextension.admin")){
                ChatUtils.sendMessage(sender, "&cNie masz dostepu do tej komendy!");
                return true;
            }
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("checkMoney")) {
                String nickname = args[1];
                Player target = Bukkit.getPlayer(nickname);
                ChatUtils.sendMessage(sender, "&7Checking balance...");
                pointsManager.profile(target).thenAccept( profile -> {
                    ChatUtils.sendMessage(sender, "&7" + nickname + ": &e" + profile.getPointsBalance());
                });
                return true;
            }
        }

        ChatUtils.sendMessage(sender, "&7/" + label + " checkMoney <nickname>");
        return true;
    }
}
