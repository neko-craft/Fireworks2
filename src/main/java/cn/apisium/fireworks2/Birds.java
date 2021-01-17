package cn.apisium.fireworks2;

import com.destroystokyo.paper.entity.Pathfinder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Parrot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public final class Birds {
    protected final static String NAME = "Tool Bird";
    private final Parrot[] birds = new Parrot[100];
    private final Location[] locations = new Location[100];
    private static Method getHandle, getTasks;
    private static Field goalSelector;

    public Birds(final Location loc) {
        final World world = loc.getWorld();
        for (int i = 0; i < birds.length; i++) {
            locations[i] = loc.clone().add(Math.random() * 100 - 50, 100 + Math.random() * 20, Math.random() * 100 - 50);
            birds[i] = world.spawn(loc.clone().add(Math.random() * 20 - 10, 0, Math.random() * 20 - 10), Parrot.class, it -> {
                try {
                    Object handle = (getHandle == null ? getHandle = it.getClass().getMethod("getHandle") : getHandle).invoke(it);
                    Object selector = (goalSelector == null ? goalSelector = handle.getClass().getField("goalSelector") : goalSelector).get(handle);
                    ((Set<?>) (getTasks == null ? getTasks = selector.getClass().getMethod("getTasks") : getTasks).invoke(selector)).clear();
                } catch (final Exception ignored) { }
                it.setSilent(true);
                it.setCustomName(NAME);
                it.setCustomNameVisible(false);
                it.setInvisible(true);
                it.setAI(false);
                it.setGravity(false);
            });
        }
    }

    public void prepareToFly() {
        for (final Parrot it : birds) {
            it.setInvisible(false);
            it.setGlowing(true);
            it.setAI(true);
        }
    }

    public void fly() {
        for (int i = 0; i < birds.length; i++) {
            final Pathfinder pf = birds[i].getPathfinder();
            pf.stopPathfinding();
            pf.moveTo(locations[i]);
        }
    }

    public static void clean(final World world) {
        world.getEntitiesByClass(Parrot.class).forEach(it -> {
            if (NAME.equals(it.getCustomName())) it.remove();
        });
    }
}
