package com.client.clients.features.commands;

import com.client.clients.Client;
import com.client.clients.features.commands.impl.CommandBind;
import com.client.clients.features.commands.impl.CommandConfig;
import com.client.clients.features.commands.impl.CommandLanguage;
import com.client.clients.features.commands.impl.CommandProxy;
import com.client.clients.features.commands.impl.CommandToggle;
import com.client.clients.utils.events.api.EventTarget;
import com.client.clients.utils.events.impl.EventClientChat;
import com.client.clients.util.client.ChatUtils;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
   public static final String PREFIX = ".";
   public final Map<String, Command> aliasMap = new HashMap<>();

   public CommandManager() {
      try {
         this.initCommands();
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }

      Client.getInstance().getEventManager().register(this);
   }

   private void initCommands() {
      this.registerCommand(new CommandBind());
      this.registerCommand(new CommandToggle());
      this.registerCommand(new CommandConfig());
      this.registerCommand(new CommandLanguage());
      this.registerCommand(new CommandProxy());
   }

   private void registerCommand(Command command) {
      command.initCommand();
      this.aliasMap.put(command.getName().toLowerCase(), command);

      for (String alias : command.getAliases()) {
         this.aliasMap.put(alias.toLowerCase(), command);
      }
   }

   @EventTarget
   public void onChat(EventClientChat e) {
      if (e.getMessage().startsWith(".")) {
         e.setCancelled(true);
         String chatMessage = e.getMessage().substring(".".length());
         String[] arguments = chatMessage.split(" ");
         if (arguments.length < 1) {
            ChatUtils.addChatMessage("Invalid command.");
            return;
         }

         String alias = arguments[0].toLowerCase();
         Command command = this.aliasMap.get(alias);
         if (command == null) {
            ChatUtils.addChatMessage("Invalid command.");
            return;
         }

         String[] args = new String[arguments.length - 1];
         System.arraycopy(arguments, 1, args, 0, args.length);
         command.onCommand(args);
      }
   }
}
