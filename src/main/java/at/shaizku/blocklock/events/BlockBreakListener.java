package at.shaizku.blocklock.events;

import at.shaizku.blocklock.Utils.FileUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static at.shaizku.blocklock.Utils.FileUtils.checkConfigFileForBoolConfiguration;

public class BlockBreakListener implements Listener {

    public BlockBreakListener() {
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (checkConfigFileForBoolConfiguration("ALLOW-OPS-BYPASS-RESTRICTIONS") && event.getPlayer().isOp()) {
            event.setCancelled(false);
            return;
        }

        if (!checkConfigFileForBoolConfiguration("ALLOW-BREAK") &&
                FileUtils.checkBannedBlocksFileForText(block.getType().name())) {
            
            event.setCancelled(true);
            event.getPlayer().sendMessage("You are not allowed to break this block.");
        }
    }
}

