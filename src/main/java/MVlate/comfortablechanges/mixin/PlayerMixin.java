package MVlate.comfortablechanges.mixin;
import MVlate.comfortablechanges.config.ClientConfig;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "tryToStartFallFlying", at = @At("HEAD"), cancellable = true)
    private void comfortablechanges$interceptElytra(CallbackInfoReturnable<Boolean> cir) {

        Player player = (Player) (Object) this;

        if (player.level().isClientSide()) {
            if (ClientConfig.DISABLE_VANILLA_ELYTRA.get()) {

                // cancel the vanilla activation
                cir.setReturnValue(false);
            }
        }
    }
}