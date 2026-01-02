package com.client.obsoverlay;

import com.client.obsoverlay.commands.CommandManager;
import com.client.obsoverlay.events.api.EventManager;
import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.api.types.EventType;
import com.client.obsoverlay.events.impl.EventRunTicks;
import com.client.obsoverlay.events.impl.EventShutdown;
import com.client.obsoverlay.files.FileManager;
import com.client.obsoverlay.modules.ModuleManager;
import com.client.obsoverlay.modules.impl.render.ClickGUIModule;
import com.client.obsoverlay.ui.notification.NotificationManager;
import com.client.obsoverlay.utils.EntityWatcher;
import com.client.obsoverlay.utils.EventWrapper;
import com.client.obsoverlay.utils.LogUtils;
import com.client.obsoverlay.utils.NetworkUtils;
import com.client.obsoverlay.utils.ServerUtils;
import com.client.obsoverlay.utils.TickTimeHelper;
import com.client.obsoverlay.utils.renderer.Fonts;
import com.client.obsoverlay.utils.renderer.PostProcessRenderer;
import com.client.obsoverlay.utils.renderer.Shaders;
import com.client.obsoverlay.utils.rotation.RotationManager;
import com.client.obsoverlay.values.HasValueManager;
import com.client.obsoverlay.values.ValueManager;
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
