import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Moneda } from './Moneda';
interface IProps { }
interface IState {
    idmoneda: string
}
export const MonedasDelete = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaMonedas = rutaBase + "/monedas";
  const idmoneda = React.useRef<HTMLInputElement>(null);
  const [state, setState] = React.useState<IState>({idmoneda: "0"});

  const deleteMonedaEffect = React.useEffect(() => {
    
      const deleteMoneda = async (id: string) =>{
        if (state.idmoneda !== "0") {
          const idDelete = "/" + id;
          await axios.delete(rutaMonedas + idDelete)
            .then(function (response) {
              
              console.log(response);
            })
            .catch(function (error) {
              console.log(error);
            });
        }     
      }

      deleteMoneda(state?.idmoneda);
  }, [state]);

  const deleteMoneda = (event: React.MouseEvent<HTMLButtonElement>) =>  {
      event.preventDefault();
      const idMoneda = idmoneda.current?.value;
      if (typeof idMoneda === "string") {
          setState({idmoneda: idMoneda});
      }
  }
  return (
      <>
          <h3>Borrar una moneda</h3>
          <div>
            <span>Moneda a borrar:</span><br/>
            <span>id: </span><input type="text" ref={idmoneda}></input><br/>
            <button onClick={deleteMoneda}>Borrar</button>
          </div>
      </>
  );
}