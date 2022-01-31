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
        return this.peso / (Math.pow(this.altura, 2));
    }

}