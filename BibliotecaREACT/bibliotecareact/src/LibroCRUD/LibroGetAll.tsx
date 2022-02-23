import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { Libro } from '../Modelo/libro';
interface IProps { }
interface IState { libros: [] }
class LibroGetAll extends React.Component<IProps, IState>{
  ip: string;
  puerto: number;
  rutaBase: string;
  rutaLibros: string;
  token: string;
  headers: any;
  constructor(props: IProps) {
    super(props);
    this.state = {
      libros: []
    };
    this.ip = "localhost";
    this.puerto = 8080;
    this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
    this.rutaLibros = this.rutaBase + "/libro";
    this.token = localStorage.getItem("token") as string;
    this.headers = {
      headers: { Authorization: this.token }
    };

  }

  public async componentDidMount() {
    let ruta = this.rutaLibros;
    let respuesta = await axios.get(ruta, this.headers);
    this.setState({ libros: respuesta.data });
  }

  render() {
    return (
      <>
        <h1>Libros</h1>
        <ul>
          {this.state.libros?.map((libro: Libro) => {
            return (
              <li key={"libro" + libro.libroid}><Link to={libro.libroid + ""}>{libro.titulo} de editorial {libro.editorial}</Link></li>
            );
          })
          }
        </ul>
        <Link to='add'>Añadir libro</Link>
      </>
    );
  }
}
export default LibroGetAll;