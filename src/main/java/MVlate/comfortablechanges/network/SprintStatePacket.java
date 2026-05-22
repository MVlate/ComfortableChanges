package MVlate.comfortablechanges.network;

import MVlate.comfortablechanges.server.SprintKeyTracker;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SprintStatePacket {
    private final boolean isHeld;

    public SprintStatePacket(boolean isHeld) {
        this.isHeld = isHeld;
    }

    public SprintStatePacket(FriendlyByteBuf buffer) {
        this.isHeld = buffer.readBoolean();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isHeld);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                SprintKeyTracker.setHeld(player.getUUID(), this.isHeld);
            }
        });
        context.setPacketHandled(true);
    }
}