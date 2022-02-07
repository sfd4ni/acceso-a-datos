import { Asignatura } from "./Asignatura";

export class Matricula {
    constructor(
        public id: number,
        public year: number,
        public asignaturas: Array<Asignatura>,
        public dnialumno: string
    ){}
}