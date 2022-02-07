import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Moneda } from './Moneda';
interface IProps { }
interface IState {
    monedaPut: Moneda,
    idmoneda: string
}
export const MonedasPut = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaMonedas = rutaBase + "/monedas";
  const [state, setState] = React.useState<IState>({idmoneda: "0", monedaPut: new Moneda("", "")});
  const idmoneda = React.useRef<HTMLInputElement>(null);
  const nombremoneda = React.useRef<HTMLInputElement>(null);
  const paismoneda = React.useRef<HTMLInputElement>(null);


  const putMonedaEffect = React.useEffect(() => {
      const putMoneda = async (moneda: Moneda, idmoneda: string) =>{
        const idPut = "/" + idmoneda;
        if (moneda.nombre !== "" && moneda.pais !== "") {
          await axios.put(rutaMonedas + idPut, moneda)
            .then(function (response) {
              
              console.log(response);
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      }

      putMoneda(state.monedaPut, state.idmoneda);
  }, [state]);

  const putMoneda = (event: React.MouseEvent<HTMLButtonElement>) =>  {
      event.preventDefault();
      const idMoneda = idmoneda.current?.value;
      const nombreMoneda = nombremoneda.current?.value;
      const paisMoneda = paismoneda.current?.value;
      console.log(nombreMoneda, paisMoneda);
      console.log("Hola");
      if (typeof idMoneda === "string" && typeof  nombreMoneda ==="string" && typeof paisMoneda === "string") {
          
          let moneda = new Moneda(nombreMoneda, paisMoneda);
          setState({monedaPut: moneda, idmoneda: idMoneda});
      }
  }
  return (
      <>
          <h3>Modificar una moneda</h3>
          <div>
            <span>Moneda a modificar:</span><br/>
            <span>id: </span><input type="text" ref={idmoneda}></input><br/>
            <span>nombre: </span><input type="text" ref={nombremoneda}></input><br/>
            <span>pais: </span><input type="text" ref={paismoneda}></input><br/>
            <button onClick={putMoneda}>Modificar Moneda</button>
          </div>
      </>
  );
}