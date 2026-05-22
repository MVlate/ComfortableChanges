package MVlate.comfortablechanges.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleFlightPacket {

    public ToggleFlightPacket() {
    }

    public ToggleFlightPacket(FriendlyByteBuf buffer) {}

    public void encode(FriendlyByteBuf buffer) {}

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {

                if (player.getAbilities().mayfly) {

                    boolean willFly = !player.getAbilities().flying;
                    player.getAbilities().flying = willFly;

                    if (willFly && player.onGround()) {
                        Vec3 currentMovement = player.getDeltaMovement();
                        player.setDeltaMovement(currentMovement.x, 0.02D, currentMovement.z);
                        player.setOnGround(false);
                        player.hurtMarked = true;
                    }

                    player.onUpdateAbilities();
                }
            }
        });
        context.setPacketHandled(true);
    }
}