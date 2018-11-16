package prospector.wander.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.class_2794;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettingsImpl;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FlowerFeature;

import java.util.Random;
import java.util.function.Function;

public class MeadowFlowerFeature extends FlowerFeature {

	public MeadowFlowerFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	public BlockState method_13175(Random random, BlockPos pos) {
		int r = random.nextInt(12);
		if (r == 0 || r == 1 || r == 2) {
			return Blocks.field_10449.getDefaultState();
		} else if (r == 3 || r == 4 || r == 5) {
			return Blocks.field_10573.getDefaultState();
		} else if (r == 6 || r == 7 || r == 8) {
			return Blocks.field_10554.getDefaultState();
		} else if (r == 9 || r == 10) {
			return Blocks.field_10182.getDefaultState();
		} else {
			int t = random.nextInt(5);
			if (t == 0) {
				return Blocks.field_10048.getDefaultState();
			} else if (t == 1) {
				return Blocks.field_10315.getDefaultState();
			} else if (t == 2) {
				return Blocks.field_10270.getDefaultState();
			} else {
				return Blocks.field_10156.getDefaultState();
			}
		}
	}

	@Override
	public boolean generate(IWorld iWorld, class_2794<? extends ChunkGeneratorSettingsImpl> class_2794, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
		return method_13176(iWorld, class_2794, random, blockPos, defaultFeatureConfig);
	}
}
