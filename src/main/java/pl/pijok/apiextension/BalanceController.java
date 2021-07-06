package pl.pijok.apiextension;

import org.bukkit.entity.Player;
import pl.pijok.apiextension.essentials.Debug;

public class BalanceController {

    public static void syncAccount(Player player, boolean joined){
        if(joined){
            Debug.log(player.getName() + " joined the server! Syncing accounts...");
        }
        else{
            Debug.log(player.getName() + " left the server! Syncing accounts...");
        }

        double currentVaultBal = APIExtension.getEcon().getBalance(player);
        double currentGamesBal = APIExtension.getPointsManager().profile(player).getPointsBalance();

        if(currentGamesBal > currentVaultBal){
            if(joined){
                APIExtension.getEcon().depositPlayer(player, currentGamesBal - currentVaultBal);
            }
            else{
                APIExtension.getPointsManager().withdraw(player, currentGamesBal - currentVaultBal);
            }
        }
        else if(currentGamesBal < currentVaultBal){
            if(joined){
                APIExtension.getEcon().withdrawPlayer(player, currentVaultBal - currentGamesBal);
            }
            else{
                APIExtension.getPointsManager().deposit(player, currentVaultBal - currentGamesBal);
            }
        }

        Debug.log("Synced! Current " + player.getName() + " balance:");
        Debug.log("Vault: " + APIExtension.getEcon().getBalance(player));
        Debug.log("MiniGames: " + APIExtension.getPointsManager().profile(player).getPointsBalance());
    }
}
