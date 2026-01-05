import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {TaskTrackerHomeComponent} from "./components/task-tracker-home/task-tracker-home.component";
import {CreateTaskListComponent} from "./components/create-task-list/create-task-list.component";
import {TaskListDetailsComponent} from "./components/task-list-details/task-list-details.component";
import {AddTaskComponent} from "./components/add-task/add-task.component";
import {EditTaskListComponent} from "./components/edit-task-list/edit-task-list.component";
import {EditTaskComponent} from "./components/edit-task/edit-task.component";


const routes: Routes = [
  { path: '', redirectTo: 'task-lists', pathMatch: 'full' },
  { path: 'task-lists', component: TaskTrackerHomeComponent },
  { path: 'create-task-list', component: CreateTaskListComponent },
  { path: 'task-list/:id', component: TaskListDetailsComponent },
  { path: 'task-list/:id/new-task', component: AddTaskComponent },
  { path: 'task-list/:id/update-task-list', component: EditTaskListComponent },
  { path: 'task-list/:id/task/:taskId', component: EditTaskComponent },

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
