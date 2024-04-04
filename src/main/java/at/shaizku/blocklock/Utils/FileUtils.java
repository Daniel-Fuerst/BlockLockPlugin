package at.shaizku.blocklock.Utils;

import org.bukkit.Bukkit;

import java.io.*;
public class FileUtils {

    public static void appendToBlockBannerYML(String blockTypeToAppend) {
        String filePath = "plugins/BlockLock/bannedblocks.yml";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(blockTypeToAppend);
            printWriter.close();
            fileWriter.close();

            Bukkit.getLogger().info("|-> Text appended to the file successfully.");
        } catch (IOException e) {
            Bukkit.getLogger().info("|-> An error occurred while appending to the file: " + e.getMessage());
        }
    }

    public static void appendToConfigYML(String blockTypeToAppend) {
        String filePath = "plugins/BlockLock/config.yml";
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(blockTypeToAppend);
            printWriter.close();
            fileWriter.close();

            Bukkit.getLogger().info("|-> Text appended to the file successfully.");
        } catch (IOException e) {
            Bukkit.getLogger().info("|-> An error occurred while appending to the file: " + e.getMessage());
        }
    }

    public static void removeLineFromBlockBannerYML(String blockTypeToRemove) {
        String filePath = "plugins/BlockLock/bannedblocks.yml";
        File tempFile = new File("plugins/BlockLock/temp.yml");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(blockTypeToRemove)) {
                    writer.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File originalFile = new File(filePath);
        if (!tempFile.renameTo(originalFile)) {
            Bukkit.getLogger().info("Could not rename temporary file to original file.");
        }
    }

    public static boolean checkBannedBlocksFileForText(String searchText) {
        String filePath = "plugins/BlockLock/bannedblocks.yml";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkConfigFileForText(String searchText) {
        String filePath = "plugins/BlockLock/config.yml";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkConfigFileForBoolConfiguration(String searchText) {
        String filePath = "plugins/BlockLock/config.yml";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    int colonIndex = line.indexOf(":");
                    if (colonIndex != -1 && colonIndex + 1 < line.length()) {
                        String value = line.substring(colonIndex + 1).trim();
                        return Boolean.parseBoolean(value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
