package com.cyberark.bst;

import org.graphwalker.core.machine.ExecutionContext;

import javax.script.Bindings;
import javax.script.SimpleBindings;

public class PrgContext extends ExecutionContext {

  private static final Bindings bindings = new SimpleBindings();

  public PrgContext() {
    super();
    getScriptEngine().put("global", bindings);
  }
}
