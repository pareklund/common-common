package com.anygine.common.common.metrics;

import java.util.List;

/**
 * Encapsulates logic that maps the underlying list of metrics to another
 * list of metrics
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
public interface MetricsMapper<T extends Comparable<T>> {
  
  /**
   * Maps the underlying list of metrics to another list of metrics
   * 
   * @return A list of metrics corresponding to applying the mapper's 
   *         encapsulated logic to the list of metrics referenced by this 
   *         mapper (often provided to the implementing class' constructor)
   */
  List<Metric<T>> map();
}
