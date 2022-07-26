package net.eternaln.survivalclasiccore.utils;

public class CooldownOld {

    protected long time;

    public CooldownOld(long duration) {
        this.time = System.currentTimeMillis() + duration;
    }

    public void stop() { this.time = 0; }

    public boolean hasExpired() { return (System.currentTimeMillis() - this.time >= 1L); }

    public long getRemaining() { return this.time - System.currentTimeMillis(); }
}
