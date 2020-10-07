import { Component, OnInit, Inject } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog'
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-user-detalhe',
  templateUrl: './user-detalhe.component.html',
  styleUrls: ['./user-detalhe.component.css']
})
export class UserDetalheComponent implements OnInit {

  constructor(
    public dialogRef:MatDialogRef<UserDetalheComponent>,
    @Inject(MAT_DIALOG_DATA) public user: User
  ) { }

  ngOnInit(): void {
  }


  fechar(){
    this.dialogRef.close();
  }

}
