package MVlate.comfortablechanges.server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SprintKeyTracker {
    private static final Map<UUID, Boolean> playersHoldingSprint = new HashMap<>();

    public static void setHeld(UUID playerUUID, boolean isHeld) {
        playersHoldingSprint.put(playerUUID, isHeld);
    }

    public static boolean isHeld(UUID playerUUID) {
        return playersHoldingSprint.getOrDefault(playerUUID, false);
    }
}