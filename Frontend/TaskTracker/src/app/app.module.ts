import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TaskTrackerHomeComponent } from './components/task-tracker-home/task-tracker-home.component';
import { CreateTaskListComponent } from './components/create-task-list/create-task-list.component';
import { TaskListDetailsComponent } from './components/task-list-details/task-list-details.component';
import { AppRoutingModule } from './app-routing.module';
import {RouterLink, RouterOutlet} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { AddTaskComponent } from './components/add-task/add-task.component';
import { EditTaskListComponent } from './components/edit-task-list/edit-task-list.component';
import { EditTaskComponent } from './components/edit-task/edit-task.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskTrackerHomeComponent,
    CreateTaskListComponent,
    TaskListDetailsComponent,
    AddTaskComponent,
    EditTaskListComponent,
    EditTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterOutlet,
    RouterLink,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
