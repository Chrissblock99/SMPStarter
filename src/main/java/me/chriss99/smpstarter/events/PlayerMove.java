package me.chriss99.smpstarter.events;

import me.chriss99.smpstarter.SMPStarter;
import me.chriss99.smpstarter.data.WorldProperties;
import me.chriss99.smpstarter.data.WorldPropertiesManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    private static final MiniMessage miniMessage = SMPStarter.getMiniMessage();

    public PlayerMove() {
        SMPStarter.registerEvent(this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.ADVENTURE))
            return;

        WorldProperties worldProperties = WorldPropertiesManager.getWorldProperties(player.getWorld());

        if (worldProperties == null)
            return;

        if (worldProperties.isInactive() && !worldProperties.playerIsInSpawn(player)) {
            player.sendMessage(miniMessage.deserialize("<red>You can't leave spawn yet!"));
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }
}
