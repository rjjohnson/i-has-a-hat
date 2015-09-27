package com.subtextgroup.mcp.ihah;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class IHasAHat extends JavaPlugin  {


    @Override
    public void onEnable() {
        getServer().broadcastMessage("I Has a Hat enabled! Type /hatme to place item in hand as helmet!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!"hatme".equalsIgnoreCase(command.getName())) {
            return false;
        }
        Player me = (Player)sender;
        
        ItemStack inHand = me.getItemInHand();
        if(isHat(inHand)) {
            me.getEquipment().setHelmet(inHand);
            return true;
        } else {
            sender.sendMessage("Not hattable: " + inHand != null ? inHand.getType().name() : "...");
            return false;
        }
        
    }


    public static boolean isHat(ItemStack itemStack) {
        // Nothingness should never be equipped
        if (itemStack == null)
            return false;
        if (itemStack.getAmount() == 0)
            return false;
        if (itemStack.getType() == Material.AIR)
            return false;

        if (itemStack.getType() == Material.PUMPKIN)
            return false;
        // However all other blocks are per default not equippable but should
        // be!
        if (itemStack.getType().isBlock())
            return true;

        // We also want to allow banners
        // For backwards compatibility below 1.8 we use the raw string rather
        // than enum comparison.
        // Material.BANNER
        if ("BANNER".equals(itemStack.getType().name()))
            return true;

        // Everything else is not allowed.
        return false;
    }
}
