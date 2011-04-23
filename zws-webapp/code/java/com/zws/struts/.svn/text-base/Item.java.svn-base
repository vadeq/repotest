package com.zws.struts;

import java.util.Collection;
import java.util.Vector;

public class Item {
  static int number = 0;
  private String ID = String.valueOf(number);
  private String name = "name" + String.valueOf(number);
  private String description = "description" + String.valueOf(number);
  private String extra = "extra" + String.valueOf(number);
  private Collection favoriteColors = new Vector();
  private String favoriteGame = "Basketball";
  private String favoriteAnimal = "Squid";

  public Item(){ number++; favoriteColors.add("Green");favoriteColors.add("Orange");}

  public String getID() { return ID; }
  public String getName() { return name; }
  public String getDescription() { return description; }
  public String getExtra() { return extra; }
  public Collection getFavoriteColors() { return favoriteColors; }
  public String getFavoriteGame() { return favoriteGame; }
  public String getFavoriteAnimal() { return favoriteAnimal; }

  public void setID(String x) { ID = x; }
  public void setName(String x) { name = x; }
  public void setDescription(String x) { description = x; }
  public void setExtra(String x) { extra = x; }
  public void setFavoriteColors(Collection c) { favoriteColors = c; }
  public void setFavoriteGame(String x) { favoriteGame = x; }
  public void setFavoriteAnimal(String x) { favoriteAnimal = x; }

  public boolean isValid() throws Exception {
    if (!getFavoriteGame().equalsIgnoreCase("baseball")) throw new Exception("favorite.game.wrong") ;
    return true;
  }

}
