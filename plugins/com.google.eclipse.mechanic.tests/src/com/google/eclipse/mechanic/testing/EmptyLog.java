/*******************************************************************************
 * Copyright (C) 2011, Google Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.google.eclipse.mechanic.testing;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.osgi.framework.Bundle;

/**
 * no-op log.
 */
public class EmptyLog implements ILog {
  public void removeLogListener(ILogListener listener) {
  }
  public void log(IStatus status) {
  }
  public Bundle getBundle() {
    return null;
  }
  public void addLogListener(ILogListener listener) {
  }
}
