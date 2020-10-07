export class User {
    id: number;
    nome     :string;
    email    :string;
    password :string;
    profile  :string;
    favorite :boolean;
    image : any;

    constructor(nome:string,email:string,password:string,profile:string){
            this.nome     = nome,
            this.email    = email,
            this.password = password,
            this.profile  = profile
    }
}