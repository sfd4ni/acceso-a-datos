import { Autor } from "./autor";
import { Ejemplar } from "./ejemplar";

export class LibroPost {
  constructor(
    public editorial: string,
    public titulo: string,
    public autores: Array<Autor>
  )
  {}
}