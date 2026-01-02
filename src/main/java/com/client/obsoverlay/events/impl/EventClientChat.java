package com.client.obsoverlay.events.impl;

import com.client.obsoverlay.events.api.events.callables.EventCancellable;

public class EventClientChat extends EventCancellable {
   private final String message;

   public String getMessage() {
      return this.message;
   }

   public EventClientChat(String message) {
      this.message = message;
   }
}
