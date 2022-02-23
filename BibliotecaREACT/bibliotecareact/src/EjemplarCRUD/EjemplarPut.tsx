import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Ejemplar } from '../Modelo/ejemplar';
import { EjemplarPost } from '../Modelo/ejemplarPost';
interface IState {
  ejemplarPut: EjemplarPost
}
export const EjemplarPut = () => {
  const ip = "localhost";
  const puerto = 8080;
  const { libroid, ejemplarid } = useParams();
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaEjemplars = rutaBase + "/libro/" + libroid + "/ejemplar/";
  const [statePut, setStatePut] = React.useState<IState>({ ejemplarPut: new EjemplarPost("") });
  const localizacionejemplar = React.useRef<HTMLInputElement>(null);
  const navigate = useNavigate();
  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };


  const getEjemplar = React.useEffect(() => {
    const getEjemplar = async (id: string | undefined) => {
      let { data } = await axios.get(rutaEjemplars + id, headers);
      setStatePut({ ejemplarPut: data });
    }
    getEjemplar(ejemplarid);
  }, [ejemplarid]);

  const putEjemplarAsync = async (ejemplar: EjemplarPost) => {
    if (ejemplar.localizacion !== "") {
      await axios.put(rutaEjemplars + ejemplarid, ejemplar, headers)
        .then(function (response) {
          setStatePut({ ejemplarPut: response.data })
          navigate(-1);
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }

  const putEjemplar = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const localizacionEjemplar = localizacionejemplar.current?.value;
    if (typeof localizacionEjemplar === "string") {
      let ejemplar = new EjemplarPost(localizacionEjemplar);
      putEjemplarAsync(ejemplar);
    }
  }
  return (
    <>
      <h3>Modificar un ejemplar</h3>
      <div>
        <span>Ejemplar a modificar:</span><br />
        <span>Localización: </span><input type="text" ref={localizacionejemplar} defaultValue={statePut.ejemplarPut.localizacion}></input><br />
        <button onClick={putEjemplar}>Modificar Ejemplar</button>
      </div>
    </>
  );
}