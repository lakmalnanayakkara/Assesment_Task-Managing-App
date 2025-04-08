import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TaskService } from '../service/task.service';

@Component({
  selector: 'app-list-task',
  standalone: false,
  templateUrl: './list-task.component.html',
  styleUrl: './list-task.component.css',
})
export class ListTaskComponent {
  isLoading = false;
  isError = false;
  message = '';
  isSuccess = false;
  items = [];
  id: number;
  page: number = 0;
  total: number;

  public subscriptions: Subscription = new Subscription();

  constructor(private taskService: TaskService, private router: Router) {}

  ngOnInit() {
    this.isLoading = true;
    const user = JSON.parse(localStorage.getItem('userInfo'));
    console.log(user);

    if (!user) {
      this.router.navigate(['sign-in']);
      this.isLoading = false;
    } else {
      this.id = user.userId;
      this.getTaskList();
    }
  }

  changeIsError(newIsError: boolean) {
    this.isError = newIsError;
  }

  getTaskList() {
    const getSub = this.taskService.getTasks(this.id, this.page).subscribe(
      (data) => {
        this.isLoading = false;
        this.items = data.data.tasks;
        this.total = data.data.total;
      },
      (error) => {
        this.isLoading = false;
        this.isError = true;
        this.message = error.error.data;
      }
    );
    this.subscriptions.add(getSub);
  }

  deleteTask(id: number) {
    this.isLoading = true;
    const deleteSub = this.taskService.deleteTask(id).subscribe(
      (data) => {
        this.isLoading = false;
        this.getTaskList();
        this.router.navigate(['']);
      },
      (error) => {
        this.isLoading = false;
        this.isError = true;
        this.message = error.error.data;
      }
    );
    this.subscriptions.add(deleteSub);
  }

  updateTask(id: number) {
    this.router.navigate([`/update-task/${id}`]);
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  getTotalPages(): number {
    return Math.ceil(this.total / 5);
  }

  prevPage() {
    if (this.page > 0) {
      this.page -= 1;
      this.getTaskList();
    }
    console.log(this.page);
  }

  nextPage() {
    if (this.getTotalPages() > this.page + 1) {
      this.page += 1;
      this.getTaskList();
    }
    console.log(this.page + 1 !== this.getTotalPages());
  }
}
