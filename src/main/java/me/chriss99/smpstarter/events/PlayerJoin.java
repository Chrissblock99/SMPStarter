package me.chriss99.smpstarter.events;

import me.chriss99.smpstarter.SMPStarter;
import me.chriss99.smpstarter.data.WorldPropertiesManager;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    public PlayerJoin() {
        SMPStarter.registerEvent(this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        if (WorldPropertiesManager.worldHasProperties(world) && WorldPropertiesManager.worldIsInactive(world))
            player.setGameMode(GameMode.ADVENTURE);
    }
}
