import { Ejemplar } from "./ejemplar";

export class Libro {
  constructor(
    public libroid: number,
    public editorial: string,
    public titulo: string,
    public ejemplares: Array<Ejemplar>
  )
  {}
}