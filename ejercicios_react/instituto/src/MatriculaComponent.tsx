import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
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
                {matricula.asignaturas?.map((asignatura: Asignatura) => {
                    return (
                        <>
                            <span>{asignatura.nombre}</span><br/>
                        </>
                        
                    );
                })
                }
            </div>
        </>
    );
}