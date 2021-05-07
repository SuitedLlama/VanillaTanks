package net.minecraft.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum TankProperties implements StringIdentifiable {
    EMPTY("empty"),
    WATER("water"),
    LAVA("lava"),
    EXPERIENCE("experience"),
    POWDERED_SNOW("powdered_snow");

    private final String name;

    private TankProperties(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }
}
