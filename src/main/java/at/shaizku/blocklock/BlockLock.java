package at.shaizku.blocklock;

import at.shaizku.blocklock.Utils.InitializeSaveFiles;
import at.shaizku.blocklock.commands.BanBlockCommand;
import at.shaizku.blocklock.commands.EnableBlockCommand;
import at.shaizku.blocklock.events.BlockBreakListener;
import at.shaizku.blocklock.events.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.File;

import static at.shaizku.blocklock.Constants.*;

public final class BlockLock extends JavaPlugin {

    private void registerCommandForClass(final String CommandName, CommandExecutor commandExecutor) {
        @Nullable PluginCommand command;
        command = getServer().getPluginCommand(CommandName);
        if (command != null) {
            command.setExecutor(commandExecutor);
            Bukkit.getLogger().info("I-> Successfully registered command '" +
                    CommandName + "'");
        } else {
            Bukkit.getLogger().info("!-> registerCommandForClass: Command '" + CommandName + "' not found, " +
                    "probably missing in plugin.yml?");
        }
    }

    public void pluginmsg(final String Message) {
        Bukkit.getLogger().info(Message);
    }

    @Override
    public void onEnable() {
        // I hate this section because its so fucking hard to add new lines
        // Please, if you read this, think about how I can make this better
        pluginmsg("###################################");
        pluginmsg("Starting BlockLock");
        pluginmsg("|-> By shaizku");

        pluginmsg("X---- Checking Files -----");

        pluginmsg("|-- Checking Plugin folder ");
        File PluginFolder = new File(pluginDIRString);
        if (!PluginFolder.exists()) {
            pluginmsg("I-> Plugin Folder not found. Creating...");
            PluginFolder.mkdir();
        } else {
            pluginmsg("I-> Plugin Folder already exists");
        }
        pluginmsg("|");
        pluginmsg("|-- Checking Save Files");

        InitializeSaveFiles.initConfigFile();
        InitializeSaveFiles.initBannedBlocksFile();

        pluginmsg("|");
        pluginmsg("|-- Checking File Contents");

        InitializeSaveFiles.initConfigFileContent();

        pluginmsg("|");
        pluginmsg("X---- Registering Commands -----");
        registerCommandForClass("disableblock", new BanBlockCommand(this));
        registerCommandForClass("enableblock", new EnableBlockCommand(this));

        pluginmsg("X---- Registering Listeners -----");
        pluginmsg("I-> Registering BlockPlaceListener");
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        pluginmsg("I-> Registering BlockBreakListener");
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        pluginmsg("!-> Thanks for using BlockLock");
        pluginmsg("###################################");

    }

    @Override
    public void onDisable() {
    }
}
