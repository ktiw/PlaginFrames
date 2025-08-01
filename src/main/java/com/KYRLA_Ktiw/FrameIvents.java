package com.KYRLA_Ktiw;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FrameIvents implements Listener {

    private static final Set<UUID> framesfix = new HashSet<>();


    @EventHandler
    public void onPlayerInteractItemFrame(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();


        if (!(event.getRightClicked() instanceof ItemFrame)) {
            return;
        }
        ItemFrame frame = (ItemFrame) event.getRightClicked();

        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if (heldItem.getType() == Material.SHEARS && player.isSneaking()) {
            event.setCancelled(true);
            frame.setVisible(false);
            player.sendActionBar("§aРамка стала невидимой!");
            player.spawnParticle(Particle.CLOUD, frame.getLocation(), 10);
            player.playSound(frame.getLocation(), Sound.ENTITY_ITEM_FRAME_BREAK, 1.0f, 1.0f);
            return;
        }
        if (heldItem.getType() == Material.HONEY_BLOCK && player.isSneaking()) {
            event.setCancelled(true);
            UUID frameUuid = frame.getUniqueId();
            if(!framesfix.contains(frameUuid)) {
                frame.setFixed(true);
                framesfix.add(frameUuid);
                player.sendActionBar(ChatColor.GOLD + "Предмет в рамке зафиксирован");
                player.spawnParticle(Particle.CRIT,frame.getLocation(), 5);
                player.playSound(frame.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 1.0f);
            }else {
                player.sendActionBar(ChatColor.GOLD + "Эта рамка уже зафиксирован" + ChatColor.BLUE + " используйте топор");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.2f, 0.5f);
            }
            return;
        }
        if (heldItem.getType().toString().endsWith("_AXE") && player.isSneaking()) {
            event.setCancelled(true);
            UUID frameuuid = frame.getUniqueId();
            if(framesfix.contains(frameuuid)) {
                frame.setFixed(false);
                framesfix.remove(frameuuid);
                player.sendActionBar(ChatColor.BLUE + "Предмет в рамке разблокирован");
                player.spawnParticle(Particle.END_ROD, frame.getLocation(), 15);
                player.playSound(frame.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.0f);
            } else {
                player.sendActionBar(ChatColor.BLUE + "Эта рамка не была зафиксирована " + ChatColor.GOLD + "медом");
                player.playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 0.5f, 0.5f);
            }
            return;
        }
        if (heldItem.getType() == Material.LEATHER && player.isSneaking()) {
            event.setCancelled(true);
            frame.setVisible(true);
            player.sendActionBar("§aРамка стала видимой");
            player.spawnParticle(Particle.ELECTRIC_SPARK, frame.getLocation().add(0, 0.5, 0), 15, 0.3, 0.3, 0.3, 0.02);
            player.playSound(frame.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.7f, 1.5f);
            return;
        }
        if (frame.isFixed() && framesfix.contains(frame.getUniqueId())) {
            event.setCancelled(true);
            player.sendActionBar(ChatColor.GOLD + "Предмет в рамке зафиксирован используйте" + ChatColor.BLUE + " топор");
            player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 0.5f, 0.5f);
        }
    }
}