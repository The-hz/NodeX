package com.client.clients.features.modules.impl.misc;

import com.client.mixin.O.accessors.MinecraftAccessor;
import com.client.clients.utils.events.api.EventTarget;
import com.client.clients.utils.events.api.types.EventType;
import com.client.clients.utils.events.impl.EventMotion;
import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;
import com.client.clients.features.values.ValueBuilder;
import com.client.clients.features.values.impl.FloatValue;
import net.minecraft.world.item.BlockItem;

@ModuleInfo(
   name = "FastPlace",
   description = "Place blocks faster",
   category = Category.MISC
)
public class FastPlace extends Module {
   private final FloatValue cps = ValueBuilder.create(this, "CPS")
      .setDefaultFloatValue(10.0F)
      .setFloatStep(1.0F)
      .setMinFloatValue(5.0F)
      .setMaxFloatValue(20.0F)
      .build()
      .getFloatValue();
   private float counter = 0.0F;

   @EventTarget
   public void onMotion(EventMotion e) {
      if (e.getType() == EventType.PRE) {
         MinecraftAccessor accessor = (MinecraftAccessor)mc;
         if (mc.options.keyUse.isDown() && mc.player.getMainHandItem().getItem() instanceof BlockItem) {
            this.counter = this.counter + this.cps.getCurrentValue() / 20.0F;
            if (this.counter >= 1.0F / this.cps.getCurrentValue()) {
               accessor.setRightClickDelay(0);
               this.counter--;
            }
         } else {
            this.counter = 0.0F;
         }
      }
   }
}
