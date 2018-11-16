package prospector.wander;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.gui.CreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.block.ItemBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import prospector.wander.biome.BiomeAutumnalWoods;
import prospector.wander.biome.BiomeMeadow;
import prospector.wander.biome.BiomeMiniJungle;
import prospector.wander.biome.BiomeWoodlands;
import prospector.wander.block.AutumnalLSCompound;

public class Wander implements ModInitializer {
	public static int currentId = 180;
	public static final Block redAutumnalLeaves;
	public static final Block redAutumnalSapling;
	public static final Block brownAutumnalLeaves;
	public static final Block brownAutumnalSapling;
	public static final Block orangeAutumnalLeaves;
	public static final Block orangeAutumnalSapling;
	public static final Block yellowAutumnalLeaves;
	public static final Block yellowAutumnalSapling;

	public static final Biome AUTUMNAL_WOODS;
	public static final Biome MEADOW;
	public static final Biome MINI_JUNGLE;
	public static final Biome WOODLANDS;

	static {
		AutumnalLSCompound redAutumnalLS = new AutumnalLSCompound();
		register("red_autumnal_leaves", redAutumnalLeaves = redAutumnalLS.lsLeaves, CreativeTab.DECORATIONS);
		register("red_autumnal_sapling", redAutumnalSapling = redAutumnalLS.lsSapling, CreativeTab.DECORATIONS);

		AutumnalLSCompound brownAutumnalLS = new AutumnalLSCompound();
		register("brown_autumnal_leaves", brownAutumnalLeaves = brownAutumnalLS.lsLeaves, CreativeTab.DECORATIONS);
		register("brown_autumnal_sapling", brownAutumnalSapling = brownAutumnalLS.lsSapling, CreativeTab.DECORATIONS);

		AutumnalLSCompound orangeAutumnalLS = new AutumnalLSCompound();
		register("orange_autumnal_leaves", orangeAutumnalLeaves = orangeAutumnalLS.lsLeaves, CreativeTab.DECORATIONS);
		register("orange_autumnal_sapling", orangeAutumnalSapling = orangeAutumnalLS.lsSapling, CreativeTab.DECORATIONS);

		AutumnalLSCompound yellowAutumnalLS = new AutumnalLSCompound();
		register("yellow_autumnal_leaves", yellowAutumnalLeaves = yellowAutumnalLS.lsLeaves, CreativeTab.DECORATIONS);
		register("yellow_autumnal_sapling", yellowAutumnalSapling = yellowAutumnalLS.lsSapling, CreativeTab.DECORATIONS);

		AUTUMNAL_WOODS = register(nextId(), "autumnal_woods", new BiomeAutumnalWoods());
		MEADOW = register(nextId(), "meadow", new BiomeMeadow());
		MINI_JUNGLE = register(nextId(), "mini_jungle", new BiomeMiniJungle());
		WOODLANDS = register(nextId(), "woodlands", new BiomeWoodlands());
	}

	private static Biome register(int rawId, String name, Biome biome) {
		Registry.register(Registry.BIOMES, "wander:" + name, biome);
		if (biome.hasParent()) {
			Biome.PARENT_BIOME_ID_MAP.add(biome, Registry.BIOMES.getRawId(Registry.BIOMES.get(new Identifier(biome.getParent()))));
		}

		return biome;
	}

	private static Block register(String name, Block block, CreativeTab tab) {
		Registry.register(Registry.BLOCKS, "wander:" + name, block);
		ItemBlock item = new ItemBlock(block, new Item.Builder().creativeTab(tab));
		item.method_7713(Item.BLOCK_ITEM_MAP, item);
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
		Registry.BLOCKS.stream().forEach(block -> block.getStateFactory().getStates().stream().filter(state -> Block.BLOCKSTATE_ID_LIST.getId(state) == -1).forEach(Block.BLOCKSTATE_ID_LIST::method_10205));
	}
}
