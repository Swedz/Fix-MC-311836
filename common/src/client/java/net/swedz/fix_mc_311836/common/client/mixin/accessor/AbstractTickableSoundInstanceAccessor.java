package net.swedz.fix_mc_311836.common.client.mixin.accessor;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractTickableSoundInstance.class)
public interface AbstractTickableSoundInstanceAccessor
{
	@Invoker("stop")
	void invokeStop();
}
