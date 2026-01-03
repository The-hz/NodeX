package com.client.clients.features.commands.impl;

import com.client.clients.Client;
import com.client.clients.features.commands.Command;
import com.client.clients.features.commands.CommandInfo;
import com.client.clients.utils.exceptions.NoSuchModuleException;
import com.client.clients.features.modules.Module;
import com.client.clients.util.client.ChatUtils;

@CommandInfo(
   name = "toggle",
   description = "Toggle a module",
   aliases = {"t"}
)
public class CommandToggle extends Command {
   @Override
   public void onCommand(String[] args) {
      if (args.length == 1) {
         String moduleName = args[0];

         try {
            Module module = Client.getInstance().getModuleManager().getModule(moduleName);
            if (module != null) {
               module.toggle();
            } else {
               ChatUtils.addChatMessage("Invalid module.");
            }
         } catch (NoSuchModuleException var4) {
            ChatUtils.addChatMessage("Invalid module.");
         }
      }
   }

   @Override
   public String[] onTab(String[] args) {
      return Client.getInstance()
         .getModuleManager()
         .getModules()
         .stream()
         .map(Module::getName)
         .filter(name -> name.toLowerCase().startsWith(args.length == 0 ? "" : args[0].toLowerCase()))
         .toArray(String[]::new);
   }
}
