package io.miragon.taskmanager.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserTaskRepository : JpaRepository<UserTaskEntity, Long>
{
    @Query("SELECT t FROM UserTaskEntity t WHERE t.assignee = :assignee")
    fun findByAssignee(assignee: String): List<UserTaskEntity>
}