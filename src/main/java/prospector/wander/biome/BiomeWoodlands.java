package prospector.wander.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.config.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.config.feature.FeatureConfig;
import net.minecraft.world.gen.config.feature.RandomFeatureConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.JungleGroundBushFeature;

public class BiomeWoodlands extends TraverseBiome {

	public static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
	public static final BlockState OAK_LEAVES = Blocks.OAK_LEAVES.getDefaultState().with(Properties.PERSISTENT, false);

	public static final Biome.Category CATEGORY = Biome.Category.FOREST;
	public static final float DEPTH = 0.15F;
	public static final float SCALE = 0.05F;
	public static final float TEMPERATURE = 0.8F;
	public static final float DOWNFALL = 0.4F;
	public static final int GRASS_COLOR = 0xFF99A955;
	public static final int FOLIAGE_COLOR = 0xFF849E4A;

	//	protected static final FeatureFallenTree FALLEN_TREE_FEATURE = new FeatureFallenTree(true);
	protected static final JungleGroundBushFeature OAK_SHRUB = new JungleGroundBushFeature(DefaultFeatureConfig::make, OAK_LOG, OAK_LEAVES);

	public BiomeWoodlands() {
		super(Biome.Category.FOREST, DEPTH, SCALE, TEMPERATURE, DOWNFALL);
	}

	@Override
	protected void overrideOptions() {
		grassFrequency = 16;
		flowerFrequency = 6;
		hasDoubleFlowers = false;
	}

	@Override
	public void addVegetation() {
		super.addVegetation();
		addVegetation(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[] { OAK_SHRUB }, new FeatureConfig[] {
			FeatureConfig.DEFAULT }, new float[] { 0.2F }, Feature.OAK_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_HEIGHTMAP, new CountDecoratorConfig(5));
	}

	@Override
	public void addEntitySpawns() {
		super.addEntitySpawns();
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.WOLF, 2, 4, 4);
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