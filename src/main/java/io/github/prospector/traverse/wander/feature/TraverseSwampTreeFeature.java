package io.github.prospector.traverse.wander.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.class_3747;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class TraverseSwampTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {

	public static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
	public static final BlockState OAK_LEAVES = Blocks.OAK_LEAVES.getDefaultState().with(Properties.PERSISTENT, false);
	public final boolean isWorldGen;
	private final int minTreeHeight;
	private BlockState stateWood;
	private BlockState stateLeaves;

	public TraverseSwampTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean isWorldGen) {
		this(function, isWorldGen, 5);
	}

	public TraverseSwampTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean isWorldGen, int minTreeHeight) {
		this(function, isWorldGen, minTreeHeight, OAK_LOG, OAK_LEAVES);
	}

	public TraverseSwampTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean isWorldGen, int minTreeHeight, BlockState stateWood, BlockState stateLeaves) {
		super(function, !isWorldGen);
		this.isWorldGen = isWorldGen;
		this.minTreeHeight = minTreeHeight;
		this.stateWood = stateWood;
		this.stateLeaves = stateLeaves;
	}

	@Override
	public boolean method_12775(Set<BlockPos> var1, class_3747 var2, Random var3, BlockPos var4) {
		int var5 = var3.nextInt(4) + minTreeHeight;
		var4 = var2.getTopPosition(Heightmap.Type.OCEAN_FLOOR, var4);
		boolean var6 = true;
		if (var4.getY() >= 1 && var4.getY() + var5 + 1 <= 256) {
			int var7;
			int var10;
			int var11;
			for (var7 = var4.getY(); var7 <= var4.getY() + 1 + var5; ++var7) {
				int var8 = 1;
				if (var7 == var4.getY()) {
					var8 = 0;
				}

				if (var7 >= var4.getY() + 1 + var5 - 2) {
					var8 = 3;
				}

				BlockPos.Mutable var9 = new BlockPos.Mutable();

				for (var10 = var4.getX() - var8; var10 <= var4.getX() + var8 && var6; ++var10) {
					for (var11 = var4.getZ() - var8; var11 <= var4.getZ() + var8 && var6; ++var11) {
						if (var7 >= 0 && var7 < 256) {
							var9.set(var10, var7, var11);
							if (!method_16420(var2, var9)) {
								if (method_16422(var2, var9)) {
									if (var7 > var4.getY()) {
										var6 = false;
									}
								} else {
									var6 = false;
								}
							}
						} else {
							var6 = false;
						}
					}
				}
			}

			if (!var6) {
				return false;
			} else if (method_16430(var2, var4.down()) && var4.getY() < 256 - var5 - 1) {
				this.method_16427(var2, var4.down());

				int var12;
				BlockPos var14;
				int var17;
				int var18;
				for (var7 = var4.getY() - 3 + var5; var7 <= var4.getY() + var5; ++var7) {
					var17 = var7 - (var4.getY() + var5);
					var18 = 2 - var17 / 2;

					for (var10 = var4.getX() - var18; var10 <= var4.getX() + var18; ++var10) {
						var11 = var10 - var4.getX();

						for (var12 = var4.getZ() - var18; var12 <= var4.getZ() + var18; ++var12) {
							int var13 = var12 - var4.getZ();
							if (Math.abs(var11) != var18 || Math.abs(var13) != var18 || var3.nextInt(2) != 0 && var17 != 0) {
								var14 = new BlockPos(var10, var7, var12);
								if (method_16420(var2, var14) || method_16425(var2, var14)) {
									this.method_13153(var2, var14, OAK_LEAVES);
								}
							}
						}
					}
				}

				for (var7 = 0; var7 < var5; ++var7) {
					BlockPos var19 = var4.up(var7);
					if (method_16420(var2, var19) || method_16422(var2, var19)) {
						this.method_12773(var1, var2, var19, OAK_LOG);
					}
				}

				for (var7 = var4.getY() - 3 + var5; var7 <= var4.getY() + var5; ++var7) {
					var17 = var7 - (var4.getY() + var5);
					var18 = 2 - var17 / 2;
					BlockPos.Mutable var20 = new BlockPos.Mutable();

					for (var11 = var4.getX() - var18; var11 <= var4.getX() + var18; ++var11) {
						for (var12 = var4.getZ() - var18; var12 <= var4.getZ() + var18; ++var12) {
							var20.set(var11, var7, var12);
							if (method_16416(var2, var20)) {
								BlockPos var21 = var20.west();
								var14 = var20.east();
								BlockPos var15 = var20.north();
								BlockPos var16 = var20.south();
								if (var3.nextInt(4) == 0 && method_16424(var2, var21)) {
									this.addVines(var2, var21, VineBlock.field_11702);
								}

								if (var3.nextInt(4) == 0 && method_16424(var2, var14)) {
									this.addVines(var2, var14, VineBlock.field_11696);
								}

								if (var3.nextInt(4) == 0 && method_16424(var2, var15)) {
									this.addVines(var2, var15, VineBlock.field_11699);
								}

								if (var3.nextInt(4) == 0 && method_16424(var2, var16)) {
									this.addVines(var2, var16, VineBlock.field_11706);
								}
							}
						}
					}
				}

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void addVines(class_3747 var1, BlockPos var2, BooleanProperty var3) {
		BlockState var4 = Blocks.VINE.getDefaultState().with(var3, true);
		this.method_13153(var1, var2, var4);
		int var5 = 4;

		for (var2 = var2.down(); method_16424(var1, var2) && var5 > 0; --var5) {
			this.method_13153(var1, var2, var4);
			var2 = var2.down();
		}

	}
}
