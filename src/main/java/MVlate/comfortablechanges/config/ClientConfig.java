package MVlate.comfortablechanges.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue DISABLE_VANILLA_FLIGHT;
    public static final ForgeConfigSpec.BooleanValue DISABLE_VANILLA_ELYTRA;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Controles Vanilla"); // Category

        DISABLE_VANILLA_FLIGHT = builder
                .comment("If true, disable double jump (space) to fly in creative mode.")
                .define("disableVanillaFlight", true);

        DISABLE_VANILLA_ELYTRA = builder
                .comment("If true, disable pressing airspace to open the Elytras.")
                .define("disableVanillaElytra", true);

        builder.pop();

        SPEC = builder.build();
    }
}