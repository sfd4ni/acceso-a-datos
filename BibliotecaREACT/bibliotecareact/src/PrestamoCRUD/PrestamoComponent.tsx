import axios from 'axios';
import React from 'react';
import { Prestamo } from '../Modelo/prestamo';
import { Link, useNavigate, useParams } from 'react-router-dom';
interface IProps {
  prestamo: Prestamo
}

export const PrestamoComponent = (props: IProps) => {
  const [pulsado, setPulsado] = React.useState(false);
  const { prestamo } = props;
  const ip = "localhost";
  const puerto = 8080  ;
  const {clienteid} = useParams();
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/cliente/" + clienteid + "/prestamo/";
  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };



  const navigate = useNavigate();
  React.useEffect(() => {
      const eliminarPrestamo = async (id: string | undefined) =>{
          if (pulsado) {
              console.log(rutaBase + id, headers);
              await axios.delete(rutaBase + id)
              .then(function (response) {
                navigate(-1);
                })
                .catch(function (error) {
                    console.log(error);
              });
          }
      }
      eliminarPrestamo(prestamo.prestamoid+"");
  }, [pulsado]);
  function eliminarPrestamo(event: React.MouseEvent<HTMLButtonElement>) {
      event.preventDefault();
      setPulsado(true);
  }
  function modificarPrestamo(event: React.MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    navigate('put/');
}
return (
  <>
    
    <div>
      <h3>Ejemplar {prestamo.ejemplar.localizacion}</h3>
      <h4>Fecha devolución: {prestamo.fechadevolucion.toString()}</h4>
      <h4>Fecha prestamo: {prestamo.fechaprestamo.toString()}</h4>
    </div>
    <button onClick={eliminarPrestamo}>Eliminar</button>
    <button onClick={modificarPrestamo}>Modificar</button>
  </>
  );
}