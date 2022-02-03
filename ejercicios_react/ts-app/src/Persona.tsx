export class Persona {
    constructor(
        public id: number,
        public nombre: string ,
        public apellido: string, 
        public altura: number ,
        public edad: number,
        public peso: number
        
    ){}


    getIMC() {
        if (this.altura !== 0) {
            console.log(this.peso / (Math.pow(this.altura, 2)));
            return this.peso / (Math.pow(this.altura, 2));
        }
        return 0;
    }

}