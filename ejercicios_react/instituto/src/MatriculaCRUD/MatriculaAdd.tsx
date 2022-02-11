import axios from 'axios';
import React, { useState } from 'react';
import { MatriculaSinId } from '../modelo/MatriculaSinId';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Alumno } from '../modelo/Alumno';
import { Asignatura } from '../modelo/Asignatura';
import { string } from 'yargs';
interface IState {
  matriculaPost: MatriculaSinId
}
interface IAsignaturasState {
  asignaturas: Array<Asignatura>;
}
export const MatriculasPost = () => {
const ip = "localhost";
const puerto = 8081;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaMatriculas = rutaBase + "/matriculas";

const [checked, setChecked] = useState({checked: new Array<String>()});


const { dnialumno } = useParams();
let dniAlumno = dnialumno || "";
const [statePost, setStatePost] = React.useState<IState>({matriculaPost: new MatriculaSinId(0, new Array(), dniAlumno)});
const [stateAsignaturas, setAsignatura] = React.useState<IAsignaturasState>({asignaturas: new Array<Asignatura>()});
const rutaAsignaturas = rutaBase + "/asignaturas";



React.useEffect(() => {
  const getAsignaturas = async () =>{
    let respuesta = await axios.get(rutaAsignaturas);
    setAsignatura({ asignaturas: respuesta.data });
  }
  getAsignaturas();
}, [statePost]);


//const [stateAlumno, setStateAlumno] = React.useState<IAlumnoState>({alumno: new Alumno("", "", "", 0, [])});
//const añomatricula = React.useRef<HTMLInputElement>(null);

const navigate = useNavigate();

const postMatriculaEffect = React.useEffect(() => {
    const postMatricula = async (matricula: MatriculaSinId) =>{
      if (matricula.year !== 0 && matricula.dnialumno !== "") {
        await axios.post(rutaMatriculas, matricula)
          .then(function (response) {
            navigate(-1);
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    postMatricula(statePost.matriculaPost);
}, [statePost]);

const postMatricula = (event:React.FormEvent<HTMLFormElement>) =>  {
    event.preventDefault();

    let formulario: HTMLFormElement = event.currentTarget;

    const añoMatricula = formulario.añoMatricula.value;

    /*stateAsignaturas.asignaturas.map( (asignatura: Asignatura) => {
      if (formulario.)
    }

    )*/

    let asignaturasAdd = new Array<Asignatura>();

    stateAsignaturas.asignaturas.map((asignatura : Asignatura) => {
      console.log(checked.checked);
      if (checked.checked.indexOf(asignatura.nombre) !== -1) {
        asignaturasAdd.push(asignatura);
      }
    });

    console.log(asignaturasAdd);

    if (typeof añoMatricula === "string") {
        let matricula = statePost.matriculaPost;
        let year = parseInt(añoMatricula);
        matricula.year = year;
        matricula.asignaturas = asignaturasAdd;
        setStatePost({matriculaPost: matricula});
    }
}

const handleCheck = (event: React.ChangeEvent<HTMLInputElement>) => {
  let updatedList = [...checked.checked];
  if (event.target.checked) {
    updatedList = [...checked.checked, event.target.id];
  } else {
    updatedList.splice(checked.checked.indexOf(event.target.id), 1);
  }
  setChecked({checked: updatedList});
};

return (
    <>
        <h3>Crear una matricula</h3>
        <div>
          <span>Matricula a introducir:</span><br/>
          
          <form onSubmit={postMatricula}>
          <span>Año: </span><input type="text" id="añoMatricula"></input><br/>
          {stateAsignaturas.asignaturas?.map((asignatura: Asignatura) => {
            return (
                <>
                <label>
                  <input type="checkbox" id={asignatura.nombre} onChange={handleCheck}/>
                  {asignatura.nombre}
                </label>
                <br/>
                </>
                );
            })
        }
          <button type='submit'>Introducir Matricula</button>
          </form>
        </div>
    </>
);
}