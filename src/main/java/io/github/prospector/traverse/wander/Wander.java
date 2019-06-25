package io.github.prospector.traverse.wander;

import io.github.prospector.silk.block.SilkLeavesBlock;
import io.github.prospector.silk.block.SilkSaplingBlock;
import io.github.prospector.silk.util.SilkSaplingGenerator;
import io.github.prospector.traverse.api.feature.TraverseTreeFeature;
import io.github.prospector.traverse.loader.api.BiomePack;
import io.github.prospector.traverse.wander.feature.FirTreeFeature;
import io.github.prospector.traverse.wander.feature.MeadowFlowerFeature;
import io.github.prospector.traverse.wander.surface.SandWithPatchesSurfaceBuilder;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.JungleGroundBushFeature;
import net.minecraft.world.gen.feature.OakTreeFeature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Map;

public class Wander implements BiomePack {

    public static final String MOD_ID = "wander";

    @Override
    public String getNamespace() {
        return MOD_ID;
    }

    public static final Block RED_AUTUMNAL_LEAVES = register("red_autumnal_leaves", new SilkLeavesBlock(), ItemGroup.DECORATIONS);
    public static final Block RED_AUTUMNAL_SAPLING = register("red_autumnal_sapling", new SilkSaplingBlock(new SilkSaplingGenerator(() -> new TraverseTreeFeature(DefaultFeatureConfig::deserialize, true, Blocks.DARK_OAK_LOG.getDefaultState(), Wander.RED_AUTUMNAL_LEAVES.getDefaultState()))), ItemGroup.DECORATIONS);
    public static final Block BROWN_AUTUMNAL_LEAVES = register("brown_autumnal_leaves", new SilkLeavesBlock(), ItemGroup.DECORATIONS);
    public static final Block BROWN_AUTUMNAL_SAPLING = register("brown_autumnal_sapling", new SilkSaplingBlock(new SilkSaplingGenerator(() -> new TraverseTreeFeature(DefaultFeatureConfig::deserialize, true, 5, Blocks.OAK_LOG.getDefaultState(), Wander.BROWN_AUTUMNAL_LEAVES.getDefaultState()))), ItemGroup.DECORATIONS);
    public static final Block ORANGE_AUTUMNAL_LEAVES = register("orange_autumnal_leaves", new SilkLeavesBlock(), ItemGroup.DECORATIONS);
    public static final Block ORANGE_AUTUMNAL_SAPLING = register("orange_autumnal_sapling", new SilkSaplingBlock(new SilkSaplingGenerator(() -> new TraverseTreeFeature(DefaultFeatureConfig::deserialize, true, Blocks.OAK_LOG.getDefaultState(), Wander.ORANGE_AUTUMNAL_LEAVES.getDefaultState()))), ItemGroup.DECORATIONS);
    public static final Block YELLOW_AUTUMNAL_LEAVES = register("yellow_autumnal_leaves", new SilkLeavesBlock(), ItemGroup.DECORATIONS);
    public static final Block YELLOW_AUTUMNAL_SAPLING = register("yellow_autumnal_sapling", new SilkSaplingBlock(new SilkSaplingGenerator(() -> new TraverseTreeFeature(DefaultFeatureConfig::deserialize, true, 6, Blocks.BIRCH_LOG.getDefaultState(), Wander.YELLOW_AUTUMNAL_LEAVES.getDefaultState()))), ItemGroup.DECORATIONS);

    public static final Block FIR_LOG = register("fir_log", new LogBlock(MaterialColor.BROWN, FabricBlockSettings.copy(Blocks.DARK_OAK_LOG).build()), ItemGroup.DECORATIONS);
    public static final Block FIR_LEAVES = register("fir_leaves", new SilkLeavesBlock(), ItemGroup.DECORATIONS);
    public static final Block FIR_SAPLING = register("fir_sapling", new SilkSaplingBlock(new SilkSaplingGenerator(() -> new FirTreeFeature(DefaultFeatureConfig::deserialize, true))), ItemGroup.DECORATIONS);

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
        features.put("fir_tree", new FirTreeFeature(DefaultFeatureConfig::deserialize, false));
    }

    @Override
    public void addSurfaceBuilders(final Map<String, SurfaceBuilder<?>> surfaceBuilders) {
        surfaceBuilders.put("sand_with_patches", new SandWithPatchesSurfaceBuilder(TernarySurfaceConfig::deserialize));
    }

}
