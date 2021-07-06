package pl.pijok.apiextension.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.pijok.apiextension.BalanceController;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();
        BalanceController.syncAccount(player, false);

    }
}
