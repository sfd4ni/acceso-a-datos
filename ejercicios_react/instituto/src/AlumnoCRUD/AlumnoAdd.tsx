import axios from 'axios';
import React from 'react';
import { Alumno } from '../modelo/Alumno';
import { AlumnoComponent } from './AlumnoComponent';
import { matchPath, Location, useParams } from 'react-router-dom';
interface IState {
  alumnoPost: Alumno
}
export const AlumnosPost = () => {
const ip = "localhost";
const puerto = 8081;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaAlumnos = rutaBase + "/alumnos";
const [statePost, setStatePost] = React.useState<IState>({alumnoPost: new Alumno("", "", "", 0, new Array())});
const nombrealumno = React.useRef<HTMLInputElement>(null);
const dnialumno = React.useRef<HTMLInputElement>(null);
const apellidosalumno = React.useRef<HTMLInputElement>(null);
const fechanacimiento = React.useRef<HTMLInputElement>(null);


const postAlumnoEffect = React.useEffect(() => {
    const postAlumno = async (alumno: Alumno) =>{
      if (alumno.nombre !== "" && alumno.dnialumno !== ""
      && alumno.apellidos !== "" && alumno.fechanacimiento !== 0) {
        await axios.post(rutaAlumnos, alumno)
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    console.log(statePost.alumnoPost);
    postAlumno(statePost.alumnoPost);
}, [statePost]);

const postAlumno = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    console.log("Algo pasa");
    const dniAlumno = dnialumno.current?.value;
    const nombreAlumno = nombrealumno.current?.value;
    const apellidosAlumno = apellidosalumno.current?.value;
    const fechaNacimiento = fechanacimiento.current?.value;
    if (typeof nombreAlumno === "string" && typeof dniAlumno === "string"
    && typeof apellidosAlumno === "string" && typeof fechaNacimiento === "string") {
        console.log("Hemos entrado");
        let fechaNacimientoInt = new Date(fechaNacimiento).getTime();
        let alumno = new Alumno(dniAlumno, nombreAlumno, apellidosAlumno, fechaNacimientoInt, new Array());
        setStatePost({alumnoPost: alumno});
    }
}
return (
    <>
        <h3>Crear una alumno</h3>
        <div>
          <span>Alumno a introducir:</span><br/>
          <span>Dni: </span><input type="text" ref={dnialumno}></input><br/>
          <span>Nombre: </span><input type="text" ref={nombrealumno}></input><br/>
          <span>Apellidos: </span><input type="text" ref={apellidosalumno}></input><br/>
          <span>Fecha nacimiento: </span><input type="text" ref={fechanacimiento} placeholder="mm/dd/yyyy"></input><br/>
          <button onClick={postAlumno}>Introducir Alumno</button>
        </div>
    </>
);
}