package pl.pijok.apiextension;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "MinigamesAPIExtension";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Pijok";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        return null;
    }
}
