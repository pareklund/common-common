package com.anygine.core.common.codegen.api;

import playn.core.Json;

// TODO: Generate this enum (one enum constant for each 
//       encountered Embeddable/Storable class
public interface JsonWritableInternal {

  // TODO: Possibly solve this in a different way to allow for 
  //       framework users to add new types
  public static enum JsonType {
    Profile, Session, GameComponentState, 
    CollectableBase, ExitBase, Inventory, SearchableBase, ProjectileBase, 
    GroundBasedEnemyBase, AirBasedEnemyBase, AmmoBase, ConsumableBase, Effect, 
    GunBase, AmmoSupply, LevelBase, Tile, Player, StoredProfile, 
    AnygineException, Animation, AnimationPlayer, Rectangle, ImageWithPath, 
    SoundWithPath, Vector2, UniqueConstraintViolationException, InventoryItemBase, ValuableBase, PlatformerPlayer;
  }
  
  public static enum TypeOfData {
    Object, Id, ChangedFields;
  }
    
  JsonType getJsonType();

  void writeJson(String key, Json.Writer writer);
  
  void update(Json.Object jsonObj);
}
