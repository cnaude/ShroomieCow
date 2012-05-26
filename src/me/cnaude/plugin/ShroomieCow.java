/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cnaude.plugin;

import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author cnaude
 */
public class ShroomieCow extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity e = event.getRightClicked();
        Player p = event.getPlayer();
        if (p.hasPermission("shroomiecow.create")) {
            if ((e.getType().equals(EntityType.COW))) {
                ItemStack bait = p.getItemInHand();
                if (bait != null) {
                    if (bait.getType().equals(Material.MUSHROOM_SOUP)) {
                        int amt = bait.getAmount();
                        if (amt == 1) {
                            p.getInventory().removeItem(new ItemStack[]{bait});
                        } else {
                            bait.setAmount(amt - 1);
                        }
                        Location loc = e.getLocation();                        
                        e.remove();
                        p.getWorld().spawnCreature(loc, EntityType.MUSHROOM_COW);
                        p.sendMessage(ChatColor.GREEN + "The cow feels different.");
                    }
                }
            }
        }

    }
}
