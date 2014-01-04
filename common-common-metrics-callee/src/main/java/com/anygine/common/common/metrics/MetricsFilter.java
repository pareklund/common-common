package com.anygine.common.common.metrics;

/**
 * Encapsulates a criteria that determines the acceptance of a metric
 * 
 * NOTE: This is a typical functional interface and is well suited to
 *       be implemented with a lambda
 *       
 * @author pareklund
 *
 * @param <T> A type implementing the Comparable interface
 */
public interface MetricsFilter<T extends Comparable<T>> {
  
  /**
   * Determine whether a metric should be accepted or not
   * 
   * @param metric The metric to determine acceptance for
   * @return True if accepted, false otherwise
   */
  boolean accept(Metric<T> metric);
}
