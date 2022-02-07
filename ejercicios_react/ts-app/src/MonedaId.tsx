export class MonedaId {
  constructor(
      public idmoneda: number,
      public nombre: string ,
      public pais: string,
      public historicos: Array<any>
  ){}
  
}