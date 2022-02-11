import axios from 'axios';
import React from 'react';
import { Asignatura } from '../modelo/Asignatura';
interface IProps {
  asignatura: Asignatura
}

export const AsignaturaMatriculaComponent = (props: IProps) => {

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