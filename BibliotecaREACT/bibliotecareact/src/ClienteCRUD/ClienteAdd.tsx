import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { ClientePost } from '../Modelo/clientePost';
interface IState {
  clientePost: ClientePost
}
export const ClienteAdd = () => {
  const ip = "localhost";
  const puerto = 8080;
  const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
  const rutaClientes = rutaBase + "/cliente";
  const [statePost, setStatePost] = React.useState<IState>({ clientePost: new ClientePost("", "", "") });
  const nombrecliente = React.useRef<HTMLInputElement>(null);
  const direccioncliente = React.useRef<HTMLInputElement>(null);
  const apellidoscliente = React.useRef<HTMLInputElement>(null);
  const navigate = useNavigate();

  const token = localStorage.getItem("token") as string;
  const headers = {
    headers: { Authorization: token }
  };
  const postClienteEffect = React.useEffect(() => {
    const postCliente = async (cliente: ClientePost) => {
      if (cliente.nombre !== "" && cliente.direccion !== ""
        && cliente.apellidos !== "") {
        await axios.post(rutaClientes, cliente, headers)
          .then(function (response) {
            navigate(-1);
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
    postCliente(statePost.clientePost);
  }, [statePost]);

  const postCliente = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const direccionCliente = direccioncliente.current?.value;
    const nombreCliente = nombrecliente.current?.value;
    const apellidosCliente = apellidoscliente.current?.value;
    if (typeof nombreCliente === "string" && typeof direccionCliente === "string"
      && typeof apellidosCliente === "string") {
      let cliente = new ClientePost(apellidosCliente, direccionCliente, nombreCliente);
      setStatePost({ clientePost: cliente });
    }

  }
  return (
    <>
      <h3>Crear una cliente</h3>
      <div>
        <span>Cliente a introducir:</span><br />
        <span>Dirección: </span><input type="text" ref={direccioncliente}></input><br />
        <span>Nombre: </span><input type="text" ref={nombrecliente}></input><br />
        <span>Apellidos: </span><input type="text" ref={apellidoscliente}></input><br />
        <button onClick={postCliente}>Introducir Cliente</button>
      </div>
    </>
  );
}