import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Alumno } from '../modelo/Alumno';
import { Matricula } from '../modelo/Matricula';
import { Link, Route, BrowserRouter, Routes, useNavigate } from 'react-router-dom';
interface IProps {
  alumno: Alumno
 }
 
export const AlumnoComponent = (props: IProps) => {
  const { alumno } = props;
  const fechaNacimiento = new Date(alumno.fechanacimiento);
  const day = fechaNacimiento.getUTCDate();
  const month = fechaNacimiento.getUTCMonth() + 1;
  const year = fechaNacimiento.getFullYear();
  const navigate = useNavigate();
  const [pulsado, setPulsado] = React.useState(false);
    const ip = "localhost";
    const puerto = 8081  ;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/alumnos/";
    React.useEffect(() => {
        const eliminarAlumno = async (dnialumno: string | undefined) =>{
            if (pulsado) {
                console.log(rutaBase + dnialumno);
                await axios.delete(rutaBase + dnialumno)
                    .then(function (response) {
                        navigate('/alumnos');
                    })
                    .catch(function (error) {
                        console.log(error);
                });
            }
        }
        eliminarAlumno(alumno.id);
    }, [pulsado]);
    function eliminarAlumno(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        setPulsado(true);
    }
    function modificarAlumno(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        navigate('put');
    }
    return (
        <>
            <h3>{alumno.id}</h3>
            <div>
                <span>Nombre: {alumno.nombre}</span><br/>
                <span>Apellidos: {alumno.apellidos} </span><br/>
                <span>Fecha nacimiento: {day}/{month}/{year} </span><br/>
                <h4>Matriculas: </h4>
                <ul>
                {alumno.matriculas?.map((matricula: Matricula) => {
                        return (
                            <li><Link to={'matriculas/' + matricula.id}>Curso {matricula.year-1}/{matricula.year}</Link></li>
                        );
                    })
                    }
                </ul>
                <button onClick={eliminarAlumno}>Eliminar alumno</button>
                <button onClick={modificarAlumno}>Modificar alumno</button>
            </div>
        </>
    );
}