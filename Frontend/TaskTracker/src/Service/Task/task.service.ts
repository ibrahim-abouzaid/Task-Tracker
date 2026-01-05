import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { Task } from "src/model/Task/task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly baseUrl = 'http://localhost:8080/task-lists';

  constructor(private http: HttpClient) {}

  /** GET /task-lists/{taskListId}/tasks */
  getAllTasks(taskListId: string): Observable<Task[]> {
    return this.http.get<Task[]>(
      `${this.baseUrl}/${taskListId}/tasks`
    );
  }

  /** POST /task-lists/{taskListId}/tasks */
  createTask(taskListId: string, task: Task): Observable<Task> {
    return this.http.post<Task>(
      `${this.baseUrl}/${taskListId}/tasks`,
      task
    );
  }

  /** GET /task-lists/{taskListId}/tasks/{taskId} */
  getTask(taskListId: string, taskId: string): Observable<Task> {
    return this.http.get<Task>(
      `${this.baseUrl}/${taskListId}/tasks/${taskId}`
    );
  }

  /** PUT /task-lists/{taskListId}/tasks/{taskId} */
  updateTask(
    taskListId: string,
    taskId: string,
    task: Task
  ): Observable<Task> {
    return this.http.put<Task>(
      `${this.baseUrl}/${taskListId}/tasks/${taskId}`,
      task
    );
  }

  deletTask(taskListId: string, taskId: string): Observable<Task> {
    return this.http.delete<Task>(
      `${this.baseUrl}/${taskListId}/tasks/${taskId}`
    );
  }
}
