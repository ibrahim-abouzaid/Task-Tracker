import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TaskList} from "../../model/TaskList/task-list";

@Injectable({
  providedIn: 'root'
})
export class TaskListService {

  private readonly API_URL = 'http://localhost:8080/task-lists';

  constructor(private http: HttpClient) {}
  /** GET: get all task lists */
  getAllTaskLists(): Observable<TaskList[]> {
    return this.http.get<TaskList[]>(this.API_URL);
  }

  /** POST: create new task list */
  createTaskList(taskList: TaskList): Observable<TaskList> {
    return this.http.post<TaskList>(this.API_URL, taskList);
  }

  /** GET: get task list by id */
  getTaskListById(id: string): Observable<TaskList> {
    return this.http.get<TaskList>(`${this.API_URL}/${id}`);
  }

  /** PUT: update task list */
  updateTaskList(id: string, taskList: TaskList): Observable<TaskList> {
    return this.http.put<TaskList>(`${this.API_URL}/${id}`, taskList);
  }

  /** DELETE: delete task list */
  deleteTaskList(id: string): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
