import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskTrackerHomeComponent } from './task-tracker-home.component';

describe('TaskTrackerHomeComponent', () => {
  let component: TaskTrackerHomeComponent;
  let fixture: ComponentFixture<TaskTrackerHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskTrackerHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskTrackerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
