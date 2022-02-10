import axios from 'axios';
import React from 'react';
import { Asignatura } from '../modelo/Asignatura';
import { AsignaturaComponent } from './AsignaturaComponent';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { AsignaturaSinId } from '../modelo/AsignaturaSinId';
interface IState {
  asignaturaPut: AsignaturaSinId
}
export const AsignaturasPut = () => {
const ip = "localhost";
const puerto = 8081;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaAsignaturas = rutaBase + "/asignaturas/";
const [statePut, setStatePut] = React.useState<IState>({asignaturaPut: new AsignaturaSinId("", "")});
const nombreasignatura = React.useRef<HTMLInputElement>(null);
const cursoasignatura = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();

const { id } = useParams();

const getAsignatura = React.useEffect(() => {
  const getAlumno = async (id: string | undefined) =>{
      let { data } = await axios.get(rutaAsignaturas + id);
      setStatePut({asignaturaPut: data});
      }
  getAlumno(id);
}, [id]);

async function putAsignaturaDb(asignatura: AsignaturaSinId) {
  if (asignatura.nombre !== "" && asignatura.curso !== "") {
    await axios.put(rutaAsignaturas + id, asignatura)
      .then(function (response) {
        navigate('/asignaturas');
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }
}


const putAsignatura = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    console.log("Algo pasa");
    const nombreAsignatura = nombreasignatura.current?.value;
    const cursoAsignatura = cursoasignatura.current?.value;
    if (typeof nombreAsignatura === "string" && typeof cursoAsignatura === "string") {
        let asignatura = new AsignaturaSinId(nombreAsignatura, cursoAsignatura);
        putAsignaturaDb(asignatura);
    }
}
return (
    <>
        <h3>Modificar una asignatura</h3>
        <div>
          <span>Asignatura a introducir:</span><br/>
          <span>Nombre: </span><input type="text" ref={nombreasignatura} defaultValue={statePut.asignaturaPut.nombre}></input><br/>
          <span>Curso: </span><input type="text" ref={cursoasignatura} defaultValue={statePut.asignaturaPut.curso}></input><br/>
          <button onClick={putAsignatura}>Introducir Asignatura</button>
        </div>
    </>
);
}