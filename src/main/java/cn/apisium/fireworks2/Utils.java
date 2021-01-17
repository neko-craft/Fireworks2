package cn.apisium.fireworks2;

import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public final class Utils {
    protected final static Random r = new Random();
    public static Color randomColor() {
        return Color.fromBGR(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
    public static FireworkEffect.Type randomType() {
        switch (r.nextInt(5)) {
            case 0: return FireworkEffect.Type.BALL;
            case 1: return FireworkEffect.Type.BURST;
            case 2: return FireworkEffect.Type.STAR;
            case 3: return FireworkEffect.Type.CREEPER;
            default: return FireworkEffect.Type.BALL_LARGE;
        }
    }
    public static int randomPower() {
        return r.nextInt(2) + 1;
    }
    public static void genFirework(final Location l, final FireworkEffect effect) {
        genFirework(l, effect, Math.random() > 0.5 ? 2 : 1);
    }
    public static void randomFirework(final Location l) {
        genFirework(l.clone().add(r.nextInt(60) - 30, 10, r.nextInt(60) - 30), FireworkEffect
                .builder()
                .flicker(true)
                .withColor(randomColor())
                .withColor(randomColor())
                .withColor(randomColor())
                .withFade(randomColor())
                .withFade(randomColor())
                .withFade(randomColor())
                .with(randomType())
                .build());
    }
    public static void genFirework(final Location l, final FireworkEffect effect, int power) {
        Firework fw = l.getWorld().spawn(l, Firework.class);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.addEffect(effect);
        fwm.setPower(power);
        fw.setFireworkMeta(fwm);
    }
    public static Particle randomParticleType() {
        switch (r.nextInt(4)) {
            case 0: return Particle.TOTEM;
            case 1: return Particle.FLAME;
            case 2: return Particle.SOUL_FIRE_FLAME;
            default: return Particle.END_ROD;
        }
    }

    public static double randomN() {
        return (Math.random() > 0.5 ? 1 : -1) * 0.8;
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static Action[] parseSvg(final int width, final int height, final String path) {
        final Scanner cin = new Scanner(path.replaceAll("[MLHVCSQTAZmlhvcsqtaz]", " $0 ").replace(",", " ").replace("-", " -"));
        final ArrayList<Action> list = new ArrayList<>();
        while (cin.hasNext()) {
            boolean f = false;
            switch (cin.next()) {
                case "m": f = true;
                case "M":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.MoveTo, new double[] { cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "l": f = true;
                case "L":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.LineTo, new double[] { cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "h": f = true;
                case "H":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.HorizontalLineTo, new double[] { cin.nextDouble() / width }, f));
                    break;
                case "v": f = true;
                case "V":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.VerticalLineTo, new double[] { cin.nextDouble() / width }, f));
                    break;
                case "c": f = true;
                case "C":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.CurveTo, new double[] { cin.nextDouble() / width, cin.nextDouble() / height, cin.nextDouble() / width, cin.nextDouble() / height, cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "s": f = true;
                case "S":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.SmoothCurveTo, new double[] { cin.nextDouble() / width, cin.nextDouble() / height, cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "q": f = true;
                case "Q":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.QuadraticBelzierCurve, new double[] { cin.nextDouble() / width, cin.nextDouble() / height, cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "t": f = true;
                case "T":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.SmoothQuadraticBelzierCurveTo, new double[] { cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "a": f = true;
                case "A":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.EllipticalArc, new double[] { cin.nextDouble() / width, cin.nextDouble() / height, cin.nextDouble(), cin.nextDouble(), cin.nextDouble(), cin.nextDouble() / width, cin.nextDouble() / height }, f));
                    break;
                case "z":
                case "Z":
                    while (cin.hasNextDouble()) list.add(new Action(Action.ActionType.ClosePath, null, false));
            }
        }
        final Action[] actions = new Action[list.size()];
        return list.toArray(actions);
    }

    public static void drawSvg(final int width, final int height, final double step, final int count, double offset, double offsetY, final Particle particle, final Action[] actions, final Location initLoc, final long sleep) throws Exception {
        final World w = initLoc.getWorld();
        final double ix = initLoc.getX(), iy = initLoc.getY(), iz = initLoc.getZ(), centerX = ix + width / 2.0, centerZ = iz + height / 2.0;
        double x = ix, z = iz, ix2 = x, iz2 = z;
        for (final Action it : actions) {
            switch (it.type) {
                case MoveTo:
                    ix2 = x = (it.isRelative ? x : ix) + it.args[0] * width;
                    iz2 = z = (it.isRelative ? z : iz) + it.args[1] * height;
                    break;
                case HorizontalLineTo: {
                    double tx = (it.isRelative ? x : ix) + it.args[0] * width, dx = tx - x, oz = offset * (z - centerZ);
                    for (double t = 0; t <= 1; t += step) {
                        double cx = dx * t + x;
                        w.spawnParticle(particle, cx, iy, z, count, offset * (cx - centerX), offsetY, oz, 0.001, null, true);
                    }
                    x = tx;
                    break;
                }
                case VerticalLineTo: {
                    double tz = (it.isRelative ? z : iz) + it.args[0] * height, dz = tz - z, ox = offset * (x - centerX);
                    for (double t = 0; t <= 1; t += step) {
                        double cz = dz * t + z;
                        w.spawnParticle(particle, x, iy, cz, count, ox, offsetY, offset * (cz - centerZ), 0.001, null, true);
                    }
                    z = tz;
                    break;
                }
                case LineTo: {
                    double tx = (it.isRelative ? x : ix) + it.args[0] * width, tz = (it.isRelative ? z : iz) + it.args[1] * height, dx = tx - x, dz = tz - z;
                    for (double t = 0; t <= 1; t += step) {
                        double cx = dx * t + x, cz = dz * t + z;
                        w.spawnParticle(particle, cx, iy, cz, count, offset * (cx - centerX), offsetY, offset * (cz - centerZ), 0.001, null, true);
                    }
                    x = tx;
                    z = tz;
                    break;
                }
                case SmoothCurveTo: {
                    double x0 = it.isRelative ? x : ix, z0 = it.isRelative ? z : iz,
                            x1 = x0 + it.args[0] * width, z1 = z0 + it.args[1] * height,
                            x2 = x0 + it.args[2] * width, z2 = z0 + it.args[3] * height;
                    for (double t = 0; t <= 1; t += step) {
                        double temp = 1 - t,
                                cx = x * temp * temp + 2 * x1 * t * temp + 2 * x2 * t * t,
                                cz = z * temp * temp + 2 * z1 * t * temp + 2 * z2 * t * t;
                        w.spawnParticle(particle, cx, iy, cz, count, offset * (cx - centerX), offsetY, offset * (cz - centerZ), 0.001, null, true);
                    }
                    if (it.isRelative) {
                        x = x2;
                        z = z2;
                    }
                    break;
                }
                case CurveTo: {
                    double x0 = it.isRelative ? x : ix, z0 = it.isRelative ? z : iz,
                            x1 = x0 + it.args[0] * width, z1 = z0 + it.args[1] * height,
                            x2 = x0 + it.args[2] * width, z2 = z0 + it.args[3] * height,
                            x3 = x0 + it.args[4] * width, z3 = z0 + it.args[5] * height;
                    for (double t = 0; t <= 1; t += step) {
                        double temp = 1 - t,
                                cx = x * temp * temp * temp + 3 * x1 * t * temp * temp + 3 * x2 * t * t * temp + x3 * t * t * t,
                                cz = z * temp * temp * temp + 3 * z1 * t * temp * temp + 3 * z2 * t * t * temp + z3 * t * t * t;
                        w.spawnParticle(particle, cx, iy, cz, count, offset * (cx - centerX), offsetY, offset * (cz - centerZ), 0.001, null, true);
                    }
                    if (it.isRelative) {
                        x = x3;
                        z = z3;
                    }
                    break;
                }
                case ClosePath: {
                    double dx = ix2 - x, dz = iz2 - z;
                    for (double t = 0; t <= 1; t += step) {
                        double cx = dx * t + x, cz = dz * t + z;
                        w.spawnParticle(particle, cx, iy, cz, count, offset * (cx - centerX), offsetY, offset * (cz - centerZ), 0.001, null, true);
                    }
                    x = ix2;
                    z = iz2;
                    break;
                }
                default:
                    System.out.println(it.type.name());
            }
            if (sleep > 0) Thread.sleep(sleep);
        }
    }
}
