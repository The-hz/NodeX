package com.client.obsoverlay.modules.impl.render;

import com.client.obsoverlay.modules.Category;
import com.client.obsoverlay.modules.Module;
import com.client.obsoverlay.modules.ModuleInfo;
import com.client.obsoverlay.values.ValueBuilder;
import com.client.obsoverlay.values.impl.BooleanValue;
import com.client.obsoverlay.values.impl.FloatValue;

@ModuleInfo(
   name = "Scoreboard",
   description = "Modifies the scoreboard",
   category = Category.RENDER
)
public class Scoreboard extends Module {
   public BooleanValue hideScore = ValueBuilder.create(this, "Hide Red Score").setDefaultBooleanValue(true).build().getBooleanValue();
   public FloatValue down = ValueBuilder.create(this, "Down")
      .setDefaultFloatValue(120.0F)
      .setFloatStep(1.0F)
      .setMinFloatValue(0.0F)
      .setMaxFloatValue(300.0F)
      .build()
      .getFloatValue();
}
