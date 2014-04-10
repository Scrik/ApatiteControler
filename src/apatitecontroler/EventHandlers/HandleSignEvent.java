/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apatitecontroler.EventHandlers;

import apatitecontroler.Utils;
import apatitecontroler.worlds.ApatiteWorld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Daniil
 */
public class HandleSignEvent implements Listener{
   @EventHandler
   public boolean onPlayerInteract(PlayerInteractEvent event){
       Player pl = event.getPlayer();
       if(event.getAction() == Action.RIGHT_CLICK_BLOCK || ((event.getAction() == Action.LEFT_CLICK_BLOCK) && (pl.getGameMode() != GameMode.CREATIVE))){
           Block bl = event.getClickedBlock();
           if(event.getClickedBlock().getState() instanceof Sign){
                Sign sn = (Sign) event.getClickedBlock().getState();
                //APATITE TELEPORTER
                if((ChatColor.GREEN+"[AT]").equals(sn.getLine(0))){
                     String worldName = sn.getLine(1);
                     Utils.teleportPlayerToWorld(pl, worldName);
                     return true;
                  }
                //APATITE COMMAND EXECUTOR
                if((ChatColor.GREEN+"[AE]").equals(sn.getLine(0))){
                     String worldName = sn.getLine(1);
                     event.getPlayer().performCommand(worldName);
                     return true;
                  }
           }
       }
       return true;
   }
   @EventHandler
   public boolean onSignChange(SignChangeEvent event){
       Block bl = event.getBlock();
       if(bl.getState() instanceof Sign){
           Sign sn = (Sign)  bl.getState();
           String firstLine = ChatColor.stripColor(event.getLine(0)).trim();
           //APATITE TELEPORTER
           if("[AT]".equals(firstLine)){
               if(Utils.hasPermission(event.getPlayer(),"ac.sign.create.teleport")){
                    Utils.sendMsg(event.getPlayer(), ChatColor.GREEN+"���� ����������!");
                   event.setLine(0, ChatColor.GREEN+"[AT]");
               }else{Utils.sendMsg(event.getPlayer(), ChatColor.RED+"� ��� ��������� ���� ���� ���������� ������ ����");
               event.setLine(0, ChatColor.RED+"[AT]");}
           }
           //APATITE COMMAND EXECUTOR
           if("[AE]".equals(firstLine)){
               if(Utils.hasPermission(event.getPlayer(),"ac.sign.create.command")){
                    Utils.sendMsg(event.getPlayer(), ChatColor.GREEN+"���� ����������!");
                    event.setLine(0, ChatColor.GREEN+"[AE]");
               }else{Utils.sendMsg(event.getPlayer(), ChatColor.RED+"� ��� ��������� ���� ���� ���������� ������ ����");
               event.setLine(0, ChatColor.RED+"[AE]");}
           }
       }
       return true;
   }
}
