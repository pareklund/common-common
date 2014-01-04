package com.anygine.common.common.job;

public interface Job {
  long getId();
  Schedule getSchedule();
  JobStatus getStatus();
  void run();
}
