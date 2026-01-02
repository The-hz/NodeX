package com.client.obsoverlay.modules.impl.misc;

import com.client.obsoverlay.Client;
import com.client.obsoverlay.modules.Category;
import com.client.obsoverlay.modules.Module;
import com.client.obsoverlay.modules.ModuleInfo;
import com.client.obsoverlay.ui.notification.Notification;
import com.client.obsoverlay.ui.notification.NotificationLevel;
import com.client.obsoverlay.utils.TimeHelper;

@ModuleInfo(
   name = "ClientFriend",
   description = "Treat other users as friend!",
   category = Category.MISC
)
public class ClientFriend extends Module {
   public static TimeHelper attackTimer = new TimeHelper();

   @Override
   public void onDisable() {
      attackTimer.reset();
      Notification notification = new Notification(NotificationLevel.INFO, "You can attack other players after 15 seconds.", 15000L);
      Client.getInstance().getNotificationManager().addNotification(notification);
   }
}
