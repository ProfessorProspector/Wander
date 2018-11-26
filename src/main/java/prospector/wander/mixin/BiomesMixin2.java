package prospector.wander.mixin;

import net.minecraft.class_1966;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import prospector.wander.Wander;

import java.util.ArrayList;
import java.util.List;

@Mixin(class_1966.class)
public class BiomesMixin2 {

	@Shadow
	@Final
	@Mutable
	private static List<Biome> field_9391;

	private static List<Biome> updatedList = null;

	public List<Biome> method_8759() {
		if (updatedList == null) {
			updatedList = new ArrayList<>();
			updatedList.addAll(field_9391);
			updatedList.add(Wander.AUTUMNAL_WOODS);
			updatedList.add(Wander.MEADOW);
			updatedList.add(Wander.MINI_JUNGLE);
			updatedList.add(Wander.WOODLANDS);
		}
		field_9391 = updatedList;
		return updatedList;
	}

}
