package net.swedz.fix_mc_311836.common.client.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.swedz.fix_mc_311836.common.client.LocalPlayerRidingSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerRidingSoundIdHolder implements LocalPlayerRidingSound
{
	@Unique
	private int ridingSoundId;
	
	@Override
	public int fix_mc_311836$getRidingSoundId()
	{
		return ridingSoundId;
	}
	
	@Inject(
			method = "startRiding",
			at = @At("HEAD")
	)
	public void createSoundInstance(Entity entity, boolean force, boolean sendEventAndTriggers, CallbackInfoReturnable<Boolean> cir)
	{
		ridingSoundId++;
	}
}
