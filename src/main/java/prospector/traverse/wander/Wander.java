package prospector.traverse.wander;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sortme.ItemGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.JungleGroundBushFeature;
import net.minecraft.world.gen.feature.OakTreeFeature;
import prospector.traverse.api.Traverse;
import prospector.traverse.api.feature.TraverseTreeFeature;
import prospector.traverse.wander.block.AutumnalLSCompound;
import prospector.traverse.wander.feature.MeadowFlowerFeature;

public class Wander implements ModInitializer {
	public static final String MOD_ID = "wander";

	public static final BlockState JUNGLE_LOG = Blocks.JUNGLE_LOG.getDefaultState();
	public static final BlockState JUNGLE_LEAVES = Blocks.JUNGLE_LEAVES.getDefaultState().with(Properties.PERSISTENT, false);
	public static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
	public static final BlockState OAK_LEAVES = Blocks.OAK_LEAVES.getDefaultState().with(Properties.PERSISTENT, false);

	public static final Block RED_AUTUMNAL_LEAVES;
	public static final Block RED_AUTUMNAL_SAPLING;
	public static final Block BROWN_AUTUMNAL_LEAVES;
	public static final Block BROWN_AUTUMNAL_SAPLING;
	public static final Block ORANGE_AUTUMNAL_LEAVES;
	public static final Block ORANGE_AUTUMNAL_SAPLING;
	public static final Block YELLOW_AUTUMNAL_LEAVES;
	public static final Block YELLOW_AUTUMNAL_SAPLING;

	public static final Feature<DefaultFeatureConfig> RED_AUTUMNAL_TREE;
	public static final Feature<DefaultFeatureConfig> BROWN_AUTUMNAL_TREE;
	public static final Feature<DefaultFeatureConfig> ORANGE_AUTUMNAL_TREE;
	public static final Feature<DefaultFeatureConfig> YELLOW_AUTUMNAL_TREE;
	public static final Feature<DefaultFeatureConfig> MEADOW_FLOWER;
	public static final Feature<DefaultFeatureConfig> MINI_JUNGLE_TREE;
	public static final Feature<DefaultFeatureConfig> OAK_SHRUB;

	static {
		AutumnalLSCompound redAutumnalLS = new AutumnalLSCompound();
		register("red_autumnal_leaves", RED_AUTUMNAL_LEAVES = redAutumnalLS.lsLeaves, ItemGroup.DECORATIONS);
		register("red_autumnal_sapling", RED_AUTUMNAL_SAPLING = redAutumnalLS.lsSapling, ItemGroup.DECORATIONS);

		AutumnalLSCompound brownAutumnalLS = new AutumnalLSCompound();
		register("brown_autumnal_leaves", BROWN_AUTUMNAL_LEAVES = brownAutumnalLS.lsLeaves, ItemGroup.DECORATIONS);
		register("brown_autumnal_sapling", BROWN_AUTUMNAL_SAPLING = brownAutumnalLS.lsSapling, ItemGroup.DECORATIONS);

		AutumnalLSCompound orangeAutumnalLS = new AutumnalLSCompound();
		register("orange_autumnal_leaves", ORANGE_AUTUMNAL_LEAVES = orangeAutumnalLS.lsLeaves, ItemGroup.DECORATIONS);
		register("orange_autumnal_sapling", ORANGE_AUTUMNAL_SAPLING = orangeAutumnalLS.lsSapling, ItemGroup.DECORATIONS);

		AutumnalLSCompound yellowAutumnalLS = new AutumnalLSCompound();
		register("yellow_autumnal_leaves", YELLOW_AUTUMNAL_LEAVES = yellowAutumnalLS.lsLeaves, ItemGroup.DECORATIONS);
		register("yellow_autumnal_sapling", YELLOW_AUTUMNAL_SAPLING = yellowAutumnalLS.lsSapling, ItemGroup.DECORATIONS);

		RED_AUTUMNAL_TREE = Traverse.registerFeature(MOD_ID, "red_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, Blocks.OAK_LOG.getDefaultState(), Wander.RED_AUTUMNAL_LEAVES.getDefaultState().with(Properties.PERSISTENT, false)));
		BROWN_AUTUMNAL_TREE = Traverse.registerFeature(MOD_ID, "brown_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, Blocks.OAK_LOG.getDefaultState(), Wander.BROWN_AUTUMNAL_LEAVES.getDefaultState().with(Properties.PERSISTENT, false)));
		ORANGE_AUTUMNAL_TREE = Traverse.registerFeature(MOD_ID, "orange_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, Blocks.OAK_LOG.getDefaultState(), Wander.ORANGE_AUTUMNAL_LEAVES.getDefaultState().with(Properties.PERSISTENT, false)));
		YELLOW_AUTUMNAL_TREE = Traverse.registerFeature(MOD_ID, "yellow_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, Blocks.OAK_LOG.getDefaultState(), Wander.YELLOW_AUTUMNAL_LEAVES.getDefaultState().with(Properties.PERSISTENT, false)));
		MEADOW_FLOWER = Traverse.registerFeature(MOD_ID, "meadow_flower", new MeadowFlowerFeature(DefaultFeatureConfig::deserialize));
		MINI_JUNGLE_TREE = Traverse.registerFeature(MOD_ID, "mini_jungle_tree", new OakTreeFeature(DefaultFeatureConfig::deserialize, false, 4, JUNGLE_LOG, JUNGLE_LEAVES, true));
		OAK_SHRUB = Traverse.registerFeature(MOD_ID, "oak_shrub", new JungleGroundBushFeature(DefaultFeatureConfig::deserialize, JUNGLE_LOG, JUNGLE_LEAVES));
	}

	private static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCKS, MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Builder().creativeTab(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		register(name, item);
		return block;
	}

	private static Item register(String name, Item item) {
		Registry.register(Registry.ITEMS, "wander:" + name, item);
		return item;
	}

	@Override
	public void onInitialize() {
		Traverse.registerBiomePack("wander");
	}
}
