package prospector.wander;

import net.minecraft.util.LazySingleton;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

import java.util.Random;

public class TreeGenerator extends LazySingleton {
	public final TreeFeature<DefaultFeatureConfig> feature;

	public TreeGenerator(TreeFeature<DefaultFeatureConfig> feature) {
		this.feature = feature;
	}

	@Override
	protected TreeFeature<DefaultFeatureConfig> method_11430(Random random) {
		return feature;
	}
}
