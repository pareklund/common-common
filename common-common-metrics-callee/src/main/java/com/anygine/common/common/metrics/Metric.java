package com.anygine.common.common.metrics;

/**
 * The Metric class encapsulates a metric with a particular value,
 * with an optional information (often describing the origin of 
 * this particular metric instance) and with a key identifying the
 * metric type (metric instances with the same key are grouped into
 * a metric series).
 * 
 * @author pareklund
 *
 * @param <T> A type implementing the Comparable interface
 */
public class Metric<T extends Comparable<T>> implements Comparable<Metric<T>>{
  
  private final String key;
  private final T value;
  private final String info;
  
  public Metric(String key, T value, String info) {
    this.key = key;
    this.value = value;
    this.info = info;
  }
  
  /**
   * 
   * @return The key identifying this metric type (not instance)
   */
  public String getKey() {
    return key;
  }
  
  /**
   * 
   * @return The metric instance value
   */
  public T getValue() {
    return value;
  }
  
  /**
   * 
   * @return The metric instance information
   */
  public String getInfo() {
    return info;
  }

  /**
   * Adapter method to support numerical operations on the metric
   * 
   * @return The value cast to a number if compatible, otherwise
   *         its hashcode.
   */
  public Number toNumber() {
    if (value.getClass().isAssignableFrom(Number.class)) {
      return Number.class.cast(value);
    }
    return value.hashCode();
  }
  
  /**
   * Making the metric comparable through delegation to its value
   */
  @Override
  public int compareTo(Metric<T> other) {
    return value.compareTo(other.getValue());
  }
  
  /**
   * Print convenience
   */
  @Override
  public String toString() {
    return "{ " + info + ": " + value + " }";
  }
  
  /**
   * NOTE: Equality is defined on key and value.
   *       Thus, two metrics with different info values will be deemed unique
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Metric<?>)) {
      return false;
    }
    Metric<T> otherMetric = (Metric<T>) other;
    return key.equals(otherMetric.getKey()) 
        && value.equals(otherMetric.getValue());
  }
  
  /**
   * For consistency with equals method
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + key.hashCode();
    hash = 31 * hash + value.hashCode();
    return hash;
  }
}
