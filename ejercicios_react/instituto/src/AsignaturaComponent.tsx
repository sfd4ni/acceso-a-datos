import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Alumno } from './modelo/Alumno';
import { Asignatura } from './modelo/Asignatura';
interface IProps {
  asignatura: Asignatura
}

export const AsignaturaComponent = (props: IProps) => {
    const [pulsado, setPulsado] = React.useState(1);
    const { asignatura } = props;
    const ip = "localhost";
    const puerto = 8081  ;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/asignaturas/";
    React.useEffect(() => {
        const eliminarMatricula = async (id: string | undefined) =>{
            if (pulsado !== 1) {
                await axios.delete(rutaBase + id).then(function (response) {
                    })
                    .catch(function (error) {
                        console.log(error);
                });
            }
        }
        eliminarMatricula(asignatura.id+"");
    }, [pulsado]);
    function eliminarMatricula(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        setPulsado(0);
    }
  return (
    <>
      <h3>{asignatura.nombre}</h3>
      <div>
        <h4>Curso: {asignatura.curso}</h4>
      </div>
      <button onClick={eliminarMatricula}>Eliminar de Matrícula</button>
    </>
    );
  }