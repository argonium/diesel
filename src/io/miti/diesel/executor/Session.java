package io.miti.diesel.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Session {
  
  /** The list of variables. */
  private Map<String, String> vars = null;
  
  public Session() {
    vars = new HashMap<String, String>(10);
  }
  
  public List<String> getVarNames() {
    List<String> names = new ArrayList<String>(vars.size());
    for (Entry<String, String> var : vars.entrySet()) {
      names.add(var.getKey());
    }
    
    return names;
  }
  
  public String getVariable(final String name) {
    return vars.get(name);
  }
  
  public void setVariable(final String name, final String value) {
    vars.put(name, value);
  }
  
  public boolean isDeclared(final String name) {
    return vars.containsKey(name);
  }
}
