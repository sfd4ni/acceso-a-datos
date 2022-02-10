import axios from 'axios';
import React from 'react';
import { MatriculaSinId } from '../modelo/MatriculaSinId';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Alumno } from '../modelo/Alumno';
interface IState {
  matriculaPost: MatriculaSinId
}
interface IAlumnoState {
  alumno: Alumno
}
export const MatriculasPost = () => {
const ip = "localhost";
const puerto = 8081;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaMatriculas = rutaBase + "/matriculas";
const rutaAlumnos = rutaBase + "/alumnos/";

const { dnialumno } = useParams();
let dniAlumno = dnialumno || "";
const [statePost, setStatePost] = React.useState<IState>({matriculaPost: new MatriculaSinId(0, new Array(), dniAlumno)});
//const [stateAlumno, setStateAlumno] = React.useState<IAlumnoState>({alumno: new Alumno("", "", "", 0, [])});
const añomatricula = React.useRef<HTMLInputElement>(null);

const navigate = useNavigate();

const postMatriculaEffect = React.useEffect(() => {
    const postMatricula = async (matricula: MatriculaSinId) =>{
      if (matricula.year !== 0 && matricula.dnialumno !== "") {
        await axios.post(rutaMatriculas, matricula)
          .then(function (response) {
            navigate('../');
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    postMatricula(statePost.matriculaPost);
}, [statePost]);

const postMatricula = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();


    const añoMatricula = añomatricula.current?.value;

    if (typeof añoMatricula === "string") {
        let matricula = statePost.matriculaPost;
        let year = parseInt(añoMatricula);
        matricula.year = year;
        setStatePost({matriculaPost: matricula});
    }
}
return (
    <>
        <h3>Crear una matricula</h3>
        <div>
          <span>MatriculaSinId a introducir:</span><br/>
          <span>Año: </span><input type="text" ref={añomatricula}></input><br/>
          
          <button onClick={postMatricula}>Introducir MatriculaSinId</button>
        </div>
    </>
);
}