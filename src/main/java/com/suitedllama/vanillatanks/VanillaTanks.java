package com.suitedllama.vanillatanks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VanillaTanks implements ModInitializer {

	public static final String MOD_ID = "vanillatanks";

	public static final Block WATER_TANK = new TankBlock(Items.WATER_BUCKET, FabricBlockSettings.copy(Blocks.CAULDRON));

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID,"water_tank"), WATER_TANK);
	}
}
