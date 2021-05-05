package com.suitedllama.vanillatanks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TankBlock extends Block {

    public static final IntProperty LEVEL;
    public final Item ITEM;

    public TankBlock(Item item, Settings settings) {
        super(settings);
        this.ITEM = item;
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(this.getLevelProperty(), 1));
    }

    public IntProperty getLevelProperty() {
        return LEVEL;
    }
    public int getMaxFill() {
        return 9;
    }

    public boolean isFull(BlockState state) {
        return (Integer)state.get(this.getLevelProperty()) >= this.getMaxFill();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    int i = (Integer)state.get(LEVEL);
    if(player.isHolding(ITEM)) {
        if (isFull(state)) {
            return ActionResult.FAIL;
        } else if (!isFull(state)) {
            world.setBlockState(pos, (BlockState) state.with(LEVEL, i + 1), 2);
        }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    static {
        LEVEL = Properties.LEVEL_1_8;
    }
}
