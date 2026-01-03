package com.client.clients.features.commands.impl;

import com.client.clients.features.commands.Command;
import com.client.clients.features.commands.CommandInfo;
import com.client.clients.utils.files.FileManager;
import java.io.IOException;

@CommandInfo(
   name = "config",
   description = "Open client config folder.",
   aliases = {"conf"}
)
public class CommandConfig extends Command {
   @Override
   public void onCommand(String[] args) {
      try {
         Runtime.getRuntime().exec("explorer " + FileManager.clientFolder.getAbsolutePath());
      } catch (IOException var3) {
      }
   }

   @Override
   public String[] onTab(String[] args) {
      return new String[0];
   }
}
