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
        /*double currentGamesBal = APIExtension.getPointsManager().profile(player).getPointsBalance();*/

        APIExtension.getAsyncPointsManager().profile(player).thenAccept(pointsProfile -> {
            double currentGamesBal = pointsProfile.getPointsBalance();
            if(currentGamesBal > currentVaultBal){
                if(joined){
                    APIExtension.getEcon().depositPlayer(player, currentGamesBal - currentVaultBal);
                }
                else{
                    //APIExtension.getPointsManager().withdraw(player, currentGamesBal - currentVaultBal);
                    APIExtension.getAsyncPointsManager().withdraw(player, currentGamesBal - currentVaultBal).thenAccept(transaction -> {
                        if(transaction.isSuccessful()){
                            Debug.log("&aSync successful!");
                        }
                        else{
                            Debug.log("&cSync failed!");
                        }

                    });
                }
            }
            else if(currentGamesBal < currentVaultBal){
                if(joined){
                    APIExtension.getEcon().withdrawPlayer(player, currentVaultBal - currentGamesBal);
                }
                else{
                    //APIExtension.getPointsManager().deposit(player, currentVaultBal - currentGamesBal);
                    APIExtension.getAsyncPointsManager().deposit(player, currentVaultBal - currentGamesBal).thenAccept(transaction -> {
                        if(transaction.isSuccessful()){
                            Debug.log("&aSync successful!");
                        }
                        else{
                            Debug.log("&cSync failed!");
                        }


                    });
                }
            }

            Debug.log("Vault: " + APIExtension.getEcon().getBalance(player));
            Debug.log("MiniGames: " + pointsProfile.getPointsBalance());
        });

    }
}
