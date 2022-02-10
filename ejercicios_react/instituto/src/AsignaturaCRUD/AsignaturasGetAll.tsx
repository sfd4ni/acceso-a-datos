import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { Asignatura } from '../modelo/Asignatura';
interface IProps { }
interface IState { asignaturas: [] }
class AsignaturasGetAll extends React.Component<IProps, IState>{
  ip: string;
  puerto: number;
  rutaBase: string;
  rutaAsignaturas: string;
  constructor(props: IProps) {
    super(props);
    this.state = {
      asignaturas: []
    };
    this.ip = "localhost";
    this.puerto = 8081  ;
    this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
    this.rutaAsignaturas = this.rutaBase + "/asignaturas";
  }
  
  public async componentDidMount() {
    let ruta = this.rutaAsignaturas;
    let respuesta = await axios.get(ruta);
    this.setState({ asignaturas: respuesta.data });
  }
  
  render() {
    return (
      <>
        <h1>Asignaturas</h1>
        <ul>
        {this.state.asignaturas?.map((asignatura: Asignatura) => {
          return (
            <li><Link to={asignatura.id+""}>{asignatura.nombre}</Link></li>
            );
          })
        }
        </ul>
        <Link to='add'>Añadir asignatura</Link>
      </>
      );
    }
  }
  export default AsignaturasGetAll;