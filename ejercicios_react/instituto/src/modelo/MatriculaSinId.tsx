import { Asignatura } from "./Asignatura";

export class MatriculaSinId {
    constructor(
        public year: number,
        public asignaturas: Array<Asignatura>,
        public dnialumno: string
    ){}
}