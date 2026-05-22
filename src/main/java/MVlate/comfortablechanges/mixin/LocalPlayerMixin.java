package MVlate.comfortablechanges.mixin;

import MVlate.comfortablechanges.config.ClientConfig;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    // inject before change to fly mode
    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;onUpdateAbilities()V"))
    private void comfortablechanges$interceptCreativeFlight(CallbackInfo ci) {

        if (ClientConfig.DISABLE_VANILLA_FLIGHT.get()) {
            Player player = (Player) (Object) this;

            // cancel fly
            player.getAbilities().flying = !player.getAbilities().flying;
        }
    }
}