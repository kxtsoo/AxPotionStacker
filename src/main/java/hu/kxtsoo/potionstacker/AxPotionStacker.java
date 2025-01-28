package hu.kxtsoo.potionstacker;

import hu.kxtsoo.potionstacker.manager.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AxPotionStacker extends JavaPlugin {

    private static AxPotionStacker instance;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;

        commandManager = new CommandManager(this);
        commandManager.init();
    }

    @Override
    public void onDisable() {
        commandManager.shutdown();
    }

    public static AxPotionStacker getInstance() {
        return instance;
    }
}
