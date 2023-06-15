package me.chriss99.smpstarter;

import com.google.gson.Gson;
import me.chriss99.smpstarter.commands.SMPStarterCommand;
import me.chriss99.smpstarter.events.FoodLevelChange;
import me.chriss99.smpstarter.events.EntityDamageByEntity;
import me.chriss99.smpstarter.events.PlayerJoin;
import me.chriss99.smpstarter.events.PlayerMove;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class SMPStarter extends JavaPlugin {
    private static SMPStarter instance;
    private static final Gson gson = new Gson();
    private static final MiniMessage miniMsg = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        instance = this;

        registerAllEvents();
        registerAllCommands();

        Bukkit.getLogger().info("SMPStarter enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SMPStarter disabled!");
    }

    private static void registerAllEvents() {
        new PlayerMove();
        new EntityDamageByEntity();
        new FoodLevelChange();
        new PlayerJoin();
    }

    private static void registerAllCommands() {
        new SMPStarterCommand();
    }

    public static void registerEvent(@NotNull Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, instance);
    }

    public static SMPStarter getInstance() {
        return instance;
    }

    public static Gson getGson() {
        return gson;
    }

    public static MiniMessage getMiniMessage(){
        return miniMsg;
    }
}
