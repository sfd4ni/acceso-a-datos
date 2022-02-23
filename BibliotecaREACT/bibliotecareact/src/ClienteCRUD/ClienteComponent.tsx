import axios from 'axios';
import React from 'react';
import { Cliente } from '../Modelo/cliente';
import { Link, useNavigate } from 'react-router-dom';
import { Prestamo } from '../Modelo/prestamo';
interface IProps {
  cliente: Cliente
}

export const ClienteComponent = (props: IProps) => {
    const [pulsado, setPulsado] = React.useState(false);
    const { cliente } = props;
    const ip = "localhost";
    const puerto = 8080  ;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/cliente/";
    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
      headers: { Authorization: token }
    };

    React.useEffect(() => {
        const eliminarCliente = async (id: string | undefined) =>{
            if (pulsado) {
                console.log(rutaBase + id);
                await axios.delete(rutaBase + id, headers)
                .then(function (response) {
                  navigate(-1);
                  })
                  .catch(function (error) {
                      console.log(error);
                });
            }
        }
        eliminarCliente(cliente.clienteid+"");
    }, [pulsado]);
    function eliminarCliente(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        setPulsado(true);
    }
    function modificarCliente(event: React.MouseEvent<HTMLButtonElement>) {
      event.preventDefault();
      navigate('put/');
  }
  return (
    <>
      <h3>{cliente.nombre} {cliente.apellidos}</h3>
      <div>
        <h4>Dirección: {cliente.direccion}</h4>
        <ul>
        {cliente.prestamos?.map((prestamo: Prestamo) => {
          return (
            <li key={"prestamo"+prestamo.prestamoid}><Link to={"prestamo/"+prestamo.prestamoid+""}>{prestamo.ejemplar.localizacion} - {prestamo.fechaprestamo}</Link></li>
            );
          })
        }
        </ul>
        <Link to='prestamo/add'>Añadir nuevo préstamo</Link>
      </div>
      <button onClick={eliminarCliente}>Eliminar</button>
      <button onClick={modificarCliente}>Modificar</button>
    </>
    );
  }