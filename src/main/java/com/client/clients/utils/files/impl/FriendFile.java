package com.client.clients.utils.files.impl;

import com.client.clients.utils.files.ClientFile;
import com.client.clients.util.client.FriendManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class FriendFile extends ClientFile {
   public FriendFile() {
      super("friend.cfg");
   }

   @Override
   public void read(BufferedReader reader) throws IOException {
      String line;
      while ((line = reader.readLine()) != null) {
         FriendManager.addFriend(line);
      }
   }

   @Override
   public void save(BufferedWriter writer) throws IOException {
      for (String friend : FriendManager.getFriends()) {
         writer.write(friend + "\n");
      }
   }
}
