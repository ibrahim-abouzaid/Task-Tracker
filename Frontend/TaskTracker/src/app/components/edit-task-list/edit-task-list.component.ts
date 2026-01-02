import { Component } from '@angular/core';
import {TaskList} from "../../../model/TaskList/task-list";
import {TaskService} from "../../../Service/Task/task.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TaskListService} from "../../../Service/TaskList/task-list.service";

@Component({
  selector: 'app-edit-task-list',
  templateUrl: './edit-task-list.component.html',
  styleUrls: ['./edit-task-list.component.css']
})
export class EditTaskListComponent {

  taskList:TaskList={} as TaskList;

  constructor(private taskListService: TaskListService, private activatedRouter: ActivatedRoute, private router: Router) {}

ngOnInit() {
    this.getTaskList()
}

  getTaskList(){
    this.activatedRouter.paramMap.subscribe(params=>{
      this.taskList.id = params.get('id')!;
      this.taskListService.getTaskListById(this.taskList.id).subscribe(taskList => {
        this.taskList = taskList;
      });
  });

}
editTask(title:string, description:string){
      this.taskList.title = title;
      this.taskList.description = description;
      this.taskListService.updateTaskList(this.taskList.id,this.taskList).subscribe(taskList => {
        this.router.navigate(['/task-list',this.taskList.id]);
      })
}



}
