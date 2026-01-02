package com.client.obsoverlay.modules.impl.render;

import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.api.types.EventType;
import com.client.obsoverlay.events.impl.EventRenderScoreboard;
import com.client.obsoverlay.events.impl.EventRenderTabOverlay;
import com.client.obsoverlay.modules.Category;
import com.client.obsoverlay.modules.Module;
import com.client.obsoverlay.modules.ModuleInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

@ModuleInfo(
   name = "ServerNameSpoof",
   description = "Spoof the server name",
   category = Category.RENDER
)
public class ScoreboardSpoof extends Module {
   @EventTarget
   public void onRenderScoreboard(EventRenderScoreboard e) {
      String string = e.getComponent().getString();
      if (string.contains("布吉岛")) {
         MutableComponent textComponent = Component.literal("§d§l吉吉岛");
         textComponent.setStyle(e.getComponent().getStyle());
         e.setComponent(textComponent);
      }
   }

   @EventTarget
   public void onRenderTab(EventRenderTabOverlay e) {
      String string = e.getComponent().getString();
      if (string.contains("布吉岛")) {
         if (e.getType() == EventType.HEADER) {
            e.setComponent(Component.literal("§d§l吉吉岛"));
         } else if (e.getType() == EventType.FOOTER) {
            e.setComponent(Component.literal(""));
         }
      }
   }
}
