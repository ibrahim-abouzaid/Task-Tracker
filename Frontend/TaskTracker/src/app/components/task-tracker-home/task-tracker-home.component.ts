import { Component } from '@angular/core';
import {TaskList} from "../../../model/TaskList/task-list";
import {TaskListService} from "../../../Service/TaskList/task-list.service";

@Component({
  selector: 'app-task-tracker-home',
  templateUrl: './task-tracker-home.component.html',
  styleUrls: ['./task-tracker-home.component.css']
})
export class TaskTrackerHomeComponent {

  taskLists: TaskList[] = [];



  constructor( private taskListService: TaskListService) {
  }

  ngOnInit() {
    this.getAllTaskLists();
  }
  getAllTaskLists() {
    this.taskListService.getAllTaskLists()
      .subscribe({
        next: data => this.taskLists = data,
        error: err => console.error(err)
      });


  }



}
