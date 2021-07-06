package pl.pijok.apiextension;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.pijok.apiextension.essentials.ChatUtils;
import pl.pijok.apiextension.essentials.Debug;
import pl.pijok.apiextension.listeners.JoinListener;
import pl.pijok.apiextension.listeners.QuitListener;
import pl.trollcraft.minigamesapi.model.points.PointsManager;

public class APIExtension extends JavaPlugin {

    private static Economy econ = null;
    private static PointsManager pointsManager;

    @Override
    public void onEnable() {

        ChatUtils.setPrefix("&7[APIExtension]");
        Debug.setPrefix("[APIExtension]");

        if (!setupEconomy() ) {
            Debug.log(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if(!setupMiniGamesEconomy()){
            Debug.log(String.format("[%s] - Disabled due to no MiniGamesAPI dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

    }

    @Override
    public void onDisable() {

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupMiniGamesEconomy(){
        if(getServer().getPluginManager().getPlugin("MiniGamesAPI") == null){
            return false;
        }

        try{
            pointsManager = Bukkit.getServicesManager().getRegistration(PointsManager.class).getProvider();
        }
        catch (NullPointerException exception){
            return false;
        }

        return pointsManager != null;
    }

    public static Economy getEcon(){
        return econ;
    }

    public static PointsManager getPointsManager() {
        return pointsManager;
    }
}