package com.client.obsoverlay.modules;

import com.client.obsoverlay.Client;
import com.client.obsoverlay.events.api.EventTarget;
import com.client.obsoverlay.events.impl.EventKey;
import com.client.obsoverlay.events.impl.EventMouseClick;
import com.client.obsoverlay.exceptions.NoSuchModuleException;
import com.client.obsoverlay.modules.impl.combat.AimAssist;
import com.client.obsoverlay.modules.impl.combat.AntiBots;
import com.client.obsoverlay.modules.impl.combat.AttackCrystal;
import com.client.obsoverlay.modules.impl.combat.Aura;
import com.client.obsoverlay.modules.impl.combat.AutoClicker;
import com.client.obsoverlay.modules.impl.combat.Velocity;
import com.client.obsoverlay.modules.impl.misc.AntiFireball;
import com.client.obsoverlay.modules.impl.misc.AutoTools;
import com.client.obsoverlay.modules.impl.misc.ChestStealer;
import com.client.obsoverlay.modules.impl.misc.ClientFriend;
import com.client.obsoverlay.modules.impl.misc.Disabler;
import com.client.obsoverlay.modules.impl.misc.FastPlace;
import com.client.obsoverlay.modules.impl.misc.Helper;
import com.client.obsoverlay.modules.impl.misc.InventoryCleaner;
import com.client.obsoverlay.modules.impl.misc.ItemTracker;
import com.client.obsoverlay.modules.impl.misc.KillSay;
import com.client.obsoverlay.modules.impl.misc.Spammer;
import com.client.obsoverlay.modules.impl.misc.Teams;
import com.client.obsoverlay.modules.impl.move.AutoMLG;
import com.client.obsoverlay.modules.impl.move.Blink;
import com.client.obsoverlay.modules.impl.move.FastWeb;
import com.client.obsoverlay.modules.impl.move.LongJump;
import com.client.obsoverlay.modules.impl.move.NoJumpDelay;
import com.client.obsoverlay.modules.impl.move.NoSlow;
import com.client.obsoverlay.modules.impl.move.SafeWalk;
import com.client.obsoverlay.modules.impl.move.Scaffold;
import com.client.obsoverlay.modules.impl.move.Sprint;
import com.client.obsoverlay.modules.impl.move.Stuck;
import com.client.obsoverlay.modules.impl.render.AntiBlindness;
import com.client.obsoverlay.modules.impl.render.AntiNausea;
import com.client.obsoverlay.modules.impl.render.ChestESP;
import com.client.obsoverlay.modules.impl.render.ClickGUIModule;
import com.client.obsoverlay.modules.impl.render.Compass;
import com.client.obsoverlay.modules.impl.render.EffectDisplay;
import com.client.obsoverlay.modules.impl.render.FullBright;
import com.client.obsoverlay.modules.impl.render.Glow;
import com.client.obsoverlay.modules.impl.render.HUD;
import com.client.obsoverlay.modules.impl.render.ItemTags;
import com.client.obsoverlay.modules.impl.render.MotionBlur;
import com.client.obsoverlay.modules.impl.render.NameProtect;
import com.client.obsoverlay.modules.impl.render.NameTags;
import com.client.obsoverlay.modules.impl.render.NoHurtCam;
import com.client.obsoverlay.modules.impl.render.NoRender;
import com.client.obsoverlay.modules.impl.render.PostProcess;
import com.client.obsoverlay.modules.impl.render.Projectile;
import com.client.obsoverlay.modules.impl.render.Scoreboard;
import com.client.obsoverlay.modules.impl.render.ScoreboardSpoof;
import com.client.obsoverlay.modules.impl.render.TimeChanger;
import com.client.obsoverlay.modules.impl.render.ViewClip;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModuleManager {
   private static final Logger log = LogManager.getLogger(ModuleManager.class);
   private final List<Module> modules = new ArrayList<>();
   private final Map<Class<? extends Module>, Module> classMap = new HashMap<>();
   private final Map<String, Module> nameMap = new HashMap<>();

   public ModuleManager() {
      try {
         this.initModules();
         this.modules.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
      } catch (Exception var2) {
         log.error("Failed to initialize modules", var2);
         throw new RuntimeException(var2);
      }

      Client.getInstance().getEventManager().register(this);
   }

   private void initModules() {
      this.registerModule(
         new Aura(),
         new HUD(),
         new Velocity(),
         new NameTags(),
         new ChestStealer(),
         new InventoryCleaner(),
         new Scaffold(),
         new AntiBots(),
         new Sprint(),
         new ChestESP(),
         new ClickGUIModule(),
         new Teams(),
         new Glow(),
         new ItemTracker(),
         new AutoMLG(),
         new ClientFriend(),
         new NoJumpDelay(),
         new FastPlace(),
         new AntiFireball(),
         new Stuck(),
         new ScoreboardSpoof(),
         new AutoTools(),
         new ViewClip(),
         new Disabler(),
         new Projectile(),
         new TimeChanger(),
         new FullBright(),
         new NameProtect(),
         new NoHurtCam(),
         new AutoClicker(),
         new AntiBlindness(),
         new AntiNausea(),
         new Scoreboard(),
         new Compass(),
         new Spammer(),
         new KillSay(),
         new Blink(),
         new FastWeb(),
         new PostProcess(),
         new AttackCrystal(),
         new EffectDisplay(),
         new NoRender(),
         new ItemTags(),
         new SafeWalk(),
         new AimAssist(),
         new MotionBlur(),
         new Helper(),
         new NoSlow(),
         new LongJump()
      );
   }

   private void registerModule(Module... modules) {
      for (Module module : modules) {
         this.registerModule(module);
      }
   }

   private void registerModule(Module module) {
      module.initModule();
      this.modules.add(module);
      this.classMap.put((Class<? extends Module>)module.getClass(), module);
      this.nameMap.put(module.getName().toLowerCase(), module);
   }

   public List<Module> getModulesByCategory(Category category) {
      List<Module> modules = new ArrayList<>();

      for (Module module : this.modules) {
         if (module.getCategory() == category) {
            modules.add(module);
         }
      }

      return modules;
   }

   public Module getModule(Class<? extends Module> clazz) {
      Module module = this.classMap.get(clazz);
      if (module == null) {
         throw new NoSuchModuleException();
      } else {
         return module;
      }
   }

   public Module getModule(String name) {
      Module module = this.nameMap.get(name.toLowerCase());
      if (module == null) {
         throw new NoSuchModuleException();
      } else {
         return module;
      }
   }

   @EventTarget
   public void onKey(EventKey e) {
      if (e.isState() && Minecraft.getInstance().screen == null) {
         for (Module module : this.modules) {
            if (module.getKey() == e.getKey()) {
               module.toggle();
            }
         }
      }
   }

   @EventTarget
   public void onKey(EventMouseClick e) {
      if (!e.isState() && (e.getKey() == 3 || e.getKey() == 4)) {
         for (Module module : this.modules) {
            if (module.getKey() == -e.getKey()) {
               module.toggle();
            }
         }
      }
   }

   public List<Module> getModules() {
      return this.modules;
   }
}
