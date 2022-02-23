import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { AutorPost } from '../Modelo/autorPost';
interface IState {
  autorPost: AutorPost
}
export const AutorAdd = () => {
const ip = "localhost";
const puerto = 8080;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaAutors = rutaBase + "/autor";
const [statePost, setStatePost] = React.useState<IState>({autorPost: new AutorPost("", "", "")});
const nombreautor = React.useRef<HTMLInputElement>(null);
const nacionalidadautor = React.useRef<HTMLInputElement>(null);
const apellidosautor = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();
const token = localStorage.getItem("token") as string;
const headers = {
  headers: { Authorization: token }
};


const postAutorEffect = React.useEffect(() => {
    const postAutor = async (autor: AutorPost) =>{
      if (autor.nombre !== "" && autor.nacionalidad !== ""
      && autor.apellidos !== "") {
        await axios.post(rutaAutors, autor, headers)
          .then(function (response) {
            navigate(-1);
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    postAutor(statePost.autorPost);
}, [statePost]);

const postAutor = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    const nacionalidadAutor = nacionalidadautor.current?.value;
    const nombreAutor = nombreautor.current?.value;
    const apellidosAutor = apellidosautor.current?.value;
    if (typeof nombreAutor === "string" && typeof nacionalidadAutor === "string"
    && typeof apellidosAutor === "string") {
        let autor = new AutorPost(apellidosAutor, nacionalidadAutor, nombreAutor);
        setStatePost({autorPost: autor});
    }
}
return (
    <>
        <h3>Crear un autor</h3>
        <div>
          <span>Autor a introducir:</span><br/>
          <span>Nacionalidad: </span><input type="text" ref={nacionalidadautor}></input><br/>
          <span>Nombre: </span><input type="text" ref={nombreautor}></input><br/>
          <span>Apellidos: </span><input type="text" ref={apellidosautor}></input><br/>
          <button onClick={postAutor}>Introducir Autor</button>
        </div>
    </>
);
}