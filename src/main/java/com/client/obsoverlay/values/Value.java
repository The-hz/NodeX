package com.client.obsoverlay.values;

import com.client.obsoverlay.Client;
import com.client.obsoverlay.exceptions.BadValueTypeException;
import com.client.obsoverlay.values.impl.BooleanValue;
import com.client.obsoverlay.values.impl.FloatValue;
import com.client.obsoverlay.values.impl.ModeValue;
import com.client.obsoverlay.values.impl.StringValue;
import java.util.function.Supplier;

public abstract class Value {
   private final HasValue key;
   private final String name;
   private final Supplier<Boolean> visibility;

   protected Value(HasValue key, String name, Supplier<Boolean> visibility) {
      this.key = key;
      this.name = name;
      this.visibility = visibility;
      Client.getInstance().getValueManager().addValue(this);
   }

   public abstract ValueType getValueType();

   public BooleanValue getBooleanValue() {
      throw new BadValueTypeException();
   }

   public FloatValue getFloatValue() {
      throw new BadValueTypeException();
   }

   public StringValue getStringValue() {
      throw new BadValueTypeException();
   }

   public ModeValue getModeValue() {
      throw new BadValueTypeException();
   }

   public boolean isVisible() {
      return this.visibility == null || this.visibility.get();
   }

   public HasValue getKey() {
      return this.key;
   }

   public String getName() {
      return this.name;
   }

   public Supplier<Boolean> getVisibility() {
      return this.visibility;
   }
}
