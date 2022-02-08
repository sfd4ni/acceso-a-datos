import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Alumno } from './modelo/Alumno';
import { Asignatura } from './modelo/Asignatura';
interface IProps {
  asignatura: Asignatura
 }
 
export const AsignaturaComponent = (props: IProps) => {
  const { asignatura } = props;
    return (
        <>
            <h3>{asignatura.nombre}</h3>
            <div>
                <h4>Curso: {asignatura.curso}</h4>
            </div>
        </>
    );
}