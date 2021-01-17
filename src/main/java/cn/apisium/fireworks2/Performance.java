package cn.apisium.fireworks2;

import org.bukkit.*;
import org.bukkit.map.MapFont;
import org.bukkit.map.MinecraftFont;

import java.util.Timer;
import java.util.TimerTask;

import static cn.apisium.fireworks2.Utils.randomColor;
import static cn.apisium.fireworks2.Utils.randomPower;

public final class Performance extends TimerTask {
    private final Main plugin;
    private int tick = 0;
    private Location center;
    private final double END = Math.PI * 2;
    private Birds birds;
    protected final Timer timer = new Timer();

    private static final FireworkEffect fw1 = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Color.AQUA)
            .withFade(Color.YELLOW)
            .with(FireworkEffect.Type.BALL)
            .trail(true)
            .build();
    private static final FireworkEffect fw1Bigger = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Color.AQUA)
            .withFade(Color.YELLOW)
            .with(FireworkEffect.Type.BALL_LARGE)
            .trail(true)
            .build();
    private static final FireworkEffect fw2 = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withFade(Color.AQUA)
            .withFade(Color.AQUA)
            .with(FireworkEffect.Type.STAR)
            .trail(true)
            .build();
    private static final FireworkEffect fw3 = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Color.AQUA)
            .withFade(Color.BLUE)
            .with(FireworkEffect.Type.BURST)
            .trail(false)
            .build();
    private static final FireworkEffect fw4 = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Color.AQUA)
            .withFade(Color.BLUE)
            .withColor(Color.AQUA)
            .withFade(Color.BLUE)
            .with(FireworkEffect.Type.BURST)
            .trail(false)
            .build();
    private static final FireworkEffect fw5 = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withFade(Color.AQUA)
            .withFade(Color.AQUA)
            .with(FireworkEffect.Type.BURST)
            .trail(false)
            .build();
    private static final FireworkEffect fw2Bigger = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withFade(Color.AQUA)
            .withFade(Color.AQUA)
            .with(FireworkEffect.Type.BALL_LARGE)
            .trail(true)
            .build();
    private static final FireworkEffect fw2Ball = FireworkEffect
            .builder()
            .flicker(true)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withColor(Constants.PINK)
            .withFade(Color.AQUA)
            .withFade(Color.AQUA)
            .with(FireworkEffect.Type.BALL)
            .trail(true)
            .build();

    public Performance(final Main plugin) {
        this.plugin = plugin;
    }

    public void sync(final Runnable fn) {
        sync(fn, 1);
    }
    public void async(final Runnable fn) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, fn);
    }
    public void sync(final Runnable fn, final int times) {
        Bukkit.getScheduler().runTask(plugin, times == 1 ? fn : () -> {
            for (int j = 0; j < times; j++) fn.run();
        });
    }

    @Override
    public void run() {
        switch (tick++) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 19:
            case 21:
                sync(() -> {
                    double x = 25 * (tick / 2 % 2 == 0 ? -1 : 1), z = 50 - (tick - 9) * 5;
                    Utils.genFirework(center.clone().add(x, 0, z), fw1, 1);
                    Utils.genFirework(center.clone().add(x, 0, z), fw1, 2);
                    Utils.genFirework(center.clone().add(x, 0, z), fw1, 3);
                });
                break;
            case 24:
            case 27:
            case 29:
            case 31:
            case 33:
                sync(() -> {
                    double z = (tick - 25) * 5 - 50;
                    Utils.genFirework(center.clone().add(25, 0, z), fw1Bigger, 1);
                    Utils.genFirework(center.clone().add(25, 0, z), fw1Bigger, 2);
                    Utils.genFirework(center.clone().add(25, 0, z), fw1Bigger, 3);
                    Utils.genFirework(center.clone().add(-25, 0, z), fw1Bigger, 1);
                    Utils.genFirework(center.clone().add(-25, 0, z), fw1Bigger, 2);
                    Utils.genFirework(center.clone().add(-25, 0, z), fw1Bigger, 3);
                });
                break;
            case 37:
            case 40:
            case 43:
            case 46:
            case 48:
            case 50:
            case 53:
            case 56:
                sync(() -> {
                    double z = 50 - (tick - 36) * 4;
                    Utils.genFirework(center.clone().add(35, 0, z), fw2, 1);
                    Utils.genFirework(center.clone().add(35, 0, z), fw2, 1);
                    Utils.genFirework(center.clone().add(35, 0, z), fw2, 2);
                    Utils.genFirework(center.clone().add(35, 0, z), fw2, 2);
                    Utils.genFirework(center.clone().add(35, 0, z), fw2, 3);
                    Utils.genFirework(center.clone().add(-35, 0, z), fw2, 1);
                    Utils.genFirework(center.clone().add(-35, 0, z), fw2, 1);
                    Utils.genFirework(center.clone().add(-35, 0, z), fw2, 2);
                    Utils.genFirework(center.clone().add(-35, 0, z), fw2, 2);
                    Utils.genFirework(center.clone().add(-35, 0, z), fw2, 3);
                });
                break;
            case 61:
            case 64:
                sync(() -> Utils.randomFirework(center), 60);
                break;
            case 70:
                async(() -> {
                    try {
                        for (double i = 0; i < END; i += Math.PI * 2 / 150) {
                            double x = Math.cos(i) * 50, z = Math.sin(i) * 50, x1 = Math.cos(END - i) * 50, z1 = Math.sin(END - i) * 50;
                            sync(() -> {
                                Utils.genFirework(center.clone().add(x, 5.0, z), FireworkEffect
                                        .builder()
                                        .flicker(true)
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withFade(randomColor())
                                        .withFade(randomColor())
                                        .withFade(randomColor())
                                        .with(FireworkEffect.Type.BURST)
                                        .build(), Utils.randomPower());
                                Utils.genFirework(center.clone().add(x1, 5.0, z1), FireworkEffect
                                        .builder()
                                        .flicker(true)
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withColor(randomColor())
                                        .withFade(randomColor())
                                        .withFade(randomColor())
                                        .withFade(randomColor())
                                        .with(FireworkEffect.Type.BURST)
                                        .build(), Utils.randomPower());
                            }, 5);
                            Thread.sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 76:
            case 80:
                sync(() -> Utils.randomFirework(center), 120);
                break;
            case 83:
                rain(Particle.DRIPPING_OBSIDIAN_TEAR);
                break;
            case 85:
                async(() -> {
                    final World world = center.getWorld();
                    double x = center.getX(), y = center.getY(), z = center.getZ();
                    try {
                        for (int i = 0; i < 120; i++) {
                            Thread.sleep(75);
                            y++;
                            world.spawnParticle(Particle.FIREWORKS_SPARK, x, y, z, 25, 0.0, 0.0, 0.0, 1.0, null, true);
                        }
                    } catch (InterruptedException ignored) { }
                });
                break;
            case 95:
                rain(Particle.FIREWORKS_SPARK);
                break;
            case 97:
                async (() -> {
                    final Location loc = center.clone().add(-64, 30, -64);
                    try {
                        Utils.drawSvg(128, 128, 0.03, 0, 0, -5, Particle.END_ROD, Constants.BORDER1, loc, 5);
                        Thread.sleep(2000);
                        Utils.drawSvg(128, 128, 0.03, 0, 5, 10, Particle.TOTEM, Constants.BORDER1, loc, 0);
                        for (final Action[] it : Constants.BORDER) {
                            Utils.drawSvg(128, 128, 0.03, 0, 5, 5, Particle.TOTEM, it, loc, 0);
                        }
                        for (final Action[] it : Constants.CENTER) {
                            Utils.drawSvg(128, 128, 0.04, 0, 5, -10, Particle.TOTEM, it, loc, 0);
                        }
                    } catch (Exception ignored) {  }
                });
                break;
            case 100:
                sync(birds::prepareToFly);
            case 104:
            case 107:
            case 112:
            case 117:
            case 122:
                sync(birds::fly);
                break;
            case 105:
                async(() -> {
                    try {
                        for (double i = 0; i < END; i += Math.PI * 2 / 150) {
                            double x = Math.cos(i) * 50, z = Math.sin(i) * 50, x1 = Math.cos(END - i) * 50, z1 = Math.sin(END - i) * 50;
                            sync(() -> {
                                Utils.genFirework(center.clone().add(x, 5.0, z), fw3, 2);
                                Utils.genFirework(center.clone().add(x1, 5.0, z1), fw3, 2);
                            });
                            Thread.sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 114:
                async(() -> {
                    try {
                        for (double i = 0; i < END; i += Math.PI * 2 / 150) {
                            double x = Math.cos(i) * 60, z = Math.sin(i) * 60, x1 = Math.cos(END - i) * 60, z1 = Math.sin(END - i) * 60;
                            sync(() -> {
                                Utils.genFirework(center.clone().add(x, 5.0, z), fw4, 3);
                                Utils.genFirework(center.clone().add(x1, 5.0, z1), fw4, 3);
                            }, 2);
                            Thread.sleep(60);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 130:
                sync(() -> Birds.clean(center.getWorld()));
                break;
            case 128:
            case 135:
            case 140:
                // noinspection DuplicateBranchesInSwitch
                sync(() -> Utils.randomFirework(center), 120);
                break;
            case 146:
                async(() -> Curves.cylindricSineWave(center.clone().add(0, 10, 0)));
                drawText("NekoCraft");
                break;
            case 150:
                drawText("is Wonderful");
                break;
            case 154:
                drawText("Because");
                break;
            case 159:
                drawText("of You !");
                break;
            case 164:
                // noinspection DuplicateBranchesInSwitch
                rain(Particle.FIREWORKS_SPARK);
                break;
            case 168:
                async(() -> {
                    try {
                        for (double i = 0; i < 188; i++) {
                            genHeart(center.clone().add(Math.random() * 120 - 60.0, 20.0, Math.random() * 120 - 60.0));
                            genHeart(center.clone().add(Math.random() * 120 - 60.0, 20.0, Math.random() * 120 - 60.0));
                            Thread.sleep(109);
                        }
                        for (double i = 0; i < END * 2; i += Math.PI * 2 / 130) {
                            double x = Math.cos(i) * 50, z = Math.sin(i) * 50, x1 = Math.cos(END - i) * 50, z1 = Math.sin(END - i) * 50;
                            sync(() -> {
                                Utils.genFirework(center.clone().add(x, 5.0, z), fw5, randomPower());
                                Utils.genFirework(center.clone().add(x1, 5.0, z1), fw5, randomPower());
                            });
                            Thread.sleep(40);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 194:
                async(() -> {
                    try {
                        final Location loc = center.clone().add(0, 20, 0);
                        for (int j = 0; j < 64; j++) {
                            sync(() -> {
                                for (double i = 0; i < END; i += Math.PI * 2 / 14) {
                                    double x = Math.cos(i) * 40, z = Math.sin(i) * 40;
                                    Utils.genFirework(center.clone().add(x, 0.0, z), fw1, randomPower());
                                }
                                Utils.genFirework(loc, fw1Bigger, Utils.r.nextInt(3));
                            });
                            Thread.sleep(435);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 222:
                async(() -> {
                    try {
                        for (int j = 0; j < 64; j++) {
                            sync(() -> {
                                for (double i = 0; i < END; i += Math.PI * 2 / 12) {
                                    double x = Math.cos(i) * 40, z = Math.sin(i) * 40;
                                    Utils.randomFirework(center);
                                    Utils.genFirework(center.clone().add(x, 0.0, z), Utils.r.nextInt(6) == 0 ? fw2Bigger : fw2Ball, randomPower());
                                }
                            });
                            Thread.sleep(435);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 252:
                async(() -> {
                    final World world = center.getWorld();
                    final double ix = center.getX(), iy = center.getY(), iz = center.getZ();
                    try {
                        for (double t = 0; t < 15; t += 0.003) {
                            world.spawnParticle(Particle.CLOUD, ix + t * Math.cos(10 + t * 1800) * 5, iy + t * 5, iz + t * Math.sin(10 + t * 1800) * 5, 0, 0.0, 0.0, 0.0, 1.0, null, true);
                            Thread.sleep(2);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 254:
                rain(Particle.DRIPPING_OBSIDIAN_TEAR);
                async(() -> {
                    try {
                        for (int j = 0; j < 110; j++) {
                            sync(() -> {
                                for (double i = 0; i < 20; i++) {
                                    Utils.genFirework(center.clone().add(Math.random() * 60 - 30, 10, Math.random() * 60 - 30), FireworkEffect
                                            .builder()
                                            .flicker(true)
                                            .withColor(Constants.PINK)
                                            .withColor(Color.AQUA)
                                            .withFade(Color.AQUA)
                                            .withFade(Constants.PINK)
                                            .with(Utils.randomType())
                                            .with(Utils.randomType())
                                            .build());
                                }
                            });
                            Thread.sleep(436);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 278:
                async(() -> {
                    try {
                        for (int i = 0; i < 120; i++) {
                            final Location loc = center.clone().add(Math.random() * 80 - 40, 10 + Math.random() * 30, Math.random() * 80 - 40);
                            if (Utils.r.nextBoolean()) Curves.sphericalHelix(loc); else Curves.sphericalSinusoid(loc);
                            Thread.sleep(200);
                        }
                    } catch (Exception ignored) {}
                });
                break;
            case 300:
                drawText("Thank You For");
                break;
            case 304:
                drawText("Watching!");
                break;
            case 310:
                drawText("Happy New Year!");
                break;
            case 312:
                // noinspection DuplicateBranchesInSwitch
                rain(Particle.FIREWORKS_SPARK);
        }
    }

    public void start(final Location center) {
        try { timer.purge(); } catch (Exception ignored) { }
        plugin.getServer().getOnlinePlayers().forEach(it -> {
            it.stopSound("com.neko-craft.hny", SoundCategory.RECORDS);
            it.playSound(it.getLocation(), "com.neko-craft.hny", SoundCategory.RECORDS, 1f, 1f);
        });
        tick = 0;
        this.center = center;
        Birds.clean(center.getWorld());
        birds = new Birds(center);
        timer.schedule(this, 0L, 1000L);
    }

    private void rain(final Particle type) {
        final World world = center.getWorld();
        for (double i = 0.0; i < END; i += Math.PI / 32) {
            world.spawnParticle(type, center.clone().add(Math.cos(i) * 40, 10.0, Math.sin(i) * 40), 100, 30.0, 10.0, 30.0, 1.0, null, true);
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void drawText(final String str) {
        final MapFont.CharacterSprite[] arr = new MapFont.CharacterSprite[str.length()];
        int i = 0, len = 0;
        Location loc = center.clone().add(0, 70, 0);
        for (final char ch : str.toCharArray()) {
            MapFont.CharacterSprite cs = arr[i++] = MinecraftFont.Font.getChar(ch);
            loc.setX(loc.getX() + cs.getHeight() / 2.0);
        }
        final World world = center.getWorld();
        for (final MapFont.CharacterSprite it : arr) {
            for (int y = 0, h = it.getHeight(); y < h; y++) for (int x = 0, w = it.getWidth(); x < w; x++) {
                if (it.get(y, x)) {
                    final Location l = loc.clone().add(-(x + len), 0.0, y);
                    world.spawnParticle(Particle.FALLING_OBSIDIAN_TEAR, l, 150, 0.3, 0.0, 0.3, 1.0, null, true);
                    world.spawnParticle(Particle.END_ROD, l, 0, 0, 0, 0, 1.0, null, true);
                }
            }
            len += it.getWidth() + 2;
        }
    }

    private void genHeart(final Location loc) {
        final World world = loc.getWorld();
        final double ix = loc.getX(), iy = loc.getY(), iz = loc.getZ();
        final Particle type = Utils.randomParticleType();
        for (double i = 0.0; i < END; i += 0.005) {
            final double z = Math.cos(i) * 13 - 5 * Math.cos(2 * i) - 2 * Math.cos(3 * i) - Math.cos(4 * i);
            world.spawnParticle(type, ix + Math.pow(Math.sin(i), 3) * 16, iy, iz - z, 0, Utils.randomN(), 2.0, Utils.randomN(), 1.0, null, true);
        }
    }
}
