import { Component } from '@angular/core';
import {TaskList} from "../../../model/TaskList/task-list";
import {TaskListService} from "../../../Service/TaskList/task-list.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-task-list',
  templateUrl: './create-task-list.component.html',
  styleUrls: ['./create-task-list.component.css']
})
export class CreateTaskListComponent {


  constructor(private taskListService: TaskListService, private router: Router) {
  }

  createTask(title: string, description: string) {
    let taskList = new TaskList()
    taskList.title = title;
    taskList.description = description;
    this.taskListService.createTaskList(taskList).subscribe(
      {
        next: (response) => {
          console.log('TaskList created successfully', response);
          // Redirect to main page
          this.router.navigate(['/']) // or replace '/' with your main page route
        }
      });


  }
}
