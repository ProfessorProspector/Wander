package prospector.traverse.wander.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import prospector.silk.block.SilkLeavesBlock;
import prospector.silk.block.SilkSaplingBlock;
import prospector.silk.util.SilkSaplingGenerator;
import prospector.traverse.api.feature.TraverseTreeFeature;

public class AutumnalLSCompound {
	public static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
	public String name;
	public LSLeaves lsLeaves;
	public LSSapling lsSapling;

	public AutumnalLSCompound() {
		lsLeaves = new LSLeaves();
		lsSapling = new LSSapling();
	}

	public class LSLeaves extends SilkLeavesBlock {

		public LSLeaves() {
			super(null);
		}
	}

	public class LSSapling extends SilkSaplingBlock {
		public LSSapling() {
			super(new SilkSaplingGenerator(new TraverseTreeFeature(DefaultFeatureConfig::deserialize, false, OAK_LOG, lsLeaves.getDefaultState())));
		}
	}

}