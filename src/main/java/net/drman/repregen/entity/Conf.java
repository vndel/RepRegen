package net.drman.repregen.entity;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Conf extends Entity<Conf> {
    private static Conf i = new Conf();
    public static Conf get() {
        return i;
    }
    public static void set(Conf conf) {i = conf;}

    public List<String> allowedBlocks = MUtil.list(
            "stone",
            "pumpkin"
    );

    public Map<String, BlockObj> blocks = MUtil.map(
            "stone", new BlockObj(new BlockID(Material.STONE)),
            "pumpkin", new BlockObj(new BlockID(Material.IRON_ORE), false, true)
    );

    public void addBlock(String id, BlockObj block) {
        this.blocks.put(id, block);
        this.changed();
    }

    public BlockObj getBlock(Material material, int data) {
        try {
            return blocks.values().stream().filter(a -> a.getBlockID().getMaterial().equals(material) && (a.isAllDataValues() ? true:a.getBlockID().getData() == data)).findFirst().get();
        }catch (NoSuchElementException e) {
            return null;
        }
    }
}
