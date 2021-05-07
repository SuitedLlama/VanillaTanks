package com.suitedllama.vanillatanks.mixin;

import com.suitedllama.vanillatanks.TankBlock;
import com.suitedllama.vanillatanks.VanillaTanks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.TankProperties;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BucketItem.class)
public abstract class ExampleMixin extends Item {

	@Shadow @Final private Fluid fluid;

	public ExampleMixin(Settings settings) {
		super(settings);
	}
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		if(world.getBlockState(pos).isOf(VanillaTanks.TANK)){
			BlockState state = context.getWorld().getBlockState(pos);
			int i = state.get(TankBlock.LEVEL);
			if(i < 9){
				if((this.fluid instanceof WaterFluid)){
					world.setBlockState(pos, state.with(TankBlock.LEVEL, i + 1).with(TankBlock.FILLED_WITH, TankProperties.WATER), 2);
				}
				if((this.fluid instanceof LavaFluid)){
					world.setBlockState(pos, state.with(TankBlock.LEVEL, i + 1).with(TankBlock.FILLED_WITH, TankProperties.LAVA), 2);
				}
			}
		}


		return super.useOnBlock(context);
	}
	public void useOnTank(World world){

	}
}
