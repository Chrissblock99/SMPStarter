package me.chriss99.smpstarter.data;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class WorldPropertiesManager {
    private static final HashMap<World, WorldProperties> worldToWorldPropertiesMap = new HashMap<>();

    public static @NotNull WorldProperties createWorldProperties(@NotNull World world) {
        WorldProperties worldProperties = new WorldProperties(world, List.of());
        worldToWorldPropertiesMap.put(world, worldProperties);
        return worldProperties;
    }

    public static void setWorldActive(@NotNull World world, boolean active) {
        WorldProperties worldProperties = WorldPropertiesManager.getWorldProperties(world);

        if (worldProperties == null)
            worldProperties = WorldPropertiesManager.createWorldProperties(world);

        worldProperties.setActive(active);
    }

    public static void addWorldSpawnBoundingBox(@NotNull World world, @NotNull BoundingBox boundingBox) {
        if (!worldHasProperties(world)) {
            worldToWorldPropertiesMap.put(world, new WorldProperties(world, List.of(boundingBox)));
            return;
        }

        worldToWorldPropertiesMap.get(world).addSpawnBoundingBox(boundingBox);
    }

    public static void clearSpawnBoundingBoxes(@NotNull World world) {
        if (!worldHasProperties(world))
            return;

        worldToWorldPropertiesMap.get(world).clearSpawnBoundingBoxes();
    }

    public static @Nullable WorldProperties getWorldProperties(@NotNull World world) {
        return worldToWorldPropertiesMap.get(world);
    }

    public static boolean worldHasProperties(@NotNull World world) {
        return worldToWorldPropertiesMap.containsKey(world);
    }

    public static boolean playerIsInSpawn(@NotNull Player player) {
        WorldProperties worldProperties = worldToWorldPropertiesMap.get(player.getWorld());

        if (worldProperties == null)
            throw new IllegalArgumentException("World \"" + player.getWorld().getName() + "\" has no properties!");

        return worldProperties.playerIsInSpawn(player);
    }

    public static boolean worldIsInactive(@NotNull World world) {
        WorldProperties worldProperties = worldToWorldPropertiesMap.get(world);

        if (worldProperties == null)
            throw new IllegalArgumentException("World \"" + world.getName() + "\" has no properties!");

        return worldProperties.isInactive();
    }
}
