package at.shaizku.blocklock.Utils;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

import static at.shaizku.blocklock.Constants.pluginDIR;

public class InitializeSaveFiles {


    public static void initBannedBlocksFile() {
        File file = new File(pluginDIR, "bannedblocks.yml");

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Bukkit.getLogger().info("I-> File created: " + file.getAbsolutePath());
                } else {
                    Bukkit.getLogger().info("!-> Failed to create file.");
                }
            } catch (IOException e) {
                Bukkit.getLogger().info("!-> An error occurred while creating the file: " + e.getMessage());
            }

        } else {
            Bukkit.getLogger().info("I-> File already exists: " + file.getAbsolutePath());
        }
    }

    public static void initConfigFile() {
        File file = new File(pluginDIR, "config.yml");

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Bukkit.getLogger().info("I-> File created: " + file.getAbsolutePath());
                } else {
                    Bukkit.getLogger().info("!-> Failed to create file.");
                }
            } catch (IOException e) {
                Bukkit.getLogger().info("!-> An error occurred while creating the file: " + e.getMessage());
            }

        } else {
            Bukkit.getLogger().info("I-> File already exists: " + file.getAbsolutePath());
        }
    }

    public static void initConfigFileContent() {
        //look away ;)
        if (!FileUtils.checkConfigFileForText("ALLOW-PLACE")) {
            FileUtils.appendToConfigYML("ALLOW-PLACE: false");
        }
        if (!FileUtils.checkConfigFileForText("ALLOW-BREAK")) {
            FileUtils.appendToConfigYML("ALLOW-BREAK: false");
        }
        if (!FileUtils.checkConfigFileForText("ALLOW-OPS-BYPASS-RESTRICTIONS")) {
            FileUtils.appendToConfigYML("ALLOW-OPS-BYPASS-RESTRICTIONS: true");
        }
    }
}
