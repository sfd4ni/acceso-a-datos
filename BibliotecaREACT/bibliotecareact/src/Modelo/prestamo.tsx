import { Ejemplar } from "./ejemplar";

export class Prestamo {
  constructor(
      public prestamoid: number,
      public ejemplar: Ejemplar,
      public fechaprestamo: Date,
      public fechadevolucion: Date
  )
  {}
}