/*
 * Copyright 2012. Guidewire Software, Inc.
 */

package gw.lang.reflect;

public interface IMethodBackedPropertyInfo {

  IMethodInfo getReadMethodInfo();

  IMethodInfo getWriteMethodInfo();

}
