import axios from 'axios';
import React from 'react';
import { Autor } from '../Modelo/autor';
import { Link, useNavigate } from 'react-router-dom';
interface IProps {
  autor: Autor
}

export const AutorComponent = (props: IProps) => {
  const [pulsado, setPulsado] = React.useState(false);
  const { autor } = props;
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/autor/";
  const navigate = useNavigate();

  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };

  React.useEffect(() => {
    const eliminarAutor = async (id: string | undefined) => {
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
    eliminarAutor(autor.autorid + "");
  }, [pulsado]);
  function eliminarAutor(event: React.MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    setPulsado(true);
  }
  function modificarAutor(event: React.MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    navigate('put/');
  }
  return (
    <>
      <h3>{autor.nombre} {autor.apellidos}</h3>
      <h4>Nacionalidad: {autor.nacionalidad}</h4>
      <button onClick={eliminarAutor}>Eliminar</button>
      <button onClick={modificarAutor}>Modificar</button>
    </>
  );
}