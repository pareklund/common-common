package com.anygine.common.common.metrics;

import java.io.PrintWriter;

/**
 * Encapsulated logic that prints the underlying list of metrics
 * 
 * @author pareklund
 *
 * @param <T> A type implementing the Comparable interface
 */
public interface MetricsPrinter<T> {
  
  /**
   * Prints the underlying list of metrics
   * 
   * @param writer The writer to print to
   * @param description The description of the metrics
   * @param onlyValue If true, print only values, otherwise print whole metrics
   */
  void print(PrintWriter writer, String description, boolean onlyValue);
}
