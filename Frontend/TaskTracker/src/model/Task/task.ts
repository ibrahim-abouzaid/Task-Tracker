export class Task {
  id!: string;
  title!: string;
  description!: string;
  dueDate?: string;
  priority!: 'LOW' | 'MEDIUM' | 'HIGH';
  status!: boolean;


}

