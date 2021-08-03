package pl.pijok.apiextension.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import pl.pijok.apiextension.APIExtension;

public class Debug {

    private static final ConsoleCommandSender console = Bukkit.getConsoleSender();
    private static String prefix = "[]";

    public static void setPrefix(String a){
        prefix = a;
    }

    /**
     * Sends string to console
     * @param a Message to send
     */
    public static void log(String a) {
        a = prefix + a;
        console.sendMessage(a.replace("&", "§"));
    }

    /**
     * Sends red error to console
     * @param a Error to send
     */
    public static void sendError(String a){
        Debug.log("&c============");
        Debug.log("&c" + a);
        Debug.log("&c============");
    }

    /**
     * Sends object to console
     * @param a Object to send
     */
    public static void log(Object a) {
        a = prefix + a;
        console.sendMessage(String.valueOf(a).replace("&", "§"));
    }

    public static void syncedLog(String a){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(APIExtension.getInstance(), new Runnable() {
            @Override
            public void run() {
                log(a);
            }
        });
    }

    public static void syncedLog(Object a){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(APIExtension.getInstance(), new Runnable() {
            @Override
            public void run() {
                log(a);
            }
        });
    }

}
