import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TaskListService} from "../../../Service/TaskList/task-list.service";
import {TaskService} from "../../../Service/Task/task.service";
import {Task} from "../../../model/Task/task";

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {

  priority: 'HIGH' | 'MEDIUM' | 'LOW' = 'MEDIUM';


  taskListId:string="";

  constructor(private activeRoute: ActivatedRoute,private taskService: TaskService,private router: Router) { }

  ngOnInit() {
    this.activeRoute.paramMap.subscribe(params => {
      this.taskListId = params.get('id')!;

    });
  }
  setPriority(value: 'HIGH' | 'MEDIUM' | 'LOW') {
    this.priority = value;
  }

  createTask(title: string, description: string, dueDate: string ) {
    let task = new Task();
    task.title = title;
    task.description = description;
    task.dueDate = dueDate;
    task.priority=this.priority
    this.taskService.createTask(this.taskListId, task)
      .subscribe({
        next:
           res =>{ console.log('Task created', res)
             this.router.navigate(['/task-list', this.taskListId])
  },
        error: err => console.error(err)
      });
}
}
