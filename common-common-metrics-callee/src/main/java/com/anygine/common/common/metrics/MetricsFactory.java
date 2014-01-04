package com.anygine.common.common.metrics;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates and maintains instances of metrics support classes
 * 
 * NOTE: Fundamental mapper- and reducer- implementations are provided here.
 *       In addition, any user class can define and use any user-supplied 
 *       (often) anonymous class as mapper, reducer or filter.
 *       Together with their composable nature, this combines the benefits 
 *       of convenience and extensibility.
 * 
 * @author pareklund
 *
 */
public class MetricsFactory {

  private static final Map<Class<? extends Comparable<?>>, MetricsCollector<? extends Comparable<?>>> collectors = new HashMap<>();

  private MetricsFactory() {}

  /**
   * Retrieve a metric collector instance
   * 
   * @return A metric collector instance 
   */
  public static <T extends Comparable<T>> MetricsCollector<T> getMetricsCollector() {
    return new MetricsCollectorImpl<T>();
  }

  /**
   * Allows for creating and registering a collector identified by a (erased) 
   * class implementing Comparable
   * 
   * @param key The class identifying the given metrics collector
   * @return A metrics collector for the given class
   */
  public static <T extends Comparable<T>> MetricsCollector<T> registerCollector(
      Class<T> key) {
    MetricsCollector<T> collector = getMetricsCollector();
    collectors.put(key, collector);
    return collector;
  }

  /**
   * Retrieves the metrics collector registered for a particular (erased) class
   * implementing Comparable
   * 
   * @param key The class identifying the given metrics collector
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> MetricsCollector<T> getCollector(
      Class<T> key) {
    return (MetricsCollector<T>) collectors.get(key);
  }

  /**
   * Returns a mapper corresponding to the supplied metrics.
   * 
   * The mapper acts as a pure reference adapter, i.e. invoking it at a later 
   * point in time always returns a reference to the metrics list supplied 
   * to this method.
   * 
   * @param metrics A list of metrics
   * @return A metrics mapper reference adapter for the list of metrics given
   */
  public static <T extends Comparable<T>> MetricsMapper<T> toMapper(
      final List<Metric<T>> metrics) {
    return new MetricsMapper<T>() {

      @Override
      public List<Metric<T>> map() {
        return metrics;
      }
    };
  }

  /**
   * Returns a number reducer to return the number of metrics represented by
   * the supplied metrics mapper
   * 
   * @param mapper A metrics mapper
   * @return The number of metrics represented by the suppled metrics mapper
   */
  public static <T extends Comparable<T>> NumberReducer count(
      final MetricsMapper<T> mapper) {
    return new NumberReducer() {

      @Override
      public double reduce() {
        return mapper.map().size();
      }
    };
  }

  /**
   * Returns a number reducer to return the variance of the metrics
   * represented by the supplied metrics mapper
   * 
   * NOTE: Will only return sensible results when:
   *       T isAssignableFrom Number
   *       Otherwise, the returned variance will be a variance of the 
   *       hash codes of the underlying metrics.
   * 
   * @param mapper A metrics mapper
   * @return The variance of the metrics represented by the supplied metrics 
   *         mapper
   */
  public static <T extends Comparable<T>> NumberReducer variance(
      final MetricsMapper<T> mapper) {
    return new NumberReducer() {

      @Override
      public double reduce() {
        List<Metric<T>> metrics = mapper.map();
        double sum = 0;
        for (Metric<T> metric : metrics) {
          sum += metric.toNumber().doubleValue();
        }
        double avg = sum / metrics.size();
        double diffSquaresSum = 0; 
        for (Metric<T> metric : metrics) {
          diffSquaresSum += Math.pow(
              metric.toNumber().doubleValue() - avg, 2);
        }
        return Math.sqrt(diffSquaresSum / avg);
      }
    };
  }

  /**
   * Returns a number reducer to return the average of the metrics
   * represented by the supplied metrics mapper
   *  
   * NOTE: Will only return sensible results when:
   *       T isAssignableFrom Number
   *       Otherwise, the returned average will be an average of the 
   *       hash codes of the underlying metrics.
   * 
   * @param mapper A metrics mapper
   * @return The average of the metrics represented by the supplied metrics
   *         mapper
   */
  public static <T extends Comparable<T>> NumberReducer average(
      final MetricsMapper<T> mapper) {
    return new NumberReducer() {

      @Override
      public double reduce() {
        List<Metric<T>> metrics = mapper.map();
        double sum = 0;
        for (Metric<T> metric : metrics) {
          sum += metric.toNumber().doubleValue();
        }
        return sum / metrics.size();
      }
    };
  }

  /**
   * Returns a metrics reducer returning the metric with the maximum value
   * of the metrics represented by the supplied metrics mapper
   * 
   * @param mapper A metrics mapper
   * @return The metric with the maximum value of the metrics represented by
   *         the supplied metrics mapper
   */
  public static <T extends Comparable<T>> MetricsReducer<T> max(
      final MetricsMapper<T> mapper) {
    return new MetricsReducer<T>() {

      @Override
      public Metric<T> reduce() {
        List<Metric<T>> sorted = new ArrayList<>(mapper.map());
        Collections.sort(sorted);
        return sorted.get(sorted.size() - 1);
      }
    };
  }

  /**
   * Returns a metrics reducer returning the metric with the minimum value
   * of the metrics represented by the supplied metrics mapper
   * 
   * @param mapper A metrics mapper
   * @return The metric with the minimum value of the metrics represented by
   *         the supplied metrics mapper
   */
  public static <T extends Comparable<T>> MetricsReducer<T> min(
      final MetricsMapper<T> mapper) {
    return new MetricsReducer<T>() {

      @Override
      public Metric<T> reduce() {
        List<Metric<T>> sorted = new ArrayList<>(mapper.map());
        Collections.sort(sorted);
        return sorted.get(0);
      }
    };
  }

  /**
   * Returns a metrics reducer returning the metric with the median value
   * of the metrics represented by the supplied metrics mapper
   * 
   * NOTE: If there are more than one metric with the same median value, 
   *       only one metric is returned and it is undefined which one
   * 
   * @param mapper A metrics mapper
   * @return The metric with the median value of the metrics represented by
   *         the supplied metrics mapper
   */
  public static <T extends Comparable<T>> MetricsReducer<T> median(
      final MetricsMapper<T> mapper) {
    return new MetricsReducer<T>() {

      @Override
      public Metric<T> reduce() {
        List<Metric<T>> sorted = new ArrayList<>(mapper.map());
        Collections.sort(sorted);
        return sorted.get(sorted.size() / 2);
      }
    };
  }

  /**
   * Returns a metrics mapper returning the unique set of the metrics 
   * represented by the supplied metrics mapper.
   * 
   * NOTE: The set of metrics represented by the supplied metrics mapper will
   *       be left unchanged, i.e. the returned metrics mapper will always be
   *       backed by a different metrics list.
   * 
   * @param mapper A metrics mapper
   * @return The unique set of the metrics represented by the supplied metrics
   *         mapper
   */
  public static <T extends Comparable<T>> MetricsMapper<T> unique(
      final MetricsMapper<T> mapper) {
    return new MetricsMapper<T>() {

      @Override
      public List<Metric<T>> map() {
        List<Metric<T>> uniques = new ArrayList<Metric<T>>();
        for (Metric<T> metric : mapper.map()) {
          if (!uniques.contains(metric)) {
            uniques.add(metric);
          }
        }
        return uniques;
      }
    };
  }

  /**
   * Returns a metrics mapper returning the set of metrics represented 
   * by the supplied metrics mapper filtered with the supplied metrics filter
   * 
   * NOTE: The set of metrics represented by the supplied metrics mapper will
   *       be left unchanged, i.e. the returned metrics mapper will always be
   *       backed by a different metrics list.
   * 
   * @param mapper A metrics mapper
   * @return The set of metrics represented by the supplied metrics mapper 
   *         filtered with the supplied metrics filter
   */
  public static <T extends Comparable<T>> MetricsMapper<T> filter(
      final MetricsMapper<T> mapper, final MetricsFilter<T> filter) {
    return new MetricsMapper<T>() {

      @Override
      public List<Metric<T>> map() {
        List<Metric<T>> filtered = new ArrayList<Metric<T>>();
        for (Metric<T> metric : mapper.map()) {
          if (filter.accept(metric)) {
            filtered.add(metric);
          }
        }
        return filtered;
      }
    };
  }

  /**
   * Returns a metrics printer printing the set of metrics represented 
   * by the supplied metrics mapper
   * 
   * @param mapper A metrics mapper
   * @return A metrics printer printing the set of metrics represented by the
   *         supplied metrics mapper
   */  
  public static <T extends Comparable<T>> MetricsPrinter<T> getPrinter(
      final List<Metric<T>> metrics) {
    return new MetricsPrinter<T>() {

      @Override
      public void print(
          PrintWriter writer, String description, boolean onlyValue) {
        writer.println(description + ":");
        if (onlyValue) {
          String comma = "";
          writer.print("{ ");
          for (Metric<T> metric : metrics) {
            writer.print(comma + metric.getValue());
            comma = ", ";
          }
          writer.println(" }");
        } else {
          writer.println("[");
          for (Metric<T> metric : metrics) {
            writer.println("  " + metric);
          }
          writer.println("]");
        }
      }
    };
  }
}
