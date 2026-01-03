package com.client.clients.utils.events.impl;

import com.client.clients.utils.events.api.events.Event;

public class EventAttackYaw implements Event {
   private float yaw;

   public float getYaw() {
      return this.yaw;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public EventAttackYaw(float yaw) {
      this.yaw = yaw;
   }
}
