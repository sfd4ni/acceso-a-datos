import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Alumno } from '../modelo/Alumno';


interface IProps { }
interface IState { alumnos: [] }
class AlumnosGetAll extends React.Component<IProps, IState>{
  ip: string;
  puerto: number;
  rutaBase: string;
  rutaAlumnos: string;
  constructor(props: IProps) {
    super(props);
    this.state = {
      alumnos: []
    };
    this.ip = "localhost";
    this.puerto = 8081  ;
    this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
    this.rutaAlumnos = this.rutaBase + "/alumnos";
  }
  
  public async componentDidMount() {
    let ruta = this.rutaAlumnos;
    let respuesta = await axios.get(ruta);
    this.setState({ alumnos: respuesta.data });
  }
  
  render() {
    return (
      <>
        <h2>Alumnos</h2>
        <ul>
        {this.state.alumnos?.map((alumno: Alumno) => {
          return (
            <li><Link to={alumno.id}>{alumno.id}</Link></li>
            );
          })
        }
        </ul>
        <Link to='add'>Añadir alumno</Link>
      </>
      );
    }
  }
  export default AlumnosGetAll;