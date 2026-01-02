package com.client.obsoverlay.events.impl;

import com.client.obsoverlay.events.api.events.Event;
import com.client.obsoverlay.events.api.types.EventType;

public class EventRunTicks implements Event {
   private final EventType type;

   public EventType getType() {
      return this.type;
   }

   public EventRunTicks(EventType type) {
      this.type = type;
   }
}
