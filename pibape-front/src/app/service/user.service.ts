import { PageUser } from './../componets/user/PageUser';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './../model/User';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = environment.apiBaseUrl;
  constructor(
    private http: HttpClient
  ) { }

  save(user: User) : Observable<User> {
    return this.http.post<User>(this.url,user);
  }

  list(page,sizePage) : Observable<PageUser> {

    const params = new HttpParams()
    .set('page',page)
    .set('sizePage',sizePage);

    return this.http.get<any>(`${this.url}?${params.toString()}`);
  }

  favourite (user: User) : Observable<any> {
    return this.http.patch(`${this.url}/${user.id}/favorite`,null);
  }

  upload(user:User, formData:FormData) : Observable<any >{
    return this.http.put(`${this.url}/${user.id}/photo`,formData,{ responseType : 'blob'});
  }
}
