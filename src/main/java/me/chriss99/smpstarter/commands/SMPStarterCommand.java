package me.chriss99.smpstarter.commands;

import me.chriss99.smpstarter.SMPStarter;
import me.chriss99.smpstarter.data.WorldProperties;
import me.chriss99.smpstarter.data.WorldPropertiesManager;
import me.chriss99.smpstarter.util.reflectivecommand.ReflectCommand;
import me.chriss99.smpstarter.util.reflectivecommand.ReflectiveCommandBase;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.List;

public class SMPStarterCommand extends ReflectiveCommandBase {
    private static final MiniMessage miniMessage = SMPStarter.getMiniMessage();

    public SMPStarterCommand() {
        super("smpStarter", "used to configure SMPStarter", List.of("smps"));
    }

    @ReflectCommand(path = "addSpawnBoundingBox")
    public void addSpawnBoundingBox(Player commandSender, float x1, float y1, float z1, float x2, float y2, float z2) {
        WorldPropertiesManager.addWorldSpawnBoundingBox(commandSender.getWorld(), new BoundingBox(x1, y1, z1, x2, y2, z2));
    }

    @ReflectCommand(path = "clearSpawnBoundingBoxes")
    public void clearSpawnBoundingBoxes(Player commandSender) {
        WorldPropertiesManager.clearSpawnBoundingBoxes(commandSender.getWorld());
    }

    @ReflectCommand(path = "setup")
    public void setup(Player commandSender) {
        WorldPropertiesManager.setWorldActive(commandSender.getWorld(), false);
    }

    @ReflectCommand(path = "visualSpawn")
    public void visualSpawn(Player commandSender) {
        WorldProperties worldProperties = WorldPropertiesManager.getWorldProperties(commandSender.getWorld());
        if (worldProperties == null) {
            commandSender.sendMessage(miniMessage.deserialize("<red>This world has no properties!"));
            return;
        }

        worldProperties.visualizeSpawnBoundingBoxes();
    }

    @ReflectCommand(path = "start")
    public void start(Player commandSender) {
        WorldPropertiesManager.setWorldActive(commandSender.getWorld(), true);
    }
}
