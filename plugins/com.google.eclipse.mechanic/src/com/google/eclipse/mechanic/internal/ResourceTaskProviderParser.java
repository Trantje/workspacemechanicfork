/*******************************************************************************
 * Copyright (C) 2011, Google Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.google.eclipse.mechanic.internal;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

/**
 * Parser for the list of resource tasks stored as preferences.
 */
public class ResourceTaskProviderParser {

  private static final Gson gson = new Gson();

  private final Function<String, String> variableParser;

  /**
   * Create a new instance.
   *
   * @param variableParser used to perform variable substitution. One such translator is
   * {@link VariableManagerStringParser#INSTANCE}. To get the raw values, pass
   * {@code Functions.<String>identity()}
   */
  public ResourceTaskProviderParser(Function<String, String> variableParser) {
    this.variableParser = Preconditions.checkNotNull(variableParser);
  }

  public final String[] parse(String text) {
    if (!text.startsWith("[")) {
      // I would use Splitter, but I won't use split.
      StringTokenizer st = new StringTokenizer(text, File.pathSeparator);
      List<String> list = Lists.newArrayList();
      while (st.hasMoreElements()) {
        String elem = (String) st.nextElement();
        // Historically paths named "null" somehow got added to default prefs
        if (elem == null) {
          continue;
        }
        String substituted = variableParser.apply(elem);
        list.add(substituted);
      }
      return list.toArray(new String[0]);
    } else {
      return gson.fromJson(text, String[].class);
    }
  }

  public final String unparse(String... items) {
    return gson.toJson(items);
  }
}
