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
interface IStatePrestamo {
  prestamo: PrestamoPost
}
export const PrestamoPut = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const { clienteid, prestamoid } = useParams();
  const rutaPrestamos = rutaBase + "/cliente/" + clienteid + "/prestamo";
  const [stateEjemplar, setStateEjemplar] = React.useState<IState>({ ejemplar: new Ejemplar(0, "") });
  const [stateLibros, setStateLibros] = React.useState<IStateLibros>({ libros: new Array<Libro>() });
  const [statePrestamoPut, setStatePrestamoPut] = React.useState<IStatePrestamo>({ prestamo: new PrestamoPost(new Ejemplar(0, ""), new Date(), new Date()) });
  const fechaprestamo = React.useRef<HTMLInputElement>(null);
  const fechadevolucion = React.useRef<HTMLInputElement>(null);
  const navigate = useNavigate();
  const ejemplarid = localStorage.getItem("ejemplarid");
  let fechaDevolucionMod = localStorage.getItem("fechaDevolucionMod");
  if (fechaDevolucionMod === null) {
    fechaDevolucionMod = new Date().toISOString().split("T")[0];
  }
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

  React.useEffect(() => {
    const getPrestamo = async (id: string | undefined) => {
      let { data } = await axios.get(rutaPrestamos + "/" + id, headers);
      setStatePrestamoPut({ prestamo: data });
    }
    getPrestamo(prestamoid);
  }, [prestamoid]);


  const putPrestamoAsync = async function putPrestamoAsync(prestamo: PrestamoPost) {
    await axios.put(rutaPrestamos + "/" + prestamoid, prestamo, headers)
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

  const putPrestamo = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const fechaDevolucion = fechadevolucion.current?.value;
    if (typeof fechaDevolucion === "string") {
      let prestamo = statePrestamoPut.prestamo;
      prestamo.fechadevolucion = new Date(fechaDevolucion);
      prestamo.ejemplar = stateEjemplar.ejemplar;
      putPrestamoAsync(prestamo);
    }
  }
  const parseoDate = (date: Date) => {
    if (date !== null) {
      return date.toISOString().split("T")[0];
    }
  }
  return (
    <>
      <h3>Modificar un prestamo</h3>
      <div>
        <span>Prestamo a introducir:</span><br />
        <span>Fecha Devolución: </span><input type="text" ref={fechadevolucion} defaultValue={fechaDevolucionMod} placeholder="YYYY-MM-DD"></input><br />
        <fieldset>
          <legend>Elige un ejemplar</legend>
          {stateLibros.libros?.map((libro: Libro) => {
            return (
              <>
                <h5>{libro.titulo}</h5>
                {libro.ejemplares.map((ejemplar: Ejemplar) => {
                  return (
                    (ejemplarid === ejemplar.ejemplarid + "")
                      ? <label><input type="radio" key={"ejemplar" + ejemplar.ejemplarid} value={ejemplar.ejemplarid + "/" + ejemplar.localizacion}
                        name="ejemplaresGroup" onChange={handleOnChangeEjemplar} defaultChecked />{ejemplar.localizacion} </label>
                      : <label><input type="radio" key={"ejemplar" + ejemplar.ejemplarid} value={ejemplar.ejemplarid + "/" + ejemplar.localizacion}
                        name="ejemplaresGroup" onChange={handleOnChangeEjemplar} />{ejemplar.localizacion}</label>
                  );
                })}
              </>
            );
          })
          }
        </fieldset>
        <button onClick={putPrestamo}>Modificar Prestamo</button>
      </div>
    </>
  );
}