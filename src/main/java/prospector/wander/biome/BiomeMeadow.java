package prospector.wander.biome;

import net.minecraft.entity.EntityFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.config.decorator.CountExtraChanceDecorator;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.config.feature.FeatureConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FlowerFeature;
import prospector.wander.feature.MeadowFlowerFeature;

public class BiomeMeadow extends TraverseBiome {

	public static final Biome.Category CATEGORY = Biome.Category.FOREST;
	public static final float DEPTH = 0.1F;
	public static final float SCALE = 0.0F;
	public static final float TEMPERATURE = 0.8F;
	public static final float DOWNFALL = 0.7F;
	public static final int GRASS_COLOR = 0xFF65CC53;
	public static final int FOLIAGE_COLOR = 0xFF5DD64A;

	public static final FlowerFeature MEADOW_FLOWERS = new MeadowFlowerFeature(DefaultFeatureConfig::make);

	public BiomeMeadow() {
		super(Biome.Category.FOREST, DEPTH, SCALE, TEMPERATURE, DOWNFALL);
	}

	@Override
	protected void overrideOptions() {
		tallgrassFrequency = 15;
		flowerFrequency = 15;
		sugarCaneFrequency = 2;
		hasDoubleFlowers = false;
		flowerFeature = MEADOW_FLOWERS;
	}

	@Override
	public void addVegetation() {
		super.addVegetation();
		addVegetation(Feature.OAK_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecorator(0, 0.2F, 1));
	}

	@Override
	public void addEntitySpawns() {
		super.addEntitySpawns();
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.HORSE, 5, 2, 4);
		addEntitySpawnEntry(EntityType.CREATURE, EntityFactory.DONKEY, 1, 1, 2);
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