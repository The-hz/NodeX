package com.client.clients.utils.events.impl;

import com.client.clients.utils.events.api.events.Event;
import com.client.clients.utils.events.api.types.EventType;

public class EventRunTicks implements Event {
   private final EventType type;

   public EventType getType() {
      return this.type;
   }

   public EventRunTicks(EventType type) {
      this.type = type;
   }
}
