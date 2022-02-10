import axios from 'axios';
import React from 'react';
import { Asignatura } from '../modelo/Asignatura';
import { useNavigate } from 'react-router-dom';
interface IProps {
  asignatura: Asignatura
}

export const AsignaturaComponent = (props: IProps) => {
    const [pulsado, setPulsado] = React.useState(false);
    const { asignatura } = props;
    const ip = "localhost";
    const puerto = 8081  ;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/asignaturas/";
    const navigate = useNavigate();
    React.useEffect(() => {
        const eliminarAsignatura = async (id: string | undefined) =>{
            if (pulsado) {
                console.log(rutaBase + id);
                await axios.delete(rutaBase + id)
                .then(function (response) {
                  navigate('/asignaturas');
                  })
                  .catch(function (error) {
                      console.log(error);
                });
            }
        }
        eliminarAsignatura(asignatura.id+"");
    }, [pulsado]);
    function eliminarAsignatura(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        setPulsado(true);
    }
    function modificarAsignatura(event: React.MouseEvent<HTMLButtonElement>) {
      event.preventDefault();
      navigate('put/');
  }
  return (
    <>
      <h3>{asignatura.nombre}</h3>
      <div>
        <h4>Curso: {asignatura.curso}</h4>
      </div>
      <button onClick={eliminarAsignatura}>Eliminar</button>
      <button onClick={modificarAsignatura}>Modificar</button>
    </>
    );
  }