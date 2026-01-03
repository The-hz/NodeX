package com.client.clients.features.modules.impl.move;

import com.client.clients.utils.events.api.EventTarget;
import com.client.clients.utils.events.impl.EventSlowdown;
import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;

@ModuleInfo(
   name = "NoSlow",
   description = "NoSlowDown",
   category = Category.MOVEMENT
)
public class NoSlow extends Module {
   @EventTarget
   public void onSlow(EventSlowdown eventSlowdown) {
      if (mc.player.getUseItemRemainingTicks() % 2 != 0 && mc.player.getUseItemRemainingTicks() <= 30) {
         eventSlowdown.setSlowdown(false);
         mc.player.setSprinting(true);
      }
   }
}
