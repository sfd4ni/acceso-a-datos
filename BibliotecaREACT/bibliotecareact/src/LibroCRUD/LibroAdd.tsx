import axios from 'axios';
import React, { useState } from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Autor } from '../Modelo/autor';
import { string } from 'yargs';
import { LibroPost } from '../Modelo/libroPost';
interface IState {
  libroPost: LibroPost
}
interface IAutorsState {
  autores: Array<Autor>;
}
export const LibroAdd = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaLibros = rutaBase + "/libro";

  const [checked, setChecked] = useState({ checked: new Array<String>() });


  const [statePost, setStatePost] = React.useState<IState>({ libroPost: new LibroPost("", "", []) });
  const [stateAutors, setAutor] = React.useState<IAutorsState>({ autores: new Array<Autor>() });
  const rutaAutors = rutaBase + "/autor";
  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };


  React.useEffect(() => {
    const getAutors = async () => {
      let respuesta = await axios.get(rutaAutors, headers);
      setAutor({ autores: respuesta.data });
    }
    getAutors();
  }, [statePost]);


  //const [stateAlumno, setStateAlumno] = React.useState<IAlumnoState>({alumno: new Alumno("", "", "", 0, [])});
  //const añolibro = React.useRef<HTMLInputElement>(null);

  const navigate = useNavigate();

  const postLibroEffect = React.useEffect(() => {
    const postLibro = async (libro: LibroPost) => {
      if (libro.titulo !== "" && libro.editorial !== ""
        && libro.autores.length > 0) {
        await axios.post(rutaLibros, libro, headers)
          .then(function (response) {
            navigate(-1);
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
    postLibro(statePost.libroPost);
  }, [statePost]);

  const postLibro = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    let formulario: HTMLFormElement = event.currentTarget;

    const tituloLibro = formulario.tituloLibro.value;
    const editorialLibro = formulario.editorialLibro.value;

    /*stateAutors.autores.map( (autor: Autor) => {
      if (formulario.)
    }

    )*/

    let autoresAdd = new Array<Autor>();

    stateAutors.autores.map((autor: Autor) => {
      console.log(checked.checked);
      if (checked.checked.indexOf(autor.nombre + autor.apellidos) !== -1) {
        autoresAdd.push(autor);
      }
    });

    console.log(autoresAdd);

    if (typeof tituloLibro === "string" && typeof editorialLibro === "string") {
      let libro = statePost.libroPost;
      libro.titulo = tituloLibro;
      libro.editorial = editorialLibro;
      libro.autores = autoresAdd;
      setStatePost({ libroPost: libro });
    }
  }

  const handleCheck = (event: React.ChangeEvent<HTMLInputElement>) => {
    let updatedList = [...checked.checked];
    if (event.target.checked) {
      updatedList = [...checked.checked, event.target.id];
    } else {
      updatedList.splice(checked.checked.indexOf(event.target.id), 1);
    }
    setChecked({ checked: updatedList });
  };

  return (
    <>
      <h3>Introducir un libro</h3>
      <div>
        <span>Libro a introducir:</span><br />

        <form onSubmit={postLibro}>
          <span>Título: </span><input type="text" id="tituloLibro"></input><br />
          <span>Editorial: </span><input type="text" id="editorialLibro"></input><br />
          {stateAutors.autores?.map((autor: Autor) => {
            return (
              <>
                <label>
                  <input type="checkbox" id={autor.nombre + autor.apellidos} onChange={handleCheck} />
                  {autor.nombre} {autor.apellidos}
                </label>
                <br />
              </>
            );
          })
          }
          <button type='submit'>Introducir Libro</button>
        </form>
      </div>
    </>
  );
}