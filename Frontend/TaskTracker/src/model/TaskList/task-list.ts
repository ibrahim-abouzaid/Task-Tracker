import {Task} from "../Task/task";

export class TaskList {
  id!: string;              // UUID as string
  title!: string;
  description?: string;
  Count?: number;
  progress!: number;
  tasks: Task[] = [];


}
