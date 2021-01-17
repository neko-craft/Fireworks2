package cn.apisium.fireworks2;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.permission.Permissions;
import org.bukkit.plugin.java.annotation.plugin.*;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@SuppressWarnings("unused")
@Plugin(name = "Fireworks", version = "1.0")
@Description("Make a firework show!")
@Author("Shirasawa")
@Website("https://apisium.cn")
@ApiVersion(ApiVersion.Target.v1_15)
@Permissions(@Permission(name = "fireworks.start", defaultValue = PermissionDefault.OP))
@Commands(@org.bukkit.plugin.java.annotation.command.Command(name = "fireworks", permission = "fireworks.start"))
public final class Main extends JavaPlugin implements Listener {
    private final Performance performance = new Performance(this);

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        getServer().getPluginCommand("fireworks").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PARROT && Birds.NAME.equals(e.getEntity().getCustomName())) e.setCancelled(true);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        final Player player = (Player) sender;
//        final Location center = player.getLocation().add(0, 10, 0);
//        getServer().getScheduler().runTaskAsynchronously(this, () -> {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    Curves.sphericalHelix(center.clone().add(Math.random() * 80 - 40, 10 + Math.random() * 30, Math.random() * 80 - 40));
//                    Thread.sleep(200);
//                }
//            } catch (Exception ignored) {}
//        });
        performance.start(player.getLocation().add(0, 10, 0));
        return true;
    }
}
