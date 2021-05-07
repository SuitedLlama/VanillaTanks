package com.suitedllama.vanillatanks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.TankProperties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TankBlock extends Block {

    public static final IntProperty LEVEL = IntProperty.of("level", 0 ,9);
    public static final EnumProperty<TankProperties> FILLED_WITH = EnumProperty.of("filled_with", TankProperties.class);

    public TankBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(this.getLevelProperty(), 0).with(FILLED_WITH, TankProperties.EMPTY));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL, FILLED_WITH);
    }


    public IntProperty getLevelProperty() {
        return LEVEL;
    }

    public int getMaxFill() {
        return 9;
    }

    public boolean isFull(BlockState state) {
        return state.get(this.getLevelProperty()) >= this.getMaxFill();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        fill(state, world, pos, player, hand);
        empty(state, world, pos, player, hand);
        return ActionResult.FAIL;
    }


    protected ActionResult empty(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand){
    int i = state.get(LEVEL);
        if(player.isHolding(Items.BUCKET)){
            if(state.get(LEVEL) > 1) {
                if (state.get(FILLED_WITH).asString().equals("water")) {
                    world.setBlockState(pos, state.with(LEVEL, i - 1).with(FILLED_WITH, TankProperties.WATER), 2);
                }
                if (state.get(FILLED_WITH).asString().equals("lava")) {
                    world.setBlockState(pos, state.with(LEVEL, i - 1).with(FILLED_WITH, TankProperties.LAVA), 2);
                }
                if (state.get(FILLED_WITH).asString().equals("experience")) {
                    world.setBlockState(pos, state.with(LEVEL, i - 1).with(FILLED_WITH, TankProperties.EXPERIENCE), 2);
                }
                if (state.get(FILLED_WITH).asString().equals("powdered_snow")) {
                    world.setBlockState(pos, state.with(LEVEL, i - 1).with(FILLED_WITH, TankProperties.POWDERED_SNOW), 2);
                }
            }
            if(state.get(LEVEL) == 1) {
            }
        }
        return ActionResult.FAIL;
    }
        protected ActionResult fill(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand){
        return ActionResult.FAIL;
    }

}
