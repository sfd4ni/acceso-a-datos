import { Matricula } from './Matricula';
export class Alumno {
    constructor(
        public id: string,
        public nombre: string,
        public apellidos: string,
        public fechanacimiento: number,
        public matriculas: Array<Matricula>
    )
    {}
}