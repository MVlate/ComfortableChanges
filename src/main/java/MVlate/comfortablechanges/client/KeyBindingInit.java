package MVlate.comfortablechanges.client;
import com.mojang.blaze3d.platform.InputConstants;
import MVlate.comfortablechanges.ComfortableChanges;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ComfortableChanges.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindingInit {
    public static final String CATEGORY = "key.category.comfortablechanges";
    public static final KeyMapping FLIGHT_KEY = new KeyMapping(
            "key.comfortablechanges.flight",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            CATEGORY
    );
    public static final KeyMapping ELYTRA_KEY = new KeyMapping(
            "key.comfortablechanges.elytra",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            CATEGORY
    );

    public static final KeyMapping OFFHAND_USE_KEY = new KeyMapping(
            "key.comfortablechanges.offhand_use",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            CATEGORY
    );

    public static final KeyMapping OFFHAND_MODIFIER_KEY = new KeyMapping(
            "key.comfortablechanges.offhand_modifier",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            CATEGORY
    );

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(FLIGHT_KEY);
        event.register(ELYTRA_KEY);
        event.register(OFFHAND_USE_KEY);
        event.register(OFFHAND_MODIFIER_KEY);
    }
}