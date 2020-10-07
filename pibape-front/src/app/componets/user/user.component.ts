import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { MatDialog} from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { User } from './../../model/User';
import { UserDetalheComponent } from '../user-detalhe/user-detalhe.component';
import { PageEvent } from '@angular/material/paginator';



interface Perfil {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  
  formulario : FormGroup;
  
  users : User [] = [];

  colunas = ['photo','id','nome','email','password','profile','favorite'];

  totalElements = 0;
  page = 0;
  sizePage = 10;
  pageSizeOptions: number[] = [10];


  profiles: Perfil[] = [
    {value: 'ROLE_ADMIN', viewValue:'Administrator'},
    {value: 'ROLE_CUSTOMER', viewValue:'Customer'},
    {value: 'ROLE_TECHNICIAN', viewValue:'Technician'}
  ]

  constructor(
    private userService : UserService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.montarFormulario();
    this.list(this.page,this.sizePage);  
  }


  montarFormulario(){
    this.formulario = this.fb.group({
      nome : ['', Validators.required],
      email: ['', Validators.email],
      password: ['', Validators.required],
      profile: ['', Validators.required]
    })
  }

  sumit(){
    const formValues = this.formulario.value;
    const user : User = new User(formValues.nome,formValues.email,formValues.password,formValues.profile);

    this.userService.save(user).subscribe( user => {
      this.list(this.page,this.sizePage);
    });
  }

  list(page = 0,sizePage = 0) {
    this.userService.list(page,sizePage).subscribe ( listUserResponse => {
      this.users = listUserResponse.content;
      this.totalElements = listUserResponse.totalElements;
      this.page = listUserResponse.number
    });
  }


  favoritar(user:User){
    this.userService.favourite(user).subscribe( response =>{
      user.favorite = !user.favorite;
    })
  }

  uploadPhoto (event, user){
    const files = event.target.files;
    if(files){
      const photo = files[0];
      const formData: FormData = new FormData();
      formData.append("photo",photo);
      this.userService.upload(user,formData)
      .subscribe(response => this.list(this.page,this.sizePage));

    }

  }

  viewDialog(user:User){
      this.dialog.open (
        UserDetalheComponent, {
          width:'350px',
          height:'550px',
          data: user
        }
      )
  }

  paginador(event: PageEvent){
      this.page = event.pageIndex;
      this.list(this.page,this.sizePage)
  }

}
