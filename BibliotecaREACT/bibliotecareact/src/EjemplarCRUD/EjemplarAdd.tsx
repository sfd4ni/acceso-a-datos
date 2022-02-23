import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Ejemplar } from '../Modelo/ejemplar';
import { EjemplarPost } from '../Modelo/ejemplarPost';
interface IState {
  ejemplarPost: EjemplarPost
}
export const EjemplarAdd = () => {
const ip = "localhost";
const puerto = 8080;
const { libroid, ejemplarid } = useParams();
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaEjemplars = rutaBase + "/libro/" + libroid + "/ejemplar";
const [statePost, setStatePost] = React.useState<IState>({ejemplarPost: new EjemplarPost("")});
const localizacionejemplar = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();

const token = localStorage.getItem("token") as string;
const headers = {
  headers: { Authorization: token }
};

const postEjemplarEffect = React.useEffect(() => {
    const postEjemplar = async (ejemplar: EjemplarPost) =>{
      if (ejemplar.localizacion !== "") {
        await axios.post(rutaEjemplars, ejemplar, headers)
          .then(function (response) {
            navigate(-1);
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    postEjemplar(statePost.ejemplarPost);
}, [statePost]);

const postEjemplar = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    const localizacionEjemplar = localizacionejemplar.current?.value;
    if (typeof localizacionEjemplar === "string") {
        let ejemplar = new EjemplarPost(localizacionEjemplar);
        setStatePost({ejemplarPost: ejemplar});
    }
}
return (
    <>
        <h3>Crear una ejemplar</h3>
        <div>
          <span>Ejemplar a introducir:</span><br/>
          <span>Localización: </span><input type="text" ref={localizacionejemplar}></input><br/>
          <button onClick={postEjemplar}>Introducir Ejemplar</button>
        </div>
    </>
);
}