package com.anygine.common.common.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package private implementation of the MetricsCollector interface
 * 
 * @author pareklund
 *
 * @param <T> A type implementing the Comparable interface
 */
class MetricsCollectorImpl<T extends Comparable<T>> implements MetricsCollector<T> {

  private final Map<String, List<Metric<T>>> metrics;
  private final Map<String, Integer> counters;
 
  MetricsCollectorImpl() {
    metrics = new HashMap<String, List<Metric<T>>>();
    counters = new HashMap<String, Integer>();
  }
  
  @Override
  public void collect(String key, T value, String info) {
    List<Metric<T>> metricSeries = metrics.get(key);
    if (metricSeries == null) {
      metricSeries = new ArrayList<Metric<T>>();
      metrics.put(key, metricSeries);
    }
    metricSeries.add(new Metric<T>(key, value, info));
  }
  
  @Override
  public void increment(String key) {
    Integer counter = counters.get(key);
    if (counter == null) {
      counter = Integer.valueOf(0);
    }
    counters.put(key, counter.intValue() + 1);
  }
  
  @Override
  public int getCounter(String key) {
    return counters.get(key);
  }

  @Override
  public List<Metric<T>> getMetrics(String key) {
    return metrics.get(key);
  }
}
