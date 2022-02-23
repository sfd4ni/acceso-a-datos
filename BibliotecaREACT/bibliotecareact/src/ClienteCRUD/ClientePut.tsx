import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { ClientePost } from '../Modelo/clientePost';
interface IState {
  clientePut: ClientePost
}
export const ClientePut = () => {
const ip = "localhost";
const puerto = 8080;
const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
const rutaClientes = rutaBase + "/cliente";
const {clienteid} = useParams();
const [statePut, setStatePut] = React.useState<IState>({clientePut: new ClientePost("", "", "")});
const nombrecliente = React.useRef<HTMLInputElement>(null);
const direccioncliente = React.useRef<HTMLInputElement>(null);
const apellidoscliente = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();

const token = localStorage.getItem("token") as string;
const headers = {
  headers: { Authorization: token }
};

React.useEffect(() => {
  const getCliente = async (id: string | undefined) =>{
      let { data } = await axios.get(rutaClientes + "/" + id, headers);
      setStatePut({clientePut: data});
      }
  getCliente(clienteid);
}, [clienteid]);


  const putClienteAsync = async (cliente: ClientePost) =>{
    if (cliente.nombre !== "") {
      await axios.put(rutaClientes + "/" + clienteid, cliente, headers)
        .then(function (response) {
          navigate(-1);
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }

const putCliente = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    const direccionCliente = direccioncliente.current?.value;
    const nombreCliente = nombrecliente.current?.value;
    const apellidosCliente = apellidoscliente.current?.value;
    if (typeof nombreCliente === "string" && typeof direccionCliente === "string"
    && typeof apellidosCliente === "string") {
        let cliente = statePut.clientePut;
        cliente.nombre = nombreCliente;
        cliente.apellidos = apellidosCliente;
        cliente.direccion = direccionCliente;
        putClienteAsync(cliente);
    }
    
}
return (
    <>
        <h3>Modificar un cliente</h3>
        <div>
          <span>Cliente a modificar:</span><br/>
          <span>Dirección: </span><input type="text" ref={direccioncliente} defaultValue={statePut.clientePut.direccion}></input><br/>
          <span>Nombre: </span><input type="text" ref={nombrecliente} defaultValue={statePut.clientePut.nombre}></input><br/>
          <span>Apellidos: </span><input type="text" ref={apellidoscliente} defaultValue={statePut.clientePut.apellidos}></input><br/>
          <button onClick={putCliente}>Modificar Cliente</button>
        </div>
    </>
);
}