package net.drman.repregen.entity;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.SenderEntity;
import net.drman.repregen.coll.BPlayerColl;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BPlayer extends SenderEntity<BPlayer> {

    public static BPlayer get(Object object) {
        return BPlayerColl.get().get(object);
    }

    private Set<String> allowedBlocks = new HashSet<>();

    public List<String> getAllowedBlocks() {
        return new MassiveList<>(allowedBlocks);
    }

    public void addBlock(String blockID) {
        this.allowedBlocks.add(blockID);
        this.changed();
    }

    public void removeBlock(String blockID) {
        this.allowedBlocks.remove(blockID);
        this.changed();
    }
}
