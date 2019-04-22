package io.github.prospector.traverse.wander;

import io.github.prospector.traverse.api.BiomePack;
import io.github.prospector.traverse.api.TraverseAPI;
import io.github.prospector.traverse.api.command.FindBiomeCommand;
import io.github.prospector.traverse.api.feature.TraverseTreeFeature;
import io.github.prospector.traverse.api.json.BiomePackLoader;
import io.github.prospector.traverse.wander.block.AutumnalLSCompound;
import io.github.prospector.traverse.wander.feature.MeadowFlowerFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.JungleGroundBushFeature;
import net.minecraft.world.gen.feature.OakTreeFeature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Wander implements ModInitializer, BiomePack {
	public static final String MOD_ID = "wander";

	public static final Block RED_AUTUMNAL_LEAVES;
	public static final Block RED_AUTUMNAL_SAPLING;
	public static final Block BROWN_AUTUMNAL_LEAVES;
	public static final Block BROWN_AUTUMNAL_SAPLING;
	public static final Block ORANGE_AUTUMNAL_LEAVES;
	public static final Block ORANGE_AUTUMNAL_SAPLING;
	public static final Block YELLOW_AUTUMNAL_LEAVES;
	public static final Block YELLOW_AUTUMNAL_SAPLING;

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
	}

	private static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCK, MOD_ID + ":" + name, block);
		BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		register(name, item);
		return block;
	}

	private static Item register(String name, Item item) {
		Registry.register(Registry.ITEM, "wander:" + name, item);
		return item;
	}

	@Override
	public void addFeatures(Map<String, Feature<?>> features) {
		features.put("red_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, Blocks.DARK_OAK_LOG.getDefaultState(), Wander.RED_AUTUMNAL_LEAVES.getDefaultState()));
		features.put("brown_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, 5, Blocks.OAK_LOG.getDefaultState(), Wander.BROWN_AUTUMNAL_LEAVES.getDefaultState()));
		features.put("orange_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, Blocks.OAK_LOG.getDefaultState(), Wander.ORANGE_AUTUMNAL_LEAVES.getDefaultState()));
		features.put("yellow_autumnal_tree", new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, 6, Blocks.BIRCH_LOG.getDefaultState(), Wander.YELLOW_AUTUMNAL_LEAVES.getDefaultState()));
		features.put("meadow_flower", new MeadowFlowerFeature(DefaultFeatureConfig::deserialize));
		features.put("mini_jungle_tree", new OakTreeFeature(DefaultFeatureConfig::deserialize, false, 4, Blocks.JUNGLE_LOG.getDefaultState(), Blocks.JUNGLE_LEAVES.getDefaultState(), true));
		features.put("oak_shrub", new JungleGroundBushFeature(DefaultFeatureConfig::deserialize, Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()));
	}

	@Override
	public void onInitialize() {
		TraverseAPI.registerBiomePack("wander", this);
		File biomepacksDir = new File(FabricLoader.INSTANCE.getGameDirectory(), "biomepacks");
		if (!biomepacksDir.exists()) {
			biomepacksDir.mkdir();
		}
		for (String id : TraverseAPI.BIOME_PACKS.keySet()) {
			{
				Map<String, Feature<?>> map = new HashMap<>();
				TraverseAPI.BIOME_PACKS.get(id).addFeatures(map);
				map.keySet().forEach(s -> Registry.register(Registry.FEATURE, new Identifier(id, s), map.get(s)));
			}
			{
				Map<String, Carver<?>> map = new HashMap<>();
				TraverseAPI.BIOME_PACKS.get(id).addCarvers(map);
				map.keySet().forEach(s -> Registry.register(Registry.CARVER, new Identifier(id, s), map.get(s)));
			}
			{
				Map<String, Decorator<?>> map = new HashMap<>();
				TraverseAPI.BIOME_PACKS.get(id).addDecorators(map);
				map.keySet().forEach(s -> Registry.register(Registry.DECORATOR, new Identifier(id, s), map.get(s)));
			}
			{
				Map<String, SurfaceBuilder<?>> map = new HashMap<>();
				TraverseAPI.BIOME_PACKS.get(id).addSurfaceBuilders(map);
				map.keySet().forEach(s -> Registry.register(Registry.SURFACE_BUILDER, new Identifier(id, s), map.get(s)));
			}
		}
		new BiomePackLoader().loadBiomePacks();
		CommandRegistry.INSTANCE.register(false, FindBiomeCommand::register);
	}
}
