import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskAddDetails } from '../shared/interface/task.interface';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private apiUrl = 'http://localhost:8080/api/v1/task';

  constructor(private http: HttpClient) {}

  addTask(taskAddDetails: TaskAddDetails): Observable<any> {
    return this.http.post(`${this.apiUrl}/add-task`, taskAddDetails);
  }

  updateTask(id: number, taskUpdateDetails: TaskAddDetails): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/add-task?id=${id}`,
      taskUpdateDetails
    );
  }

  deleteTask(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete-task?id=${id}`);
  }

  getTaskById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/get-task-by-id?id=${id}`);
  }

  getTasks(id: number, page: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/get-tasks?id=${id}&page=${page}`);
  }
}
