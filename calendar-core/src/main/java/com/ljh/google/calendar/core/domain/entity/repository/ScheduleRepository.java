package com.ljh.google.calendar.core.domain.entity.repository;

import com.ljh.google.calendar.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
