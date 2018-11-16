package prospector.wander.feature;//package traverseteam.oml.traverse.feature;
//
//import net.minecraft.block.BlockLog;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.IBlockState;
//import net.minecraft.block.material.Material;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Facing;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.FeatureTreeSimple;
//
//import java.util.Random;
//
//public class FeatureFallenTree extends FeatureTreeSimple {
//	public static final IBlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
//	private final int minTreeLength;
//	private IBlockState stateWood;
//
//	public FeatureFallenTree(boolean isWorldGen) {
//		this(isWorldGen, 3, OAK_LOG);
//	}
//
//	public FeatureFallenTree(boolean isWorldGen, int minTreeLength, IBlockState stateWood) {
//		super(!isWorldGen);
//		this.minTreeLength = minTreeLength;
//		this.stateWood = stateWood;
//	}
//
//	public boolean generate(World world, Random rand, BlockPos pos) {
//		int num = rand.nextInt(5);
//		Facing orientation;
//		if (num == 0) {
//			orientation = Facing.EAST;
//			stateWood = stateWood.with(BlockLog.a, Facing.Axis.X);
//		} else if (num == 1) {
//			orientation = Facing.WEST;
//			stateWood = stateWood.with(BlockLog.a, Facing.Axis.X);
//		} else if (num == 1) {
//			orientation = Facing.SOUTH;
//			stateWood = stateWood.with(BlockLog.a, Facing.Axis.Z);
//		} else {
//			orientation = Facing.NORTH;
//			stateWood = stateWood.with(BlockLog.a, Facing.Axis.Z);
//		}
//		int i = rand.nextInt(2) + this.minTreeLength;
//		boolean flag = true;
//
//		if (pos.getY() >= 1 && pos.getY() + i + 1 <= world.getWorldHeight()) {
//			int v6;
//			int v7;
//			for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j) {
//				int k = 1;
//
//				if (j == pos.getY()) {
//					k = 0;
//				}
//
//				if (j >= pos.getY() + 1 + i - 2) {
//					k = 2;
//				}
//
//				BlockPos.Mutable mutablePos = new BlockPos.Mutable();
//
//				for(v6 = pos.getX() - k; v6 <= pos.getX() + k && flag; ++v6) {
//					for(v7 = pos.getZ() - k; v7 <= pos.getZ() + k && flag; ++v7) {
//						if (j >= 0 && j < 256) {
//							if (!this.a(world.getBlockState(mutablePos.set(v6, j, v7)).getBlock())) {
//								flag = false;
//							}
//						} else {
//							flag = false;
//						}
//					}
//				}
//			}
//
//			if (!flag) {
//				return false;
//			} else {
//				IBlockState state = world.getBlockState(pos.down());
//
//				if (Blocks.OAK_SAPLING.canBePlaced(state, world, pos) && pos.getY() < world.getWorldHeight() - i - 1) {
//
//					for (int j3 = 0; j3 < i; ++j3) {
//						BlockPos offsetPos = pos.offset(orientation, j3);
//						state = world.getBlockState(offsetPos);
//
//						if (state.getMaterial() == Material.AIR || state.getMaterial() == Material.LEAVES || state.getMaterial() == Material.PLANT) {
//							setBlockState(world, pos.offset(orientation, j3), this.stateWood);
//						}
//					}
//					return true;
//				} else {
//					return false;
//				}
//			}
//		} else {
//			return false;
//		}
//	}
//}
