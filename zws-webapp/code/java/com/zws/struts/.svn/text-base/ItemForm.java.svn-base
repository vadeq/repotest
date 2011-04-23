package com.zws.struts;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

public class ItemForm extends ActionForm {
  private Item item = new Item();
  private String action;
  private String state ="view" ;

  private String[] selectedColors;

  private Collection games = new Vector();
  private Collection colors = new Vector();
  private Collection animals = new Vector();

  public ItemForm(){
    colors.add("Yellow");
    colors.add("Green");
    colors.add("Blue");
    colors.add("Red");
    colors.add("Purple");
    colors.add("Orange");
    animals.add("Baracuda");
    animals.add("White Tip");
    animals.add("Octopus");
    animals.add("Squid");
    animals.add("Crab");
    animals.add("Urchin");
    games.add("Football");
    games.add("Basketball");
    games.add("Baseball");
    games.add("Soccer");
    games.add("Hockey");
    games.add("Darts");
  }

  public Collection getGames(){ return games; }
  public Collection getColors(){ return colors; }
  public Collection getAnimals(){ return animals; }

  public Item getItem() { return item; }
  public void setItem(Item i) { item = i; }

  public String getAction() { return action; }
  public String getState() { return state; }
  public String getID() { return item.getID(); }
  public String getName() { return item.getName(); }
  public String getDescription() { return item.getDescription(); }
  public String getExtra() { return item.getExtra(); }
  public String[] getSelectedColors() {
    selectedColors = new String[item.getFavoriteColors().size()];
    Iterator i = item.getFavoriteColors().iterator();
    int c = 0;
    while(i.hasNext()) selectedColors[c++] = i.next().toString();
    return selectedColors;
  }
  public String getFavoriteGame() { return item.getFavoriteGame(); }
  public String getFavoriteAnimal() { return item.getFavoriteAnimal(); }

  public void setAction(String x) { action = x; }
  public void setState(String x) { state = x; }
  public void setID(String x) { item.setID(x); }
  public void setName(String x) { item.setName(x); }
  public void setDescription(String x) { item.setDescription(x); }
  public void setExtra(String x) { item.setExtra(x); }
  public void setSelectedColors(String[] x) {
      selectedColors = x;
      for (int i =0; i< selectedColors.length; i++)
        if (!item.getFavoriteColors().contains(selectedColors[i]))
          item.getFavoriteColors().add(selectedColors[i]);
  }
  public void setFavoriteGame(String x) { item.setFavoriteGame(x); }
  public void setFavoriteAnimal(String x) { item.setFavoriteAnimal(x); }
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
    if (!getAction().equals("save")) return null;
    ActionErrors errors = new ActionErrors();
    validateRequiredProperties(request, errors);
    try{
      getItem().isValid();
    }
    catch (Exception e) {
      errors.add("formError", new ActionError(e.getMessage()));
    }
    {} //System.out.println("v3 errors=" + String.valueOf(errors.size()));
    return errors;
  }

  public void validateRequiredProperties(HttpServletRequest request, ActionErrors errors){
    boolean missingFields = false;
    if (isBlank("name", request)) {
      errors.add("name", new ActionError("required.field","Name"));
      missingFields = true;
    }
    if (missingFields) errors.add("formError", new ActionError("blank.required.fields"));
  }

  public boolean isBlank(String field, HttpServletRequest request){
    return (null==request.getParameter(field) || "".equals(request.getParameter(field)));
  }
}
