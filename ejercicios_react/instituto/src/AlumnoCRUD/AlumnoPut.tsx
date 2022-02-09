import axios from 'axios';
import React from 'react';
import { Alumno } from '../modelo/Alumno';
import { AlumnoComponent } from './AlumnoComponent';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
interface IState {
  alumnoPut: Alumno
}
export const AlumnosPut = () => {
const ip = "localhost";
const puerto = 8081;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaAlumnos = rutaBase + "/alumnos/";
const [statePut, setStatePut] = React.useState<IState>({alumnoPut: new Alumno("", "", "", 0, new Array())});
const nombrealumno = React.useRef<HTMLInputElement>(null);
const apellidosalumno = React.useRef<HTMLInputElement>(null);
const fechanacimiento = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();

const { id } = useParams();
const putAlumnoEffect = React.useEffect(() => {
    const putAlumno = async (alumno: Alumno) =>{
      if (alumno.nombre !== "" && alumno.id !== ""
      && alumno.apellidos !== "" && alumno.fechanacimiento !== 0) {
        await axios.put(rutaAlumnos + alumno.id, alumno)
          .then(function (response) {
            navigate('/alumnos');
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    console.log(statePut.alumnoPut);
    putAlumno(statePut.alumnoPut);
}, [statePut]);

const putAlumno = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    console.log("Algo pasa");

    const nombreAlumno = nombrealumno.current?.value;
    const apellidosAlumno = apellidosalumno.current?.value;
    const fechaNacimiento = fechanacimiento.current?.value;
    if (typeof nombreAlumno === "string" && typeof apellidosAlumno === "string"
    && typeof fechaNacimiento === "string") {
        console.log("Hemos entrado");
        let fechaNacimientoInt = new Date(fechaNacimiento).getTime();
        let dniAlumno = id || '';
        let alumno = new Alumno(dniAlumno, nombreAlumno, apellidosAlumno, fechaNacimientoInt, new Array());
        setStatePut({alumnoPut: alumno});
    }
}
return (
    <>
        <h3>Modificar una alumno</h3>
        <div>
          <span>Alumno a modificar:</span><br/>
          <span>Dni: {id}</span>
          <span>Nombre: </span><input type="text" ref={nombrealumno}></input><br/>
          <span>Apellidos: </span><input type="text" ref={apellidosalumno}></input><br/>
          <span>Fecha nacimiento: </span><input type="text" ref={fechanacimiento} placeholder="mm/dd/yyyy"></input><br/>
          <button onClick={putAlumno}>Introducir Alumno</button>
        </div>
    </>
);
}