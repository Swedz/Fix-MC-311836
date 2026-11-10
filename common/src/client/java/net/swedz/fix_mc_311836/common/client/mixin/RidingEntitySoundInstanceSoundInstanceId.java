package net.swedz.fix_mc_311836.common.client.mixin;

import net.minecraft.client.resources.sounds.RidingEntitySoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.swedz.fix_mc_311836.common.client.LocalPlayerRidingSound;
import net.swedz.fix_mc_311836.common.client.mixin.accessor.AbstractTickableSoundInstanceAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RidingEntitySoundInstance.class)
public class RidingEntitySoundInstanceSoundInstanceId
{
	@Shadow
	@Final
	private Player player;
	
	@Unique
	private int ridingSoundId = -1;
	
	@Inject(
			method = "<init>",
			at = @At("RETURN")
	)
	private void init(Player player, Entity entity, boolean underwaterSound, SoundEvent soundEvent, SoundSource soundSource, float volumeMin, float volumeMax, float volumeAmplifier, CallbackInfo callback)
	{
		ridingSoundId = ((LocalPlayerRidingSound) player).fix_mc_311836$getRidingSoundId();
	}
	
	@Inject(
			method = "tick",
			at = @At("HEAD"),
			cancellable = true
	)
	public void tick(CallbackInfo callback)
	{
		if(ridingSoundId >= 0 && ((LocalPlayerRidingSound) this.player).fix_mc_311836$getRidingSoundId() != ridingSoundId)
		{
			((AbstractTickableSoundInstanceAccessor) this).invokeStop();
			callback.cancel();
		}
	}
}
