package com.client.clients.features.modules.impl.render;

import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;
import com.client.clients.ui.ClickGUI;

@ModuleInfo(
   name = "ClickGUI",
   category = Category.RENDER,
   description = "The ClickGUI"
)
public class ClickGUIModule extends Module {
   ClickGUI clickGUI = null;

   @Override
   protected void initModule() {
      super.initModule();
      this.setKey(344);
   }

   @Override
   public void onEnable() {
      if (this.clickGUI == null) {
         this.clickGUI = new ClickGUI();
      }

      mc.setScreen(this.clickGUI);
      this.toggle();
   }
}
