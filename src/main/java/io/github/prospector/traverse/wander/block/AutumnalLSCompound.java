package io.github.prospector.traverse.wander.block;

import io.github.prospector.silk.block.SilkLeavesBlock;
import io.github.prospector.silk.block.SilkSaplingBlock;
import io.github.prospector.silk.util.SilkSaplingGenerator;
import io.github.prospector.traverse.api.feature.TraverseTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

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