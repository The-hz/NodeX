package com.client.obsoverlay.modules.impl.move;

import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.impl.EventSlowdown;
import com.client.obsoverlay.modules.Category;
import com.client.obsoverlay.modules.Module;
import com.client.obsoverlay.modules.ModuleInfo;

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
