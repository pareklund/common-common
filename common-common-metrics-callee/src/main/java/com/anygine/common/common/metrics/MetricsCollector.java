package com.anygine.common.common.metrics;

import java.util.List;

/**
 * Collects and retrieves metrics
 * 
 * @author pareklund
 *
 * @param <T> A type implementing the Comparable interface
 */
public interface MetricsCollector<T extends Comparable<T>> {
  
  /**
   * Collect a metric
   * 
   * @param key The metric type key
   * @param value The metric instance value
   * @param info The metric instance information
   */
  void collect(String key, T value, String info);
  
  /**
   * Increment a counter
   * 
   * @param key The counter key
   */
  void increment(String key);
  
  /**
   * Retrieve the value of a counter
   * 
   * @param key The counter key
   * @return The value of the counter
   */
  int getCounter(String key);
  
  /**
   * Retrieve the collected metrics
   * 
   * NOTE: Calls to this method for the same collector instance and metric key
   *       at a later point in time will return the union of the set of metrics 
   *       returned from a previous call and the set of metrics collected since
   *       that call. Thus, a collector is strictly cumulative and cannot be 
   *       reset. 
   *       
   * TODO: Investigate the need for resetting a collector for a particular
   *       metric key.
   *       
   * @param key The key to get metrics for
   * @return The metrics for the given key
   */
  List<Metric<T>> getMetrics(String key);
}
