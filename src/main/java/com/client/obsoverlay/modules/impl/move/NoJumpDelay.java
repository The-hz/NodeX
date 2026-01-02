package com.client.obsoverlay.modules.impl.move;

import com.client.mixin.O.accessors.LivingEntityAccessor;
import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.api.types.EventType;
import com.client.obsoverlay.events.impl.EventMotion;
import com.client.obsoverlay.modules.Category;
import com.client.obsoverlay.modules.Module;
import com.client.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   name = "NoJumpDelay",
   description = "Removes the delay when jumping",
   category = Category.MOVEMENT
)
public class NoJumpDelay extends Module {
   @EventTarget
   public void onMotion(EventMotion e) {
      if (e.getType() == EventType.PRE) {
         ((LivingEntityAccessor)mc.player).setNoJumpDelay(0);
      }
   }
}
