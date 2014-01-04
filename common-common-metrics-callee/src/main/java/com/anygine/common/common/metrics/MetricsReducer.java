package com.anygine.common.common.metrics;

/**
 * Encapsulates logic that reduces the underlying list of metrics to a metric
 * 
 * NOTE 1: This is a typical functional interface and is well suited to
 *         be implemented with a lambda
 *         
 * NOTE 2: To preserve the functional qualities of this interface, any
 *         implementing class should leave the underlying list of metrics
 *         unchanged        
 *       
 * @author pareklund
 *
 * @param <T> A type implementing the Comparable interface
 */
public interface MetricsReducer<T extends Comparable<T>> {
  
  /**
   * Maps the underlying list of metrics to a metric
   * 
   * @return A metric corresponding to applying the mapper's encapsulated logic 
   *         to the list of metrics referenced by this metrics reducer (often 
   *         provided to the implementing class' constructor)
   */
  Metric<T> reduce();
}
