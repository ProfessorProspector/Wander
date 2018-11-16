package prospector.wander.block;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

public class BlockTraverseLeaves extends BlockLeaves {
	public Item sapling;

	public BlockTraverseLeaves(Item sapling) {
		super(Builder.create(Material.LEAVES));
	}
}
