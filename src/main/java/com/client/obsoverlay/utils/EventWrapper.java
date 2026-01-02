package com.client.obsoverlay.utils;

import com.client.obsoverlay.Client;
import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.api.types.EventType;
import com.client.obsoverlay.events.impl.EventClientChat;
import com.client.obsoverlay.events.impl.EventMotion;
import com.client.obsoverlay.events.impl.EventRespawn;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGuiEvent.Post;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventWrapper {
   @SubscribeEvent
   public void onRender(Post e) {
   }

   @SubscribeEvent
   public void onClientChat(ClientChatEvent e) {
      EventClientChat event = new EventClientChat(e.getMessage());
      Client.getInstance().getEventManager().call(event);
      if (event.isCancelled()) {
         e.setCanceled(true);
      }
   }

   @EventTarget
   public void onMotion(EventMotion e) {
      if (e.getType() == EventType.PRE && Minecraft.getInstance().player.tickCount <= 1) {
         Client.getInstance().getEventManager().call(new EventRespawn());
      }
   }
}
