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

  private getAuthHeaders() {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    return {
      headers: {
        Authorization: `Bearer ${user.token}`,
      },
    };
  }

  addTask(taskAddDetails: TaskAddDetails): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/add-task`,
      taskAddDetails,
      this.getAuthHeaders()
    );
  }

  updateTask(id: number, taskUpdateDetails: TaskAddDetails): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/update-task?id=${id}`,
      taskUpdateDetails,
      this.getAuthHeaders()
    );
  }

  deleteTask(id: number): Observable<any> {
    return this.http.delete(
      `${this.apiUrl}/delete-task?id=${id}`,
      this.getAuthHeaders()
    );
  }

  getTaskById(id: number): Observable<any> {
    return this.http.get(
      `${this.apiUrl}/get-task-by-id?id=${id}`,
      this.getAuthHeaders()
    );
  }

  getTasks(id: number, page: number): Observable<any> {
    return this.http.get(
      `${this.apiUrl}/get-tasks?id=${id}&page=${page}`,
      this.getAuthHeaders()
    );
  }
}
