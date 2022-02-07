import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Moneda } from './Moneda';
interface IProps { }
interface IState {
    monedaPost: Moneda
}
export const MonedasPost = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaMonedas = rutaBase + "/monedas";
  const [statePost, setStatePost] = React.useState<IState>({monedaPost: new Moneda("", "")});
  const nombremoneda = React.useRef<HTMLInputElement>(null);
  const paismoneda = React.useRef<HTMLInputElement>(null);


  const postMonedaEffect = React.useEffect(() => {
      const postMoneda = async (moneda: Moneda) =>{
        if (moneda.nombre !== "" && moneda.pais !== "") {
          await axios.post(rutaMonedas, moneda)
            .then(function (response) {
              console.log(response);
            })
            .catch(function (error) {
              console.log(error);
            });
        }
        }

      postMoneda(statePost.monedaPost);
  }, [statePost]);

  const postMoneda = (event: React.MouseEvent<HTMLButtonElement>) =>  {
      event.preventDefault();
      const nombreMoneda = nombremoneda.current?.value;
      const paisMoneda = paismoneda.current?.value;
      if (typeof nombreMoneda === "string" && typeof paisMoneda === "string") {
          
          let moneda = new Moneda(nombreMoneda, paisMoneda);
          setStatePost({monedaPost: moneda});
      }
  }
  return (
      <>
          <h3>Crear una moneda</h3>
          <div>
            <span>Moneda a introducir:</span><br/>
            <span>nombre: </span><input type="text" ref={nombremoneda}></input><br/>
            <span>pais: </span><input type="text" ref={paismoneda}></input><br/>
            <button onClick={postMoneda}>Introducir Moneda</button>
          </div>
      </>
  );
}