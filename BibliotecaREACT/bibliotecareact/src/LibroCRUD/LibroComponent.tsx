import axios from 'axios';
import React from 'react';
import { Libro } from '../Modelo/libroGet';
import { Link, useNavigate } from 'react-router-dom';
import { Prestamo } from '../Modelo/prestamo';
import { Ejemplar } from '../Modelo/ejemplar';
import { Autor } from '../Modelo/autor';
interface IProps {
  libro: Libro
}

export const LibroComponent = (props: IProps) => {
  const [pulsado, setPulsado] = React.useState(false);
  const { libro } = props;
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/libro/";
  const navigate = useNavigate();

  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };

  React.useEffect(() => {
    const eliminarLibro = async (id: string | undefined) => {
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
    eliminarLibro(libro.libroid + "");
  }, [pulsado]);
  function eliminarLibro(event: React.MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    setPulsado(true);
  }
  function modificarLibro(event: React.MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    navigate('put/');
  }
  return (
    <>
      <h3>{libro.titulo} de editorial {libro.editorial}</h3>
      <div>
        <h4>Ejemplares:</h4>
        <ul>
          {libro.ejemplares?.map((ejemplar: Ejemplar) => {
            return (
              <li key={"ejemplar" + ejemplar.ejemplarid}><Link to={"ejemplar/" + ejemplar.ejemplarid + ""}>Localizacion {ejemplar.localizacion}</Link></li>
            );
          })
          }
        </ul>
        <h4>Autores:</h4>
        <ul>
          {libro.autores?.map((autor: Autor) => {
            return (
              <li key={"autor" + autor.autorid}>{autor.nombre} {autor.apellidos}</li>
            );
          })
          }
        </ul>
        <Link to='ejemplar/add'>Añadir ejemplar</Link>
      </div>
      <button onClick={eliminarLibro}>Eliminar</button>
      <button onClick={modificarLibro}>Modificar</button>
    </>
  );
}