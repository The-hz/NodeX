package com.client.clients.features.modules.impl.misc;

import com.client.clients.utils.events.api.EventTarget;
import com.client.clients.utils.events.api.types.EventType;
import com.client.clients.utils.events.impl.EventRunTicks;
import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;
import com.client.clients.util.helper.TimeHelper;
import com.client.clients.features.values.Value;
import com.client.clients.features.values.ValueBuilder;
import com.client.clients.features.values.impl.BooleanValue;
import com.client.clients.features.values.impl.FloatValue;
import com.client.clients.features.values.impl.ModeValue;
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
