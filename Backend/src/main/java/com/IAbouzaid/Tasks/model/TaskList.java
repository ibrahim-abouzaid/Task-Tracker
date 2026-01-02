package com.IAbouzaid.Tasks.model;

import com.IAbouzaid.Tasks.Base.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_list")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskList  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    //cascade remove -> when tasklist is deleted all related task will deleted too
    //cascade PERSIST -> when tasklist is added all related tasks will add too
    @OneToMany(mappedBy = "taskList",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Task> tasks;

    @Column(nullable = false , updatable = false)
    private LocalDate createdAt;
    @Column(nullable = false )
    private LocalDate updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(title, taskList.title) && Objects.equals(description, taskList.description) && Objects.equals(tasks, taskList.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, tasks);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                "} " + super.toString();
    }
}

