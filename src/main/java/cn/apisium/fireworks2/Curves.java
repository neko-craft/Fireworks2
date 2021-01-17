package cn.apisium.fireworks2;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public final class Curves { // https://www.cnblogs.com/WhyEngine/p/3841071.html
    public static void cylindricSineWave(final Location loc) {
        final double ix = loc.getX(), iy = loc.getY(), iz = loc.getZ();
        final World world = loc.getWorld();
        final double END = 20 * Math.PI, a = 25, b = 6, n = 8;
        try {
            for (double t = 0; t < END; t += 0.02) {
                for (double phase = 0; phase < Math.PI; phase += 0.9) {
                    final double t1 = t + phase;
                    world.spawnParticle(Particle.END_ROD, ix + a * Math.cos(t1), iy + b * Math.cos(n * t), iz + a * Math.sin(t1), 0, 0, 0, 0, 1.0, null, true);
                }
                Thread.sleep(6);
            }
        } catch (final Exception ignored) { }
    }
    public static void sphericalHelix(final Location loc) {
        final double ix = loc.getX(), iy = loc.getY(), iz = loc.getZ();
        final World world = loc.getWorld();
        final double END = 10 * Math.PI, k = Math.random() * 0.7 + 0.3, r = 7 + Math.random() * 10;
        double it = 0;
        final Particle type = Utils.randomParticleType();
        while (Utils.r.nextInt(7) != 0) {
            it += Math.random() * 0.5 + 0.5;
            for (double t0 = 0; t0 < END; t0 += 0.04) {
                final double t = t0 + it, s = Math.sin(t), c = Math.cos(t), a = Math.sin(k * t), b = Math.cos(k * t);
                world.spawnParticle(type, ix + r * (k * c * b - s * a), iy + r * Math.sqrt(1 - k * k) * b, iz + r * (k * s * b + c * a), 0, 0, 0, 0, 1.0, null, true);
            }
        }
    }
    public static void sphericalSinusoid(final Location loc) {
        final double ix = loc.getX(), iy = loc.getY(), iz = loc.getZ();
        final World world = loc.getWorld();
        final double END = 10 * Math.PI, n = Math.random() * 7 + 3, k = Math.random() * 0.7 + 0.3, a = 7 + Math.random() * 10;
        double it = 0;
        final Particle type = Utils.randomParticleType();
        while (Utils.r.nextInt(7) != 0) {
            it += Math.random() * 0.5 + 0.5;
            for (double t0 = 0; t0 < END; t0 += 0.04) {
                final double t = t0 + it, w = a / Math.sqrt(1 + Math.pow(k * Math.cos(n * t), 2));
                world.spawnParticle(type, ix + Math.cos(t) * w, iy + k * Math.cos(n * t) * w, iz + Math.sin(t) * w, 0, 0, 0, 0, 1.0, null, true);
            }
        }
    }
}
