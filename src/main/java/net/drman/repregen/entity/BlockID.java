package net.drman.repregen.entity;

import org.bukkit.Material;

public class BlockID {
    private Material material;
    private int data;

    public BlockID(Material material, int data) {
        this.material = material;
        this.data = data;
    }

    public BlockID(Material material) {
        this.material = material;
        this.data = 0;
    }

    public Material getMaterial() {
        return material;
    }

    public int getData() {
        return data;
    }
}
