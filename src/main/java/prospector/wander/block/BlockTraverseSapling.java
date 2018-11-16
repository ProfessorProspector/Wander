package prospector.wander.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.Material;
import net.minecraft.util.LazySingleton;
import prospector.wander.TreeGenerator;

public class BlockTraverseSapling extends BlockSapling {
	public BlockTraverseSapling(TreeGenerator treeGenerator) {
		super(treeGenerator, Block.Builder.create(Material.field_15935).method_9634());
	}
}
