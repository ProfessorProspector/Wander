package prospector.wander.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationSteps;
import net.minecraft.world.gen.config.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.config.feature.FeatureConfig;
import net.minecraft.world.gen.config.feature.RandomRandomFeatureConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import prospector.wander.Wander;
import prospector.wander.feature.TraverseTreeFeature;

public class BiomeAutumnalWoods extends TraverseBiome {

	public static final Biome.Category CATEGORY = Biome.Category.FOREST;
	public static final float DEPTH = 0.2F;
	public static final float SCALE = 0.05F;
	public static final float TEMPERATURE = 0.8F;
	public static final float DOWNFALL = 0.4F;
	public static final int GRASS_COLOR = 0xFFD6C23D;
	public static final int FOLIAGE_COLOR = 0xFFD2D31F;

	public static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
	public static Feature redAutumnalTree = new TraverseTreeFeature(DefaultFeatureConfig::make, false, OAK_LOG, Wander.redAutumnalLeaves.getDefaultState().set(Properties.PERSISTENT, false));
	public static Feature brownAutumnalTree = new TraverseTreeFeature(DefaultFeatureConfig::make, false, OAK_LOG, Wander.brownAutumnalLeaves.getDefaultState().set(Properties.PERSISTENT, false));
	public static Feature orangeAutumnalTree = new TraverseTreeFeature(DefaultFeatureConfig::make, true, OAK_LOG, Wander.orangeAutumnalLeaves.getDefaultState().set(Properties.PERSISTENT, false));
	public static Feature yellowAutumnalTree = new TraverseTreeFeature(DefaultFeatureConfig::make, true, OAK_LOG, Wander.yellowAutumnalLeaves.getDefaultState().set(Properties.PERSISTENT, false));

	public BiomeAutumnalWoods() {
		super(Biome.Category.FOREST, DEPTH, SCALE, TEMPERATURE, DOWNFALL);
	}

	@Override
	protected void overrideOptions() {

	}

	@Override
	public void addVegetation() {
		super.addVegetation();
		this.addFeature(GenerationSteps.FeatureStep.VEGETAL_DECORATION, configureFeature(Feature.RANDOM_RANDOM_SELECTOR, new RandomRandomFeatureConfig(new Feature[] { Feature.OAK_TREE, redAutumnalTree,
			brownAutumnalTree, orangeAutumnalTree, yellowAutumnalTree }, new FeatureConfig[] { FeatureConfig.DEFAULT, FeatureConfig.DEFAULT, FeatureConfig.DEFAULT, FeatureConfig.DEFAULT,
			FeatureConfig.DEFAULT }, 2), Decorator.COUNT_HEIGHTMAP, new CountDecoratorConfig(10)));
	}

	@Override
	public void addEntitySpawns() {
		super.addEntitySpawns();
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.WOLF, 5, 4, 4);
	}

	@Override
	public int getGrassColorAt(BlockPos pos) {
		return GRASS_COLOR;
	}

	@Override
	public int getFoliageColorAt(BlockPos pos) {
		return FOLIAGE_COLOR;
	}
}
