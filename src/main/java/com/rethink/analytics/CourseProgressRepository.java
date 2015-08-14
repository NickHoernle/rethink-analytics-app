package com.rethink.analytics;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseProgressRepository extends MongoRepository<CourseProgress, String> {

    public List<CourseProgress> findByUserId(String userId);
    public List<CourseProgress> findByCourseId(String courseId);
}