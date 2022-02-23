import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { Cliente } from '../Modelo/cliente';
interface IProps { }
interface IState { clientes: [] }
class ClienteGetAll extends React.Component<IProps, IState>{
  ip: string;
  puerto: number;
  rutaBase: string;
  rutaClientes: string;
  token: string;
  headers: any;
  constructor(props: IProps) {
    super(props);
    this.state = {
      clientes: []
    };
    this.ip = "localhost";
    this.puerto = 8080;
    this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
    this.rutaClientes = this.rutaBase + "/cliente";
    this.token = localStorage.getItem("token") as string;
    this.headers = {
      headers: { Authorization: this.token }
    };
     
  }
  
  public async componentDidMount() {
    let ruta = this.rutaClientes;
    let respuesta = await axios.get(ruta, this.headers);
    this.setState({ clientes: respuesta.data });
  }
  
  render() {
    return (
      <>
        <h1>Clientes</h1>
        <ul>
        {this.state.clientes?.map((cliente: Cliente) => {
          return (
            <li key={"cliente"+cliente.clienteid}><Link to={cliente.clienteid+""}>{cliente.nombre} {cliente.apellidos}</Link></li>
            );
          })
        }
        </ul>
        <Link to='add'>Añadir cliente</Link>
      </>
      );
    }
  }
  export default ClienteGetAll;