package hu.kxtsoo.potionstacker.manager;

import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import hu.kxtsoo.potionstacker.AxPotionStacker;
import hu.kxtsoo.potionstacker.command.PotionStackCommand;
import org.bukkit.command.CommandSender;

public class CommandManager {
    private final BukkitCommandManager<CommandSender> commandManager;
    private AxPotionStacker plugin;

    public CommandManager(AxPotionStacker plugin) {
        this.commandManager = BukkitCommandManager.create(plugin);
        this.plugin = plugin;
    }

    public void init() {
        registerCommands();
    }

    public void registerCommands() {
        commandManager.registerCommand(new PotionStackCommand());
    }

    public void shutdown() {
        commandManager.unregisterCommands();
    }
}
