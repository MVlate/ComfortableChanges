package MVlate.comfortablechanges.mixin;

import MVlate.comfortablechanges.client.KeyBindingInit;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {

    @Inject(method = "isDown", at = @At("HEAD"), cancellable = true)
    private void comfortablechanges$isDown(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = Minecraft.getInstance();
        if (mc != null && mc.options != null && (Object) this == mc.options.keyUse) {
            if (KeyBindingInit.OFFHAND_USE_KEY.isDown()) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "consumeClick", at = @At("HEAD"), cancellable = true)
    private void comfortablechanges$consumeClick(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = Minecraft.getInstance();
        if (mc != null && mc.options != null && (Object) this == mc.options.keyUse) {
            if (KeyBindingInit.OFFHAND_USE_KEY.consumeClick()) {
                cir.setReturnValue(true);
            }
        }
    }
}