package com.client.obsoverlay.commands.impl;

import com.client.obsoverlay.Naven;
import com.client.obsoverlay.commands.Command;
import com.client.obsoverlay.commands.CommandInfo;
import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.api.types.EventType;
import com.client.obsoverlay.events.impl.EventMotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LanguageSelectScreen;

@CommandInfo(
   name = "language",
   description = "Open language gui.",
   aliases = {"lang"}
)
public class CommandLanguage extends Command {
   @Override
   public void onCommand(String[] args) {
      Naven.getInstance().getEventManager().register(new Object() {
         @EventTarget
         public void onMotion(EventMotion e) {
            if (e.getType() == EventType.PRE) {
               Minecraft.getInstance().setScreen(new LanguageSelectScreen(null, Minecraft.getInstance().options, Minecraft.getInstance().getLanguageManager()));
               Naven.getInstance().getEventManager().unregister(this);
            }
         }
      });
   }

   @Override
   public String[] onTab(String[] args) {
      return new String[0];
   }
}
