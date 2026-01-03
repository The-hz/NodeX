package com.client.clients.features.modules.impl.misc;

import com.client.clients.Client;
import com.client.clients.features.modules.Category;
import com.client.clients.features.modules.Module;
import com.client.clients.features.modules.ModuleInfo;
import com.client.clients.ui.notification.Notification;
import com.client.clients.ui.notification.NotificationLevel;
import com.client.clients.util.helper.TimeHelper;

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
