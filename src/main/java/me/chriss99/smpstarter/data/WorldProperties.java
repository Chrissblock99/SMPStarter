package me.chriss99.smpstarter.data;

import me.chriss99.smpstarter.SMPStarter;
import me.chriss99.smpstarter.util.ParticleUtil;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class WorldProperties {
    private static final SMPStarter plugin = SMPStarter.getInstance();

    private final World world;
    private final List<BoundingBox> spawnBoundingBoxes = new LinkedList<>();
    private boolean active = true;

    public WorldProperties(@NotNull World world, @NotNull List<BoundingBox> spawnBoundingBoxes) {
        this.world = world;
        this.spawnBoundingBoxes.addAll(spawnBoundingBoxes);
    }

    public boolean playerIsInSpawn(@NotNull Player player) {
        Vector playerPosition = player.getLocation().toVector();
        for (BoundingBox boundingBox : spawnBoundingBoxes)
            if (boundingBox.contains(playerPosition))
                return true;

        return false;
    }

    public void setActive(boolean active) {
        this.active = active;

        if (!active) { //deactivate
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);

            world.setTime(1000);

            world.setGameRule(GameRule.FALL_DAMAGE, false);
            world.setGameRule(GameRule.FIRE_DAMAGE, false);
            world.setGameRule(GameRule.DROWNING_DAMAGE, false);
            world.setGameRule(GameRule.FREEZE_DAMAGE, false);

            for (Player player : world.getPlayers())
                if (player.getGameMode().equals(GameMode.SURVIVAL))
                    player.setGameMode(GameMode.ADVENTURE);
        } else { //activate
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, true);

            world.setGameRule(GameRule.FALL_DAMAGE, true);
            world.setGameRule(GameRule.FIRE_DAMAGE, true);
            world.setGameRule(GameRule.DROWNING_DAMAGE, true);
            world.setGameRule(GameRule.FREEZE_DAMAGE, true);

            for (Player player : world.getPlayers())
                if (player.getGameMode().equals(GameMode.ADVENTURE))
                    player.setGameMode(GameMode.SURVIVAL);
        }
    }

    public void visualizeSpawnBoundingBoxes() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (BoundingBox boundingBox : spawnBoundingBoxes)
                    ParticleUtil.visualizeBoundingBoxInWorld(world, boundingBox, 5, Particle.FLAME, null);

                if (active)
                    cancel();
            }
        }.runTaskTimer(plugin, 0, 7);
    }

    public void addSpawnBoundingBox(@NotNull BoundingBox boundingBox) {
        spawnBoundingBoxes.add(boundingBox);
    }

    public void clearSpawnBoundingBoxes() {
        spawnBoundingBoxes.clear();
    }

    public @NotNull World getWorld() {
        return world;
    }

    public boolean isInactive() {
        return !active;
    }
}
