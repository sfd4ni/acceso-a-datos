import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Autor } from '../Modelo/autor';
import { Libro } from '../Modelo/libro';
import { LibroPost } from '../Modelo/libroPost';
interface IState {
  libroPut: LibroPost
}
interface IAutorsState {
  autores: Array<Autor>;
}
export const LibroPut = () => {
const ip = "localhost";
const puerto = 8080;
const { libroid } = useParams();
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaLibros = rutaBase + "/libro/";
const [statePut, setStatePut] = React.useState<IState>({libroPut: new LibroPost("", "", [])});
const [stateAutors, setAutor] = React.useState<IAutorsState>({autores: new Array<Autor>()});
const rutaAutors = rutaBase + "/autor";

const [checked, setChecked] = React.useState({checked: new Array<String>()});

const localizacionlibro = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();
const token = localStorage.getItem("token") as string;
const headers = {
  headers: { Authorization: token }
};


const getLibro = React.useEffect(() => {
  const getLibro = async (id: string | undefined) =>{
      let { data } = await axios.get(rutaLibros + id, headers);
      setStatePut({libroPut: data});
      }
  getLibro(libroid);
}, [libroid]);

React.useEffect(() => {
  const getAutors = async () =>{
    let respuesta = await axios.get(rutaAutors, headers);
    setAutor({ autores: respuesta.data });
  }
  getAutors();
}, []);

  const putLibroAsync = async (libro: LibroPost) =>{
    if (libro.editorial !== "" && libro.titulo !== "") {
      await axios.put(rutaLibros+libroid, libro, headers)
        .then(function (response) {
          setStatePut({libroPut: response.data})
          navigate(-1);
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }

  const putLibro = (event:React.FormEvent<HTMLFormElement>) =>  {
    event.preventDefault();

    let formulario: HTMLFormElement = event.currentTarget;

    const tituloLibro = formulario.tituloLibro.value;
    const editorialLibro = formulario.editorialLibro.value;

    /*stateAutors.autores.map( (autor: Autor) => {
      if (formulario.)
    }

    )*/

    let autoresAdd = new Array<Autor>();

    stateAutors.autores.map((autor : Autor) => {
      if (checked.checked.indexOf(autor.nombre+autor.apellidos) !== -1) {
        autoresAdd.push(autor);
      }
    });

    if (typeof tituloLibro === "string" &&  typeof editorialLibro === "string") {
        let libro = statePut.libroPut;
        libro.titulo = tituloLibro;
        libro.editorial = editorialLibro;
        libro.autores = autoresAdd;
        putLibroAsync(libro);
    }
}

const handleCheck = (event: React.ChangeEvent<HTMLInputElement>) => {
  let updatedList = [...checked.checked];
  if (event.target.checked) {
    updatedList = [...checked.checked, event.target.id];
  } else {
    updatedList.splice(checked.checked.indexOf(event.target.id), 1);
  }
  setChecked({checked: updatedList});
};

const isIn = (autor: Autor) => {
  for (let i = 0; i < statePut.libroPut.autores.length; i++) {
    if (autor.nombre+autor.apellidos === 
      statePut.libroPut.autores[i].nombre + statePut.libroPut.autores[i].apellidos) {
        return true;
    }
  }
  return false;
}
return (
    <>
        <h3>Modificar un libro</h3>
        <div>
          <span>Libro a modificar:</span><br/>
          <form onSubmit={putLibro}>
          <span>Título: </span><input type="text" id="tituloLibro" defaultValue={statePut.libroPut.titulo}></input><br/>
          <span>Editorial: </span><input type="text" id="editorialLibro" defaultValue={statePut.libroPut.editorial}></input><br/>
          {stateAutors.autores?.map((autor: Autor) => {
            return (
                <>
                <label>
                  {
                  isIn(autor)
                  ? <input type="checkbox" id={autor.nombre+autor.apellidos} onChange={handleCheck} defaultChecked={true}/>
                  : <input type="checkbox" id={autor.nombre+autor.apellidos} onChange={handleCheck}/>}
                  {autor.nombre} {autor.apellidos}
                </label>
                <br/>
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