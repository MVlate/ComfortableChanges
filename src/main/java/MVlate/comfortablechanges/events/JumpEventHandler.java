package MVlate.comfortablechanges.events;

import MVlate.comfortablechanges.ComfortableChanges;
import MVlate.comfortablechanges.client.ClientSprintKeyHandler;
import MVlate.comfortablechanges.server.SprintKeyTracker;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ComfortableChanges.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class JumpEventHandler {

    @SubscribeEvent
    public static void onPlayerJump(LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player) {

            if (player.hasEffect(MobEffects.JUMP)) {

                boolean isHoldingSprint;

                if (player.level().isClientSide()) {
                    isHoldingSprint = ClientSprintKeyHandler.isSprintKeyHeld();
                }
                else {
                    isHoldingSprint = SprintKeyTracker.isHeld(player.getUUID());
                }

                if (!isHoldingSprint) {
                    Vec3 currentMovement = player.getDeltaMovement();
                    player.setDeltaMovement(currentMovement.x, 0.42D, currentMovement.z);
                }
            }
        }
    }
}