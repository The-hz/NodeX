package com.client.clients.features.modules.impl.move;

import com.client.clients.utils.events.api.EventTarget;
import com.client.clients.utils.events.api.types.EventType;
import com.client.clients.utils.events.impl.EventMotion;
import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;

@ModuleInfo(
   name = "Sprint",
   description = "Automatically sprints",
   category = Category.MOVEMENT
)
public class Sprint extends Module {
   @EventTarget(0)
   public void onMotion(EventMotion e) {
      if (e.getType() == EventType.PRE) {
         mc.options.keySprint.setDown(true);
         mc.options.toggleSprint().set(false);
      }
   }

   @Override
   public void onDisable() {
      mc.options.keySprint.setDown(false);
   }
}
