package prospector.wander.mixin;

import net.minecraft.class_2084;
import net.minecraft.class_2088;
import net.minecraft.world.biome.Biome;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prospector.wander.Wander;

@Mixin(class_2088.class)
public class BiomesMixin {

	@Shadow
	@Final
	@Mutable
	private Biome[] field_9677;

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/class_2084;)V")
	protected void constructor(class_2084 var1, CallbackInfo info) {
		field_9677 = ArrayUtils.addAll(field_9677, Wander.AUTUMNAL_WOODS, Wander.MEADOW, Wander.MINI_JUNGLE, Wander.WOODLANDS);
	}
}
