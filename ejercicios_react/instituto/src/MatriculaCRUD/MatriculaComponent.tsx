import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Asignatura } from '../modelo/Asignatura';
import { Matricula } from '../modelo/Matricula';
interface IProps {
    matricula: Matricula
}

export const MatriculaComponent = (props: IProps) => {
    const { matricula } = props;
    const navigate = useNavigate();
    const ip = "localhost";
    const puerto = 8081  ;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/matriculas/";
    
    async function eliminarMatriculaDb() {
        await axios.delete(rutaBase + matricula.id)
        .then(function (response) {
            navigate(-1);
        })
        .catch(function (error) {
            console.log(error);
        });
    }
    function eliminarMatricula(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        eliminarMatriculaDb();
    }
    return (
        <>
        <h3>Curso {matricula.year-1}/{matricula.year} de {matricula.dnialumno}</h3>
        <div>
        <h4>Asignaturas: </h4>
        <ul>
        {matricula.asignaturas?.map((asignatura: Asignatura) => {
            return (
                <>
                <li><Link to={'asignaturas/' + asignatura.id}>{asignatura.nombre}</Link></li>
                </>
                );
            })
        }
        </ul>
        <button onClick={eliminarMatricula}>Eliminar matrícula</button>
        </div>
        </>
        );
    }