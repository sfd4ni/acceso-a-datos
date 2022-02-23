import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { Autor } from '../Modelo/autor';
interface IProps { }
interface IState { autores: [] }
class AutorGetAll extends React.Component<IProps, IState>{
  ip: string;
  puerto: number;
  rutaBase: string;
  rutaAutores: string;
  token: string;
  headers: any;
  constructor(props: IProps) {
    super(props);
    this.state = {
      autores: []
    };
    this.ip = "localhost";
    this.puerto = 8080;
    this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
    this.rutaAutores = this.rutaBase + "/autor";
    this.token = localStorage.getItem("token") as string;
    this.headers = {
      headers: { Authorization: this.token }
    };

  }

  public async componentDidMount() {
    let ruta = this.rutaAutores;
    let respuesta = await axios.get(ruta, this.headers);
    this.setState({ autores: respuesta.data });
  }

  render() {
    return (
      <>
        <h1>Autores</h1>
        <ul>
          {this.state.autores?.map((autor: Autor) => {
            return (
              <li key={"autor" + autor.autorid}><Link to={autor.autorid + ""}>{autor.nombre} {autor.apellidos}</Link></li>
            );
          })
          }
        </ul>
        <Link to='add'>Añadir autor</Link>
      </>
    );
  }
}
export default AutorGetAll;