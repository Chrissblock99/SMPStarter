package me.chriss99.smpstarter.events;

import me.chriss99.smpstarter.SMPStarter;
import me.chriss99.smpstarter.data.WorldProperties;
import me.chriss99.smpstarter.data.WorldPropertiesManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {
    public FoodLevelChange() {
        SMPStarter.registerEvent(this);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;
        if (!player.getGameMode().equals(GameMode.ADVENTURE))
            return;

        WorldProperties worldProperties = WorldPropertiesManager.getWorldProperties(player.getWorld());
        if (worldProperties != null && worldProperties.isInactive())
            event.setCancelled(true);
    }
}