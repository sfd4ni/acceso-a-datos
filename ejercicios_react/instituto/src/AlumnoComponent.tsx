import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Alumno } from './modelo/Alumno';
import { Matricula } from './modelo/Matricula';
interface IProps {
  alumno: Alumno
 }
 
export const AlumnoComponent = (props: IProps) => {
  const { alumno } = props;
    return (
        <>
            <h3>{alumno.dnialumno}</h3>
            <div>
                <span>Nombre: {alumno.nombre}</span><br/>
                <span>Apellidos: {alumno.apellidos} </span><br/>
                <span>Fecha nacimiento: {alumno.fechanacimiento} </span><br/>
                <h4>Matriculas: </h4>
                {alumno.matriculas?.map((matricula: Matricula) => {
                    return (
                        <>
                            <span>{matricula.year}</span><br/>
                        </>
                        
                    );
                })
                }
            </div>
        </>
    );
}