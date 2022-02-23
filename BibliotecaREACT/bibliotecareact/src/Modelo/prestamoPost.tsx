import { Ejemplar } from "./ejemplar";

export class PrestamoPost {
  constructor(
      public ejemplar: Ejemplar,
      public fechaprestamo: Date,
      public fechadevolucion: Date
  )
  {}
}