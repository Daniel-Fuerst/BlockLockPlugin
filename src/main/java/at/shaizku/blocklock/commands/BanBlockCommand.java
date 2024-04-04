package at.shaizku.blocklock.commands;

import at.shaizku.blocklock.BlockLock;
import at.shaizku.blocklock.Constants;
import at.shaizku.blocklock.Utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class BanBlockCommand implements CommandExecutor {

    private final BlockLock main;

    public BanBlockCommand(BlockLock main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(@Nonnull CommandSender sender,
                             @Nonnull Command command,
                             @Nonnull String label,
                             @Nonnull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("blocklock.ban")) {

                ItemStack itemInHand = player.getInventory().getItemInMainHand();

                if (itemInHand.getType().isBlock()) {

                    if (!FileUtils.checkBannedBlocksFileForText(String.valueOf(itemInHand.getType()))) {

                        player.sendMessage(Constants.prefix + "Block '" + ChatColor.BLUE + itemInHand.getType() + ChatColor.GRAY +
                                "' is now" + ChatColor.RED + " BANNED" + ChatColor.GRAY + "!");
                        FileUtils.appendToBlockBannerYML(String.valueOf(itemInHand.getType()));

                    } else {

                        player.sendMessage(Constants.prefix + "Block '" + ChatColor.BLUE + itemInHand.getType() + ChatColor.GRAY +
                                "' is already" + ChatColor.RED + " BANNED" + ChatColor.GRAY + "!");
                    }

                } else {
                    player.sendMessage(Constants.prefix + "Block '" + ChatColor.BLUE + itemInHand.getType() + ChatColor.GRAY + "' is " +
                            ChatColor.RED + "NOT " + ChatColor.GRAY + "a block!");

                    return false;
                }
            }
        } else {
            main.getLogger().info("You have to be a player to execute the command: /" + command.getName());
        }
        return true;
    }
}