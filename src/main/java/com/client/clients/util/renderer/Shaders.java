package com.client.clients.util.renderer;

public class Shaders {
   public static Shader TEXT;

   public static void init() {
      TEXT = new Shader("text.vert", "text.frag");
   }
}
