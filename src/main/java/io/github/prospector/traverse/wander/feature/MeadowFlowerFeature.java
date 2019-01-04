package io.github.prospector.traverse.wander.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FlowerFeature;

import java.util.Random;
import java.util.function.Function;

public class MeadowFlowerFeature extends FlowerFeature {

	public MeadowFlowerFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	@Override
	public BlockState method_13175(Random rand, BlockPos pos) {
		int r = rand.nextInt(12);
		if (r == 0 || r == 1 || r == 2) {
			return Blocks.POPPY.getDefaultState();
		} else if (r == 3 || r == 4 || r == 5) {
			return Blocks.AZURE_BLUET.getDefaultState();
		} else if (r == 6 || r == 7 || r == 8) {
			return Blocks.OXEYE_DAISY.getDefaultState();
		} else if (r == 9 || r == 10) {
			return Blocks.DANDELION.getDefaultState();
		} else {
			int t = rand.nextInt(5);
			if (t == 0) {
				return Blocks.ORANGE_TULIP.getDefaultState();
			} else if (t == 1) {
				return Blocks.PINK_TULIP.getDefaultState();
			} else if (t == 2) {
				return Blocks.RED_TULIP.getDefaultState();
			} else {
				return Blocks.WHITE_TULIP.getDefaultState();
			}
		}
	}

	@Override
	public boolean generate(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorSettings> class_2794, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
		return method_13176(iWorld, class_2794, random, blockPos, defaultFeatureConfig);
	}
}
