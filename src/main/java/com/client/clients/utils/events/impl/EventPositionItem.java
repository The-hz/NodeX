package com.client.clients.utils.events.impl;

import com.client.clients.utils.events.api.events.callables.EventCancellable;
import net.minecraft.network.protocol.Packet;

public class EventPositionItem extends EventCancellable {
   private Packet<?> packet;

   public Packet<?> getPacket() {
      return this.packet;
   }

   public void setPacket(Packet<?> packet) {
      this.packet = packet;
   }

   public EventPositionItem(Packet<?> packet) {
      this.packet = packet;
   }
}
