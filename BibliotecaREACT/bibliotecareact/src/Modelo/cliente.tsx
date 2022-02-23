import { Prestamo } from "./prestamo";

export class Cliente {
  constructor(
      public clienteid: number,
      public apellidos: string,
      public direccion: string,
      public nombre: string,
      public prestamos: Array<Prestamo>
  )
  {}
}