package com.anygine.common.common.job;

public interface JobBuilder<T extends Job> {
  T newJob(long id);
}
