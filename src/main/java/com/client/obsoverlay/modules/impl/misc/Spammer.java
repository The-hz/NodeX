package com.client.obsoverlay.modules.impl.misc;

import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.api.types.EventType;
import com.client.obsoverlay.events.impl.EventRunTicks;
import com.client.obsoverlay.modules.Category;
import com.client.obsoverlay.modules.Module;
import com.client.obsoverlay.modules.ModuleInfo;
import com.client.obsoverlay.utils.TimeHelper;
import com.client.obsoverlay.values.Value;
import com.client.obsoverlay.values.ValueBuilder;
import com.client.obsoverlay.values.impl.BooleanValue;
import com.client.obsoverlay.values.impl.FloatValue;
import com.client.obsoverlay.values.impl.ModeValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ModuleInfo(
   name = "Spammer",
   description = "Spam chat!",
   category = Category.MISC
)
public class Spammer extends Module {
   Random random = new Random();
   FloatValue delay = ValueBuilder.create(this, "Delay")
      .setDefaultFloatValue(6000.0F)
      .setFloatStep(100.0F)
      .setMinFloatValue(0.0F)
      .setMaxFloatValue(15000.0F)
      .build()
      .getFloatValue();
   ModeValue prefix = ValueBuilder.create(this, "Prefix").setDefaultModeIndex(0).setModes("None", "@", "/shout ").build().getModeValue();
   private final List<BooleanValue> values = new ArrayList<>();
   private final TimeHelper timer = new TimeHelper();

   @EventTarget
   public void onMotion(EventRunTicks e) {
      if (e.getType() == EventType.POST && this.timer.delay((double)this.delay.getCurrentValue())) {
         String prefix = this.prefix.isCurrentMode("None") ? "" : this.prefix.getCurrentMode();
         List<String> styles = this.values.stream().filter(BooleanValue::getCurrentValue).map(Value::getName).toList();
         if (styles.isEmpty()) {
            return;
         }

         String style = styles.get(this.random.nextInt(styles.size()));
         String message = prefix + style;
         mc.player.connection.sendChat(message);
         this.timer.reset();
      }
   }

   public List<BooleanValue> getValues() {
      return this.values;
   }
}
