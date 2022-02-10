import axios from 'axios';
import React from 'react';
import { Asignatura } from '../modelo/Asignatura';
import { AsignaturaComponent } from './AsignaturaComponent';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { AsignaturaSinId } from '../modelo/AsignaturaSinId';
interface IState {
  asignaturaPost: AsignaturaSinId
}
export const AsignaturasPost = () => {
const ip = "localhost";
const puerto = 8081;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaAsignaturas = rutaBase + "/asignaturas";
const [statePost, setStatePost] = React.useState<IState>({asignaturaPost: new AsignaturaSinId("", "")});
const nombreasignatura = React.useRef<HTMLInputElement>(null);
const cursoasignatura = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();



const postAsignaturaEffect = React.useEffect(() => {
    const postAsignatura = async (asignatura: AsignaturaSinId) =>{
      if (asignatura.nombre !== "" && asignatura.curso !== "") {
        await axios.post(rutaAsignaturas, asignatura)
          .then(function (response) {
            navigate('/asignaturas');
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    console.log(statePost.asignaturaPost);
    postAsignatura(statePost.asignaturaPost);
}, [statePost]);

const postAsignatura = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    console.log("Algo pasa");
    const nombreAsignatura = nombreasignatura.current?.value;
    const cursoAsignatura = cursoasignatura.current?.value;
    if (typeof nombreAsignatura === "string" && typeof cursoAsignatura === "string") {
        let asignatura = new AsignaturaSinId(nombreAsignatura, cursoAsignatura);
        setStatePost({asignaturaPost: asignatura});
    }
}
return (
    <>
        <h3>Crear una asignatura</h3>
        <div>
          <span>Asignatura a introducir:</span><br/>
          <span>Nombre: </span><input type="text" ref={nombreasignatura}></input><br/>
          <span>Curso: </span><input type="text" ref={cursoasignatura}></input><br/>
          <button onClick={postAsignatura}>Introducir Asignatura</button>
        </div>
    </>
);
}