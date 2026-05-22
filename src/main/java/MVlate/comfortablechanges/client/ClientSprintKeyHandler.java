package MVlate.comfortablechanges.client;

import MVlate.comfortablechanges.ComfortableChanges;
import MVlate.comfortablechanges.network.PacketHandler;
import MVlate.comfortablechanges.network.SprintStatePacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ComfortableChanges.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientSprintKeyHandler {

    private static boolean wasSprintKeyDown = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        boolean isSprintKeyDown = mc.options.keySprint.isDown();

        if (isSprintKeyDown != wasSprintKeyDown) {
            wasSprintKeyDown = isSprintKeyDown;
            PacketHandler.INSTANCE.sendToServer(new SprintStatePacket(isSprintKeyDown));
        }
    }

    public static boolean isSprintKeyHeld() {
        return Minecraft.getInstance().options.keySprint.isDown();
    }
}