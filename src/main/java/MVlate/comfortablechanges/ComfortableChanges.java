package MVlate.comfortablechanges;

import MVlate.comfortablechanges.network.PacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ComfortableChanges.MODID)
public class ComfortableChanges {

    public static final String MODID = "comfortablechanges";

    public ComfortableChanges() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, MVlate.comfortablechanges.config.ClientConfig.SPEC);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(PacketHandler::register);
    }
}