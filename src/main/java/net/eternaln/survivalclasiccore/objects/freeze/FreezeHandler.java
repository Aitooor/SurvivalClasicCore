package net.eternaln.survivalclasiccore.objects.freeze;

public class FreezeHandler {

    public static void disable() {
        for (Freeze freeze : Freeze.getFreezes().values()) {
            freeze.unFreezePlayer(false);
        }
    }
}
