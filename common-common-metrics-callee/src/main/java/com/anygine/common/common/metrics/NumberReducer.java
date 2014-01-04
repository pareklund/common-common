package com.anygine.common.common.metrics;

/**
 * Encapsulates logic that reduces the underlying list of metrics to a number
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
public interface NumberReducer {
  
  /**
   * Maps the underlying list of metrics to a number
   * 
   * @return A number corresponding to applying the mapper's encapsulated logic 
   *         to the list of metrics referenced by this number reducer (often 
   *         provided to the implementing class' constructor)
   */
  double reduce();
}
