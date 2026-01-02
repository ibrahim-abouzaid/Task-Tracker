package com.IAbouzaid.Tasks.model;

import com.IAbouzaid.Tasks.Base.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Tasks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Task  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")

    private LocalDate dueDate;

    @Column(name="status", nullable = false)
    private TaskStatus status;

    @Column(name = "priority",nullable = false)
    private TaskPriority priority;

    //fetch LAZY -> the tasklist wouldn't be loaded from database until it's actually needed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    @Column(nullable = false , updatable = false)
    private LocalDate createdAt;
    @Column(nullable = false )
    private LocalDate updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate) && status == task.status && priority == task.priority && Objects.equals(taskList, task.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, dueDate, status, priority, taskList);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", taskList=" + taskList +
                "} " + super.toString();
    }
}
