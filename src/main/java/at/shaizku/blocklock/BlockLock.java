package at.shaizku.blockbanner;

import at.shaizku.blockbanner.commands.BanBlockCommand;
import at.shaizku.blockbanner.commands.EnableBlockCommand;
import at.shaizku.blockbanner.events.BlockBreakListener;
import at.shaizku.blockbanner.events.BlockPlaceListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public final class BlockLock extends JavaPlugin {

    private void registerCommandForClass(final String CommandName, CommandExecutor commandExecutor) {
        @Nullable PluginCommand command;
        command = getServer().getPluginCommand(CommandName);
        if (command != null) {
            command.setExecutor(commandExecutor);
            this.getLogger().info("Successfully registered command '" +
                    CommandName + "'");
        } else {
            this.getLogger().info("registerCommandForClass: Command '" + CommandName + "' not found, " +
                    "probably missing in plugin.yml?");
        }
    }

    public void pluginmsg(final String Message) {
        this.getLogger().info(Message);
    }

    @Override
    public void onEnable() {
        pluginmsg("###################################");
        pluginmsg("Starting BlockLock");
        pluginmsg("By shaizku");

        File PluginFolder = new File("plugins/BlockLock");
        if (!PluginFolder.exists()) {
            PluginFolder.mkdir();
        }

        File directory = new File("plugins/BlockLock");
        File file = new File(directory, "bannedblocks.yml");

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }

        } else {
            System.out.println("File already exists: " + file.getAbsolutePath());
        }

        pluginmsg("Registering Commands");
        registerCommandForClass("banblock", new BanBlockCommand(this));
        registerCommandForClass("disableblock", new EnableBlockCommand(this));

        pluginmsg("Registering Listeners");
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);



        pluginmsg("Thanks for using BlockLock");
        pluginmsg("###################################");

    }

    @Override
    public void onDisable() {
    }
}
