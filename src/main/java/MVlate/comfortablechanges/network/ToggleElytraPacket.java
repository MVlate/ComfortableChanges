package MVlate.comfortablechanges.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleElytraPacket {

    public ToggleElytraPacket() {}
    public ToggleElytraPacket(FriendlyByteBuf buffer) {}
    public void encode(FriendlyByteBuf buffer) {}

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {

                ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);

                if (chestplate.canElytraFly(player)) {

                    if (!player.isFallFlying()) {

                        if (player.onGround()) {
                            Vec3 currentMovement = player.getDeltaMovement();
                            player.setDeltaMovement(currentMovement.x, 0.2D, currentMovement.z);
                            player.hurtMarked = true;
                            player.setOnGround(false);
                        }

                        player.startFallFlying();

                    } else {
                        player.stopFallFlying();
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
}