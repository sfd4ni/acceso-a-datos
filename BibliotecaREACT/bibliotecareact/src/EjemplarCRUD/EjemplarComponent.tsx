import axios from 'axios';
import React from 'react';
import { Ejemplar } from '../Modelo/ejemplar';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Prestamo } from '../Modelo/prestamo';
interface IProps {
  ejemplar: Ejemplar
}

export const EjemplarComponent = (props: IProps) => {
    const [pulsado, setPulsado] = React.useState(false);
    const { ejemplar } = props;
    const { libroid } = useParams();
    const ip = "localhost";
    const puerto = 8080  ;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/libro/" + libroid + "/ejemplar/";
    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
      headers: { Authorization: token }
    };

    React.useEffect(() => {
        const eliminarEjemplar = async (id: string | undefined) =>{
            if (pulsado) {
                console.log(rutaBase + id);
                await axios.delete(rutaBase + id, headers)
                .then(function (response) {
                  navigate(-1);
                  })
                  .catch(function (error) {
                      console.log(error);
                });
            }
        }
        eliminarEjemplar(ejemplar.ejemplarid+"");
    }, [pulsado]);
    function eliminarEjemplar(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        setPulsado(true);
    }
    function modificarEjemplar(event: React.MouseEvent<HTMLButtonElement>) {
      event.preventDefault();
      navigate('put/');
  }
  return (
    <>
      <div>
        <h3>Localizacion: {ejemplar.localizacion}</h3>
      </div>
      <button onClick={eliminarEjemplar}>Eliminar</button>
      <button onClick={modificarEjemplar}>Modificar</button>
    </>
    );
  }