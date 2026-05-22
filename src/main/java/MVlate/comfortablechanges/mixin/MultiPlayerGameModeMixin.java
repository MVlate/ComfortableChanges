package MVlate.comfortablechanges.mixin;

import MVlate.comfortablechanges.client.KeyBindingInit;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    // keys activated?
    @Unique
    private boolean comfortablechanges$isOffhandKeyActive() {
        return KeyBindingInit.OFFHAND_USE_KEY.isDown() || KeyBindingInit.OFFHAND_MODIFIER_KEY.isDown();
    }

    // object to air
    @Inject(method = "useItem", at = @At("HEAD"), cancellable = true)
    private void comfortablechanges$skipMainHandItem(net.minecraft.world.entity.player.Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (hand == InteractionHand.MAIN_HAND && comfortablechanges$isOffhandKeyActive()) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    // object to block
    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void comfortablechanges$skipMainHandBlock(net.minecraft.client.player.LocalPlayer player, InteractionHand hand, net.minecraft.world.phys.BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (hand == InteractionHand.MAIN_HAND && comfortablechanges$isOffhandKeyActive()) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    // object to entity
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void comfortablechanges$skipMainHandEntity(net.minecraft.world.entity.player.Player player, net.minecraft.world.entity.Entity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {        if (hand == InteractionHand.MAIN_HAND && comfortablechanges$isOffhandKeyActive()) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}