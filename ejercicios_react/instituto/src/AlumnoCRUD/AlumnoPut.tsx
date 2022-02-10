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


async function putAlumnoDb(alumno: Alumno) {
  if (alumno.nombre !== "" && alumno.id !== ""
  && alumno.apellidos !== "" && alumno.fechanacimiento !== 0) {
    await axios.put(rutaAlumnos + alumno.id, alumno)
      .then(function (response) {
        navigate('/alumnos/' + alumno.id);
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }
}

const getAlumno = React.useEffect(() => {
  const getAlumno = async (dnialumno: string | undefined) =>{
      let { data } = await axios.get(rutaAlumnos + dnialumno);
      setStatePut({alumnoPut: data});
      console.log(statePut.alumnoPut);
      }
  getAlumno(id);
}, [id]);

const putAlumno = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();

    const nombreAlumno = nombrealumno.current?.value;
    const apellidosAlumno = apellidosalumno.current?.value;
    const fechaNacimiento = fechanacimiento.current?.value;
    if (typeof nombreAlumno === "string" && typeof apellidosAlumno === "string"
    && typeof fechaNacimiento === "string") {
        let fechaNacimientoInt = new Date(fechaNacimiento).getTime();
        
        let alumno = statePut.alumnoPut;
        alumno.nombre = nombreAlumno;
        alumno.apellidos = apellidosAlumno;
        alumno.fechanacimiento = fechaNacimientoInt;

        putAlumnoDb(alumno);
    }
}
let fecha = new Date(statePut.alumnoPut.fechanacimiento);
let day = fecha.getUTCDate();
let month = fecha.getUTCMonth() + 1;
let year = fecha.getUTCFullYear();
return (
    <>
        <h3>Modificar una alumno</h3>
        <div>
          <span>Alumno a modificar:</span><br/>
          <span>Dni: {statePut.alumnoPut.id}</span>
          <span>Nombre: </span><input type="text" ref={nombrealumno} defaultValue={statePut.alumnoPut.nombre}></input><br/>
          <span>Apellidos: </span><input type="text" ref={apellidosalumno} defaultValue={statePut.alumnoPut.apellidos}></input><br/>
          <span>Fecha nacimiento: </span><input key={`${Math.floor((Math.random() * 1000))}-min`} type="text" ref={fechanacimiento} 
          placeholder="mm/dd/yyyy" defaultValue={day + "/" + month + "/" + year}></input><br/>
          <button onClick={putAlumno}>Modificar Alumno</button>
        </div>
    </>
);
}