package io.miragon.zeebe.taskmanager.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant

interface UserTaskRepository : JpaRepository<UserTaskEntity, Long>
{
    @Query("SELECT t FROM UserTaskEntity t WHERE t.assignee = :assignee")
    fun findByAssignee(assignee: String): List<UserTaskEntity>

    @Query("SELECT t FROM UserTaskEntity t WHERE t.expiresAt > :currentInstant")
    fun findByExpiresAtAfter(@Param("currentInstant") currentInstant: Instant): List<UserTaskEntity>
}