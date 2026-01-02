import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TaskListService} from "../../../Service/TaskList/task-list.service";
import {TaskList} from "../../../model/TaskList/task-list";

@Component({
  selector: 'app-task-list-details',
  templateUrl: './task-list-details.component.html',
  styleUrls: ['./task-list-details.component.css']
})
export class TaskListDetailsComponent {

  taskList:TaskList=new TaskList();
  constructor(private activeRoute: ActivatedRoute,private taskListService: TaskListService,private router: Router) { }


  ngOnInit() {
    this.getTaskList();
  }

getTaskList() {

  this.activeRoute.paramMap.subscribe(params => {
    this.taskList.id = params.get('id')!;
   this.taskListService.getTaskListById(this.taskList.id).subscribe(taskList => {
     this.taskList=taskList;
   })
  });
}
  deleteTask(){
    this.taskListService.deleteTaskList(this.taskList.id).subscribe(taskList => {
      this.router.navigate(['']);

    })
  }

}
