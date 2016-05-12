package com.google.eclipse.mechanic.plugin.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.eclipse.mechanic.RepairDecisionProvider;
import com.google.eclipse.mechanic.Task;

public class AutoChoiceDecisionProvider implements RepairDecisionProvider {

  private Map<Task, RepairDecisionProvider.Decision> decisions
      = new HashMap<Task, RepairDecisionProvider.Decision>();


  public ResponseStatus initialize(List<Task> failing) {

    ResponseStatus rs = ResponseStatus.CANCEL;
    
    
    if(failing.size() > 0){
      for (Task task : failing) {
        decisions.put(task, Decision.YES);
      } 
      rs = ResponseStatus.OK;
    }
    
    // finally, return the status
    return rs;
  }

  public Map<Task, Decision> getDecisions() {
    System.out.println("running size" + decisions.size());
    return decisions;
  }

}
