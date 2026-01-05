import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TaskListService} from "../../../Service/TaskList/task-list.service";
import {TaskList} from "../../../model/TaskList/task-list";
import {TaskService} from "../../../Service/Task/task.service";
import {Task} from "../../../model/Task/task";

@Component({
  selector: 'app-task-list-details',
  templateUrl: './task-list-details.component.html',
  styleUrls: ['./task-list-details.component.css']
})
export class TaskListDetailsComponent {

  taskList:TaskList=new TaskList();
  progress:number=0;
  constructor(private activeRoute: ActivatedRoute,
              private taskListService: TaskListService,
              private taskService: TaskService,
              private router: Router) { }


  ngOnInit() {
    this.getTaskList();

    this.progress=this.getProgress();
    console.log("the closed tasks are "+this.progress);
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
  toggleStatus(task:Task){
    task.status = task.status === 'OPEN' ? 'CLOSE' : 'OPEN';
    this.taskService.updateTask(this.taskList.id,task.id,task).subscribe(task => {
      this.router.navigate(['/task-list/'+this.taskList.id]);
      this.ngOnInit();
    })
  }

  delectTask(task:Task){
    this.activeRoute.paramMap.subscribe(params => {
      this.taskList.id = params.get('id')!;
      this.taskService.deletTask(this.taskList.id,task.id).subscribe(task => {
        this.router.navigate(['/task-list/'+this.taskList.id]);
        this.ngOnInit();
      })
  });

}
  getProgress(): number {
    if ( this.taskList.tasks.length === 0) return 0;


    const closed = this.taskList.tasks.filter(t => t.status === 'CLOSE').length;

    return Math.round((closed / this.taskList.tasks.length) * 100);
  }


}
