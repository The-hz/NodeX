package com.client.clients.features.modules.impl.render;

import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;
import com.client.clients.features.values.ValueBuilder;
import com.client.clients.features.values.impl.FloatValue;

@ModuleInfo(
   name = "FullBright",
   description = "Make your world brighter.",
   category = Category.RENDER
)
public class FullBright extends Module {
   public FloatValue brightness = ValueBuilder.create(this, "Brightness")
      .setDefaultFloatValue(1.0F)
      .setFloatStep(0.1F)
      .setMinFloatValue(0.0F)
      .setMaxFloatValue(1.0F)
      .build()
      .getFloatValue();
}
