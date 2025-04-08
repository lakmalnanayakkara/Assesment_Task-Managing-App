import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TaskService } from '../service/task.service';
import { TaskAddDetails } from '../shared/interface/task.interface';

@Component({
  selector: 'app-add-task',
  standalone: false,
  templateUrl: './add-task.component.html',
  styleUrl: './add-task.component.css',
})
export class AddTaskComponent {
  readonly startDate = new Date();
  isLoading = false;
  isError = false;
  message = '';
  isSuccess = false;

  taskId: number = undefined;

  public subscriptions: Subscription = new Subscription();

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  addTaskForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    status: new FormControl('', [Validators.required]),
    created: new FormControl<Date | null>(null, [Validators.required]),
    description: new FormControl('', [Validators.required]),
  });

  statusTypes: { value: string; viewValue: string }[] = [
    { value: 'TO_DO', viewValue: 'To Do' },
    { value: 'DONE', viewValue: 'Done' },
    { value: 'IN_PROGRESS', viewValue: 'In Progress' },
  ];

  ngOnInit() {
    this.isLoading = true;
    this.taskId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.taskId) {
      const updateSub = this.taskService.getTaskById(this.taskId).subscribe(
        (data) => {
          this.isLoading = false;
          this.addTaskForm.controls.title.setValue(data.data.title);
          this.addTaskForm.controls.status.setValue(data.data.status);
          this.addTaskForm.controls.created.setValue(
            new Date(data.data.created)
          );
          this.addTaskForm.controls.description.setValue(data.data.description);
        },
        (error) => {
          this.isLoading = false;
          this.isError = true;
        }
      );
      this.subscriptions.add(updateSub);
    } else {
      this.isLoading = false;
    }
  }

  onSubmit() {
    this.isError = false;
    this.isSuccess = false;
    this.isLoading = true;

    const data: TaskAddDetails = {
      title: this.addTaskForm.controls.title.value,
      status: this.addTaskForm.controls.status.value,
      created: this.addTaskForm.controls.created.value
        .toISOString()
        .slice(0, 19),
      description: this.addTaskForm.controls.description.value,
      userId: JSON.parse(localStorage.getItem('userInfo')).userId,
    };

    if (this.taskId) {
      const sub = this.taskService.updateTask(this.taskId, data).subscribe(
        (data) => {
          this.isLoading = false;
          this.isSuccess = true;
          this.isError = false;
          this.message = `${data.data.title} task updated successfully.`;
          this.addTaskForm.reset();
          this.router.navigate(['']);
        },
        (error) => {
          this.isLoading = false;
          this.isSuccess = false;
          this.isError = true;
          this.message = error.error.data;
        }
      );
      this.subscriptions.add(sub);
    } else {
      const sub = this.taskService.addTask(data).subscribe(
        (data) => {
          this.isLoading = false;
          this.isSuccess = true;
          this.isError = false;
          this.message = `${data.data.taskTitle} task added successfully.`;
          this.addTaskForm.reset();
        },
        (error) => {
          this.isLoading = false;
          this.isSuccess = false;
          this.isError = true;
          this.message = error.error.data;
        }
      );
      this.subscriptions.add(sub);
    }
  }

  changeIsError(newIsError: boolean) {
    this.isError = newIsError;
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
