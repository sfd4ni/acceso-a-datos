import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Link } from 'react-router-dom';
import { Alumno } from './modelo/Alumno';
import { Asignatura } from './modelo/Asignatura';
import { Matricula } from './modelo/Matricula';
interface IProps {
  matricula: Matricula
 }
 
export const MatriculaComponent = (props: IProps) => {
  const { matricula } = props;
    return (
        <>
            <h3>Año {matricula.year} de {matricula.dnialumno}</h3>
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
            </div>
        </>
    );
}