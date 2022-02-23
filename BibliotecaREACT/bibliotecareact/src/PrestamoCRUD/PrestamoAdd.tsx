import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Ejemplar } from '../Modelo/ejemplar';
import { Libro } from '../Modelo/libro';
import { PrestamoPost } from '../Modelo/prestamoPost';
interface IState {
  ejemplar: Ejemplar
}
interface IStateLibros {
  libros: Array<Libro>
}
export const PrestamoAdd = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const { clienteid } = useParams();
  const rutaPrestamos = rutaBase + "/cliente/" + clienteid + "/prestamo";
  const [stateEjemplar, setStateEjemplar] = React.useState<IState>({ ejemplar: new Ejemplar(0, "") });
  const [stateLibros, setStateLibros] = React.useState<IStateLibros>({ libros: new Array<Libro>() });
  const fechaprestamo = React.useRef<HTMLInputElement>(null);
  const fechadevolucion = React.useRef<HTMLInputElement>(null);
  const navigate = useNavigate();

  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };

  React.useEffect(() => {
    const getLibros = async () => {
      let rutaLibros = rutaBase + "/libro";
      let { data } = await axios.get(rutaLibros, headers);
      setStateLibros({ libros: data });
    }
    getLibros();
  }, []);

  const postPrestamoAsync = async function postPrestamoAsync(prestamo: PrestamoPost) {
    await axios.post(rutaPrestamos, prestamo, headers)
      .then(function (response) {
        navigate(-1);
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }


  const handleOnChangeEjemplar = (event: React.ChangeEvent<HTMLInputElement>) => {
    event.currentTarget.checked = true;
    let stringValue = event.currentTarget.value.split("/");
    let idEjemp = parseInt(stringValue[0], 10);
    setStateEjemplar({ ejemplar: new Ejemplar(idEjemp, stringValue[1]) });
    console.log(stateEjemplar.ejemplar);
  }

  const postPrestamo = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const fechaDevolucion = fechadevolucion.current?.value;
    if (typeof fechaDevolucion === "string") {
      let prestamo = new PrestamoPost(stateEjemplar.ejemplar, new Date(), new Date(fechaDevolucion));
      postPrestamoAsync(prestamo);
    }
  }
  return (
    <>
      <h3>Crear una prestamo</h3>
      <div>
        <span>Prestamo a introducir:</span><br />
        <span>Fecha Devolución: </span><input type="text" ref={fechadevolucion} placeholder="YYYY-MM-DD"></input><br />
        <fieldset>
          <legend>Elige un ejemplar</legend>
          {stateLibros.libros?.map((libro: Libro) => {
            return (
              <>
                <h5>{libro.titulo}</h5>
                {libro.ejemplares.map((ejemplar: Ejemplar) => {
                  return (
                    <label><input type="radio" key={"ejemplar" + ejemplar.ejemplarid} value={ejemplar.ejemplarid + "/" + ejemplar.localizacion}
                      name="ejemplaresGroup" onChange={handleOnChangeEjemplar} />{ejemplar.localizacion}</label>
                  );
                })}
              </>
            );
          })
          }
        </fieldset>
        <button onClick={postPrestamo}>Introducir Prestamo</button>
      </div>
    </>
  );
}