import { Component } from '@angular/core';
import {Task} from "../../../model/Task/task";
import {ActivatedRoute, Router} from "@angular/router";
import {TaskService} from "../../../Service/Task/task.service";

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent {


  task:Task={} as Task;
  taskListId:string="";

  constructor(private taskService: TaskService, private activatedRouter: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.getTask()
  }

  getTask(){
    this.activatedRouter.paramMap.subscribe(params=>{
      this.taskListId = params.get('id')!;
      this.task.id=params.get('taskId')!;
      this.taskService.getTask(this.taskListId,this.task.id).subscribe(
       task =>{
         this.task=task;
      }
      )
    });

  }
  editTask(title:string, description:string,dueDate:string){
    this.task.title = title;
    this.task.description = description;
    this.task.dueDate = dueDate;
    this.taskService.updateTask(this.taskListId,this.task.id,this.task).subscribe(taskList => {
      this.router.navigate(['/task-list',this.taskListId]);
    })
  }
  setPriority(value: 'HIGH' | 'MEDIUM' | 'LOW') {
    this.task.priority = value;
  }


}
