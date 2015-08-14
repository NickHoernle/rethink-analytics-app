package com.rethink.analytics;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, String> {
	public List<Session> findByUserId(String userId);
    public List<Session> findByCourseId(String courseId);
    public List<Session> findByChapterId(String courseId);
    public List<Session> findByStartDateTime(String courseId);
    public List<Session> findByEndDateTime(String courseId);
    public List<Session> findByStartDateTimeGreaterThan(DateTime dateTime);
    public List<Session> findByEndDateTimeGreaterThan(DateTime dateTime);
    public List<Session> findByUserIdAndEndDateTimeGreaterThan(String userId, DateTime dateTime);
    public List<Session> findByEndDateTimeBetween(DateTime from, DateTime to);
}
