package MVlate.comfortablechanges.network;

import MVlate.comfortablechanges.ComfortableChanges;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ComfortableChanges.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        INSTANCE.registerMessage(packetId++,
                SprintStatePacket.class,
                SprintStatePacket::encode,
                SprintStatePacket::new,
                SprintStatePacket::handle
        );
        INSTANCE.registerMessage(packetId++,
                ToggleFlightPacket.class,
                ToggleFlightPacket::encode,
                ToggleFlightPacket::new,
                ToggleFlightPacket::handle
        );
        INSTANCE.registerMessage(packetId++,
                ToggleElytraPacket.class,
                ToggleElytraPacket::encode,
                ToggleElytraPacket::new,
                ToggleElytraPacket::handle
        );
    }

}
