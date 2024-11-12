package net.drman.repregen.intagration.customitems;

import com.jojodmo.customitems.api.CustomItemsAPI;
import com.jojodmo.customitems.item.custom.CustomItem;
import com.jojodmo.customitems.item.custom.block.CustomItemBlockHandler;
import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.collections.MassiveList;
import net.drman.repregen.RepRegen;
import net.drman.repregen.engine.BlockEngine;
import net.drman.repregen.entity.BlockObj;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineCustomItems extends Engine {
    private static EngineCustomItems i = new EngineCustomItems();
    public static EngineCustomItems get() {return i;}

    public boolean isCustomBlock(Block block) {
        if(!isActive()) return false;
        return CustomItemsAPI.getCustomItemIDAtBlock(block) != null;
    }

    public List<ItemStack> breakCustomBlock(Block block) {
        if(!isActive()) return new MassiveList<>();
        CustomItem ci = CustomItemBlockHandler.get(block.getLocation());
        List<ItemStack> items = new MassiveList<>();
        ci.getBlockItemDrops().forEach(genericItem -> items.add(genericItem.getItemStack()));
        CustomItemsAPI.breakCustomItemAtBlock(block, true);
        return items;
        //return CustomItemsAPI.breakCustomItemBlockWithoutDrops(block, player).getValue();
    }

    public String getCustomID(Block block) {
        if(!isActive()) return null;
        return CustomItemsAPI.getCustomItemIDAtBlock(block);
    }

    public void setCustomBlock(Block block, String id) {
        if(!isActive()) return;
        CustomItemsAPI.setCustomItemIDAtBlock(block, id, false);
    }

    public Map<Location, String> blocks = new HashMap<>();

    public void reset(Location location, BlockObj object, String id) {
        if(!isActive()) return;
        final Block block = location.getBlock();

        blocks.put(location, id);

        Bukkit.getScheduler().runTaskLater(RepRegen.get(), () -> block.setType(object.isToBedrock() ? Material.BEDROCK : Material.AIR), 1L);
        Bukkit.getScheduler().runTaskLater(RepRegen.get(), () ->  {
            EngineCustomItems.get().setCustomBlock(block, id);
            blocks.remove(location);
        }, object.getRegenTime() * 20);
    }

    public void resetBlocks() {
        if(!isActive()) return;
        blocks.forEach((location, id) -> this.setCustomBlock(location.getBlock(), id));
    }
}
