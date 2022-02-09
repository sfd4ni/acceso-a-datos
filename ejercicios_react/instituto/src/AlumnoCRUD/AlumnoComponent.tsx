import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Alumno } from '../modelo/Alumno';
import { Matricula } from '../modelo/Matricula';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { MatriculasGet } from '../MatriculasGet';
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
                <span>Fecha nacimiento: {new Date(alumno.fechanacimiento).toUTCString()} </span><br/>
                <h4>Matriculas: </h4>
                <ul>
                {alumno.matriculas?.map((matricula: Matricula) => {
                        return (
                            <li><Link to={'matriculas/' + matricula.id}>Año {matricula.year}</Link></li>
                        );
                    })
                    }
                </ul>
            </div>
        </>
    );
}