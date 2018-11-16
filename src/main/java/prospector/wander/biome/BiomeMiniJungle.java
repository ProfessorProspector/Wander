package prospector.wander.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityFactory;
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

public class BiomeMiniJungle extends TraverseBiome {

	public static final BlockState JUNGLE_LOG = Blocks.field_10306.getDefaultState();//jungle log
	public static final BlockState JUNGLE_LEAVES = Blocks.field_10335.getDefaultState().set(Properties.PERSISTENT, false); //jungle leaves

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
		tallgrassFrequency = 16;
		flowerFrequency = 6;
		hasDoubleFlowers = false;
		vineFrequency = 50;
		melonFrequency = 1;
		tallgrassFeature = Feature.JUNGLE_GRASS;
		tallgrassFeatureConfig = FeatureConfig.DEFAULT;
	}

	@Override
	public void addVegetation() {
		super.addVegetation();
		addVegetation(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[] { Feature.LARGE_OAK_TREE }, new FeatureConfig[] {
			FeatureConfig.DEFAULT }, new float[] {
			0.1F }, new OakTreeFeature(DefaultFeatureConfig::make, false, 4, JUNGLE_LOG, JUNGLE_LEAVES, true), FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecorator(50, 0.1F, 1));
	}

	@Override
	public void addEntitySpawns() {
		super.addEntitySpawns();
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.PARROT, 40, 1, 2);
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.CHICKEN, 10, 4, 4);
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.OCELOT, 2, 1, 1);
		addEntitySpawnEntry(EntityType.WATER_CREATURE, EntityFactory.TROPICAL_FISH, 30, 5, 8);
	}
}