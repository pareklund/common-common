package com.anygine.common.common.job;

public interface JobManager<T extends Job> {
  long addJob(JobBuilder<T> job);
  T getJob(long jobId);
}
