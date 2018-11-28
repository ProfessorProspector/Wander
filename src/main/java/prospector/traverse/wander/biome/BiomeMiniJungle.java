package prospector.traverse.wander.biome;

import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.config.decorator.CountExtraChanceDecorator;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.config.feature.FeatureConfig;
import net.minecraft.world.gen.config.feature.RandomFeatureConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OakTreeFeature;
import prospector.traverse.api.biome.OLDTRAVERSEBIOME;

public class BiomeMiniJungle extends OLDTRAVERSEBIOME {

	public static final BlockState JUNGLE_LOG = Blocks.JUNGLE_LOG.getDefaultState();
	public static final BlockState JUNGLE_LEAVES = Blocks.JUNGLE_LEAVES.getDefaultState().with(Properties.PERSISTENT, false);

	public static final Biome.Category CATEGORY = Biome.Category.JUNGLE;
	public static final float DEPTH = -0.1F;
	public static final float SCALE = 0.45F;
	public static final float TEMPERATURE = 0.95F;
	public static final float DOWNFALL = 0.9F;
	public static final int WATER_COLOR = 0x003320;
	public static final int WATER_FOG_COLOR = 0x052721;

	public BiomeMiniJungle() {
		super(Biome.Category.FOREST, DEPTH, SCALE, TEMPERATURE, DOWNFALL, WATER_COLOR, WATER_FOG_COLOR);
	}

	@Override
	protected void overrideOptions() {
		grassFrequency = 16;
		flowerFrequency = 6;
		hasDoubleFlowers = false;
		vineFrequency = 50;
		melonFrequency = 1;
		grassFeature = Feature.JUNGLE_GRASS;
		grassFeatureConfig = FeatureConfig.DEFAULT;
	}

	@Override
	public void addVegetation() {
		super.addVegetation();
		addVegetation(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[] { Feature.LARGE_OAK_TREE }, new FeatureConfig[] {
			FeatureConfig.DEFAULT }, new float[] {
			0.1F }, new OakTreeFeature(DefaultFeatureConfig::deserialize, false, 4, JUNGLE_LOG, JUNGLE_LEAVES, true), FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecorator(50, 0.1F, 1));
	}

	@Override
	public void addEntitySpawns() {
		super.addEntitySpawns();
		addEntitySpawnEntry(EntityCategory.CREATURE, EntityType.PARROT, 40, 1, 2);
		addEntitySpawnEntry(EntityCategory.CREATURE, EntityType.CHICKEN, 10, 4, 4);
		addEntitySpawnEntry(EntityCategory.CREATURE, EntityType.OCELOT, 2, 1, 1);
		addEntitySpawnEntry(EntityCategory.WATER_CREATURE, EntityType.TROPICAL_FISH, 30, 5, 8);
	}
}