package com.client.clients;

import com.client.clients.features.commands.CommandManager;
import com.client.clients.utils.events.api.EventManager;
import com.client.clients.utils.events.api.EventTarget;
import com.client.clients.utils.events.api.types.EventType;
import com.client.clients.utils.events.impl.EventRunTicks;
import com.client.clients.utils.events.impl.EventShutdown;
import com.client.clients.utils.files.FileManager;
import com.client.clients.features.modules.ModuleManager;
import com.client.clients.features.modules.impl.render.ClickGUIModule;
import com.client.clients.ui.notification.NotificationManager;
import com.client.clients.util.client.ingame.entity.EntityWatcher;
import com.client.clients.utils.events.EventWrapper;
import com.client.clients.util.client.LogUtils;
import com.client.clients.util.client.NetworkUtils;
import com.client.clients.util.client.ServerUtils;
import com.client.clients.util.helper.TickTimeHelper;
import com.client.clients.util.renderer.text.Fonts;
import com.client.clients.util.renderer.PostProcessRenderer;
import com.client.clients.util.renderer.Shaders;
import com.client.clients.util.rotation.RotationManager;
import com.client.clients.features.values.HasValueManager;
import com.client.clients.features.values.ValueManager;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraftforge.common.MinecraftForge;

public class Client {
   public static final String CLIENT_NAME = "Client-Modern";
   public static final String CLIENT_DISPLAY_NAME = "Client";
   private static Client instance;
   private final EventManager eventManager;
   private final EventWrapper eventWrapper;
   private final ValueManager valueManager;
   private final HasValueManager hasValueManager;
   private final RotationManager rotationManager;
   public final ModuleManager moduleManager;
   private final CommandManager commandManager;
   private final FileManager fileManager;
   private final NotificationManager notificationManager;
   public static float TICK_TIMER = 1.0F;
   public static Queue<Runnable> skipTasks = new ConcurrentLinkedQueue<>();

   private Client() {
      System.out.println("Client Init");
      instance = this;
      this.eventManager = new EventManager();
      Shaders.init();
      PostProcessRenderer.init();

      try {
         Fonts.loadFonts();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      } catch (FontFormatException var3) {
         throw new RuntimeException(var3);
      }

      this.eventWrapper = new EventWrapper();
      this.valueManager = new ValueManager();
      this.hasValueManager = new HasValueManager();
      this.moduleManager = new ModuleManager();
      this.rotationManager = new RotationManager();
      this.commandManager = new CommandManager();
      this.fileManager = new FileManager();
      this.notificationManager = new NotificationManager();
      this.fileManager.load();
      this.moduleManager.getModule(ClickGUIModule.class).setEnabled(false);
      this.eventManager.register(getInstance());
      this.eventManager.register(this.eventWrapper);
      this.eventManager.register(new RotationManager());
      this.eventManager.register(new NetworkUtils());
      this.eventManager.register(new ServerUtils());
      this.eventManager.register(new EntityWatcher());
      MinecraftForge.EVENT_BUS.register(this.eventWrapper);
   }

   public static void modRegister() {
      try {
         new Client();
      } catch (Exception var1) {
         System.err.println("Failed to load client");
         var1.printStackTrace(System.err);
      }
   }

   @EventTarget
   public void onShutdown(EventShutdown e) {
      this.fileManager.save();
      LogUtils.close();
   }

   @EventTarget(0)
   public void onEarlyTick(EventRunTicks e) {
      if (e.getType() == EventType.PRE) {
         TickTimeHelper.update();
      }
   }

   public static Client getInstance() {
      return instance;
   }

   public EventManager getEventManager() {
      return this.eventManager;
   }

   public EventWrapper getEventWrapper() {
      return this.eventWrapper;
   }

   public ValueManager getValueManager() {
      return this.valueManager;
   }

   public HasValueManager getHasValueManager() {
      return this.hasValueManager;
   }

   public RotationManager getRotationManager() {
      return this.rotationManager;
   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   public CommandManager getCommandManager() {
      return this.commandManager;
   }

   public FileManager getFileManager() {
      return this.fileManager;
   }

   public NotificationManager getNotificationManager() {
      return this.notificationManager;
   }
}
