import axios from 'axios';
import React from 'react';
import { Cliente } from '../Modelo/cliente';
import { ClienteComponent } from './ClienteComponent';
import { useNavigate, useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    cliente: Cliente;
}
export const ClienteGet = () => {
    const ip = "localhost";
    const puerto = 8080;

    const [state, setstate] = React.useState<IState>({ cliente: new Cliente(0, "", "", "", []) });
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/cliente/";
    const { clienteid } = useParams();


    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    React.useEffect(() => {
        const getCliente = async (id: string | undefined) => {
            let { data } = await axios.get(rutaBase + id, headers);
            setstate({ cliente: data });
        }
        getCliente(clienteid);
    }, [clienteid]);
    return (
        <>
            <div>
                <ClienteComponent cliente={state.cliente} />
            </div>
        </>
    );
}