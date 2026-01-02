import { TestBed } from '@angular/core/testing';

import { TaskListServiceService } from './task-list.service';

describe('TaskListServiceService', () => {
  let service: TaskListServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskListServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
