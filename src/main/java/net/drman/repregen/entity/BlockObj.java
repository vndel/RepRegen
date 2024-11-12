package net.drman.repregen.entity;

import com.massivecraft.massivecore.collections.MassiveList;

import java.util.List;

public class BlockObj {
    private BlockID blockID;
    private List<String> regions;
    private boolean isCustomBlock;
    private boolean allDataValues;
    private boolean toBedrock;
    private long regenTime;

    public BlockObj(BlockID blockID) {
        this.blockID = blockID;
        this.regions = new MassiveList<>();
        this.isCustomBlock = false;
        this.allDataValues = false;
        this.toBedrock = true;
        this.regenTime = 3;
    }

    public BlockObj(BlockID blockID, boolean isCustomBlock, boolean allDataValues) {
        this.blockID = blockID;
        this.regions = new MassiveList<>();
        this.isCustomBlock = isCustomBlock;
        this.allDataValues = allDataValues;
        this.toBedrock = true;
        this.regenTime = 3;
    }

    public BlockObj(BlockID blockID, boolean isCustomBlock, boolean allDataValues, boolean toBedrock) {
        this.blockID = blockID;
        this.regions = new MassiveList<>();
        this.isCustomBlock = isCustomBlock;
        this.allDataValues = allDataValues;
        this.toBedrock = toBedrock;
        this.regenTime = 3;
    }

    public BlockObj(BlockID blockID, boolean isCustomBlock, boolean allDataValues, boolean toBedrock, long regenTime) {
        this.blockID = blockID;
        this.regions = new MassiveList<>();
        this.isCustomBlock = isCustomBlock;
        this.allDataValues = allDataValues;
        this.toBedrock = toBedrock;
        this.regenTime = regenTime;
    }

    public BlockObj(BlockID blockID, List<String> regions, boolean isCustomBlock, boolean allDataValues, boolean toBedrock, long regenTime) {
        this.blockID = blockID;
        this.regions = regions;
        this.isCustomBlock = isCustomBlock;
        this.allDataValues = allDataValues;
        this.toBedrock = toBedrock;
        this.regenTime = regenTime;
    }

    public BlockID getBlockID() {
        return blockID;
    }

    public List<String> getRegions() {
        return regions;
    }

    public boolean isAllDataValues() {
        return allDataValues;
    }

    public boolean isCustomBlock() {
        return isCustomBlock;
    }

    public boolean isToBedrock() {
        return toBedrock;
    }

    public long getRegenTime() {
        return regenTime;
    }
}
