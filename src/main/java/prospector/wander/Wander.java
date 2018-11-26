package prospector.wander;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.gui.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.block.ItemBlock;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import prospector.wander.biome.BiomeAutumnalWoods;
import prospector.wander.biome.BiomeMeadow;
import prospector.wander.biome.BiomeMiniJungle;
import prospector.wander.biome.BiomeWoodlands;
import prospector.wander.block.AutumnalLSCompound;

public class Wander implements ModInitializer {
	public static final String MOD_ID = "wander";

	public static int currentId = 180;

	public static final Block RED_AUTUMNAL_LEAVES;
	public static final Block RED_AUTUMNAL_SAPLING;
	public static final Block BROWN_AUTUMNAL_LEAVES;
	public static final Block BROWN_AUTUMNAL_SAPLING;
	public static final Block ORANGE_AUTUMNAL_LEAVES;
	public static final Block ORANGE_AUTUMNAL_SAPLING;
	public static final Block YELLOW_AUTUMNAL_LEAVES;
	public static final Block YELLOW_AUTUMNAL_SAPLING;

	public static final Biome AUTUMNAL_WOODS;
	public static final Biome MEADOW;
	public static final Biome MINI_JUNGLE;
	public static final Biome WOODLANDS;

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

		AUTUMNAL_WOODS = register(nextId(), "autumnal_woods", new BiomeAutumnalWoods());
		MEADOW = register(nextId(), "meadow", new BiomeMeadow());
		MINI_JUNGLE = register(nextId(), "mini_jungle", new BiomeMiniJungle());
		WOODLANDS = register(nextId(), "woodlands", new BiomeWoodlands());
	}

	private static Biome register(int rawId, String name, Biome biome) {
		Registry.register(Registry.BIOMES, MOD_ID + "" + name, biome);
		if (biome.hasParent()) {
			Biome.PARENT_BIOME_ID_MAP.add(biome);
		}

		return biome;
	}

	private static Block register(String name, Block block, ItemGroup tab) {
		Registry.register(Registry.BLOCKS, MOD_ID + ":" + name, block);
		ItemBlock item = new ItemBlock(block, new Item.Builder().creativeTab(tab));
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		register(name, item);
		return block;
	}

	private static Item register(String name, Item item) {
		Registry.register(Registry.ITEMS, "wander:" + name, item);
		return item;
	}

	public static int nextId() {
		return currentId += 1;
	}

	@Override
	public void onInitialize() {

	}
}
