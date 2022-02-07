import { Matricula } from './Matricula';
export class Alumno {
    constructor(
        public dnialumno: string,
        public nombre: string,
        public apellidos: string,
        public fechanacimiento: number,
        public matriculas: Array<Matricula>
    )
    {}
}