import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { AutorPost } from '../Modelo/autorPost';
interface IState {
  autorPut: AutorPost
}
export const AutorPut = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaAutores = rutaBase + "/autor/";
  const [statePut, setStatePut] = React.useState<IState>({ autorPut: new AutorPost("", "", "") });
  const nombreautor = React.useRef<HTMLInputElement>(null);
  const nacionalidadautor = React.useRef<HTMLInputElement>(null);
  const apellidosautor = React.useRef<HTMLInputElement>(null);
  const navigate = useNavigate();
  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };

  const { autorid } = useParams();


  const putAutorAsync = async (autor: AutorPost) => {
    if (autor.nombre !== "" && autor.nacionalidad !== ""
      && autor.apellidos !== "") {
      await axios.put(rutaAutores + autorid, autor, headers)
        .then(function (response) {
          navigate(-1);
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }

  const getAutor = React.useEffect(() => {
    const getAlumno = async (id: string | undefined) => {
      let { data } = await axios.get(rutaAutores + id, headers);
      setStatePut({ autorPut: data });
    }
    getAlumno(autorid);
  }, [autorid]);



  const putAutor = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const nacionalidadAutor = nacionalidadautor.current?.value;
    const nombreAutor = nombreautor.current?.value;
    const apellidosAutor = apellidosautor.current?.value;
    if (typeof nombreAutor === "string" && typeof nacionalidadAutor === "string"
      && typeof apellidosAutor === "string") {
      let autor = new AutorPost(apellidosAutor, nacionalidadAutor, nombreAutor);
      putAutorAsync(autor);
    }
  }
  return (
    <>
      <h3>Modificar un autor</h3>
      <div>
        <span>Autor a modificar:</span><br />
        <span>Nacionalidad: </span><input type="text" ref={nacionalidadautor} defaultValue={statePut.autorPut.nacionalidad}></input><br />
        <span>Nombre: </span><input type="text" ref={nombreautor} defaultValue={statePut.autorPut.nombre}></input><br />
        <span>Apellidos: </span><input type="text" ref={apellidosautor} defaultValue={statePut.autorPut.apellidos}></input><br />
        <button onClick={putAutor}>Modificar Autor</button>
      </div>
    </>
  );
}