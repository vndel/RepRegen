package net.drman.repregen.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.collections.MassiveMap;
import com.massivecraft.massivecore.util.PermissionUtil;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.drman.repregen.RepRegen;
import net.drman.repregen.Permission;
import net.drman.repregen.Utils;
import net.drman.repregen.entity.BPlayer;
import net.drman.repregen.entity.BlockObj;
import net.drman.repregen.entity.Conf;
import net.drman.repregen.intagration.aenchants.EngineAEnchantments;
import net.drman.repregen.intagration.customitems.EngineCustomItems;
import net.drman.repregen.intagration.ultimateskills.EngineUltimateSkills;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class BlockEngine extends Engine {
    private static BlockEngine i = new BlockEngine();
    public static BlockEngine get() {return i;}

    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        BlockObj blockObj = Conf.get().getBlock(block.getType(),block.getData());
        if(blockObj == null) return;
        if(!checkRegion(block, blockObj.getRegions())) return;
        BPlayer bPlayer = BPlayer.get(player);
        if(bPlayer == null) return;
        String id = Utils.getKey(Conf.get().blocks, blockObj);
        if(id == null) return;
        if(!PermissionUtil.hasPermission(player, Permission.OVERRIDE) && !bPlayer.getAllowedBlocks().contains(id) && !Conf.get().allowedBlocks.contains(id)) return;
        //Check if its a custom block.
        if(blockObj.isCustomBlock()) {
            if(EngineCustomItems.get().isCustomBlock(block)) {
                String customID = EngineCustomItems.get().getCustomID(block);
                List<ItemStack> drops = EngineCustomItems.get().breakCustomBlock(block);
                if(EngineAEnchantments.get().itemHasTelekinesis(player.getItemInHand())) {
                    event.setCancelled(true);
                    drops.forEach(a -> player.getInventory().addItem(a));
                    EngineCustomItems.get().reset(block.getLocation(), blockObj, customID);
                    return;
                } else {
                    event.setCancelled(true);
                    drops.forEach(a -> block.getWorld().dropItemNaturally(block.getLocation(), a));
                    EngineCustomItems.get().reset(block.getLocation(), blockObj, customID);
                    return;
                }
            } else {
                event.setCancelled(true);
                return;
            }
        }
        //Give event to skills to run what it need to.

        if(EngineAEnchantments.get().itemHasTelekinesis(player.getItemInHand())) {
            event.setCancelled(true);
            EngineAEnchantments.get().runBlockBreakEvent(event);
            EngineUltimateSkills.get().manageBlockPoints(player, block, true, true);
            this.reset(block.getLocation(), blockObj, block.getState().getData().getData());
            return;
        } else {
            event.setCancelled(true);
            block.breakNaturally();
            EngineUltimateSkills.get().manageBlockPoints(player, block, true, false);
            this.reset(block.getLocation(), blockObj, block.getState().getData().getData());
        }

    }

    public static boolean checkRegion(Block block, List<String> regions) {
        Set<ProtectedRegion> region = WGBukkit.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation()).getRegions();
        AtomicBoolean isRegion = new AtomicBoolean(false);

        region.forEach(rg -> {
            if(regions.contains(rg.getId())) {
                isRegion.set(true);
                return;
            }
        });

        return isRegion.get();
    }

    public final Map<Location, BlockObj> blocks = new MassiveMap<>();

    public void reset(Location location, BlockObj object, final int data) {
        final Block block = location.getBlock();
        final String id = EngineCustomItems.get().getCustomID(block);

        blocks.put(location, object);

        Bukkit.getScheduler().runTaskLater(RepRegen.get(), () -> block.setType(object.isToBedrock() ? Material.BEDROCK : Material.AIR), 1L);
        Bukkit.getScheduler().runTaskLater(RepRegen.get(), () ->  {
            if(object.isCustomBlock()) {
                EngineCustomItems.get().setCustomBlock(block, id);
            } else {
                if(object.isAllDataValues()) {
                    block.setType(object.getBlockID().getMaterial());
                    block.setData((byte) data);
                } else {
                    block.setType(object.getBlockID().getMaterial());
                    block.setData((byte) object.getBlockID().getData());
                }
            }
            blocks.remove(location);
        }, object.getRegenTime() * 20);
    }

}
