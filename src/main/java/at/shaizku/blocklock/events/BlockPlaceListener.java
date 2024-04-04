package at.shaizku.blocklock.events;

import at.shaizku.blocklock.BlockLock;
import at.shaizku.blocklock.Utils.FileUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static at.shaizku.blocklock.Utils.FileUtils.checkConfigFileForBoolConfiguration;

public class BlockPlaceListener implements Listener {

    private final BlockLock main;

    public BlockPlaceListener(BlockLock main) {
        this.main = main;
    }


    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if (checkConfigFileForBoolConfiguration("ALLOW-OPS-BYPASS-RESTRICTIONS") && event.getPlayer().isOp()) {
            event.setCancelled(false);
            return;
        }

        if (!checkConfigFileForBoolConfiguration("ALLOW-PLACE") &&
                FileUtils.checkBannedBlocksFileForText(block.getType().name())) {

                event.setCancelled(true);
                event.getPlayer().sendMessage("You are not allowed to place this block.");
        }
    }
}

