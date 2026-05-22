package MVlate.comfortablechanges.client;
import MVlate.comfortablechanges.ComfortableChanges;
import MVlate.comfortablechanges.network.PacketHandler;
import MVlate.comfortablechanges.network.ToggleElytraPacket;
import MVlate.comfortablechanges.network.ToggleFlightPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ComfortableChanges.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        while (KeyBindingInit.FLIGHT_KEY.consumeClick()) {
            PacketHandler.INSTANCE.sendToServer(new ToggleFlightPacket());
        }
        while (KeyBindingInit.ELYTRA_KEY.consumeClick()) {

            if (mc.player.onGround()) {
                Vec3 currentMovement = mc.player.getDeltaMovement();
                mc.player.setDeltaMovement(currentMovement.x, 0.1D, currentMovement.z);
            }

            PacketHandler.INSTANCE.sendToServer(new ToggleElytraPacket());
        }
    }
}