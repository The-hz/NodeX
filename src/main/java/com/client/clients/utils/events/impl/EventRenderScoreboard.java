package com.client.clients.utils.events.impl;

import com.client.clients.utils.events.api.events.Event;
import net.minecraft.network.chat.Component;

public class EventRenderScoreboard implements Event {
   private Component component;

   public EventRenderScoreboard(Component component) {
      this.component = component;
   }

   public Component getComponent() {
      return this.component;
   }

   public void setComponent(Component component) {
      this.component = component;
   }
}
