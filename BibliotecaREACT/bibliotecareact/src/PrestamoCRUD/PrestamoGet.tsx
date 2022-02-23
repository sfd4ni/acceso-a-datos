import axios from 'axios';
import React from 'react';
import { Prestamo } from '../Modelo/prestamo';
import { PrestamoComponent } from './PrestamoComponent';
import { useNavigate, useParams } from 'react-router-dom';
import { Ejemplar } from '../Modelo/ejemplar';
interface IProps { }
interface IState {
    prestamo: Prestamo;
}
export const PrestamoGet = () => {
    const ip = "localhost";
    const puerto = 8080;

    const [state, setstate] = React.useState<IState>({ prestamo: new Prestamo(0, new Ejemplar(0, ""), new Date, new Date) });

    const { clienteid, prestamoid } = useParams();
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/cliente/" + clienteid + "/prestamo/";

    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    React.useEffect(() => {
        const getPrestamo = async (id: string | undefined) => {
            let { data } = await axios.get(rutaBase + id, headers);
            setstate({ prestamo: data });
        }
        getPrestamo(prestamoid);
    }, [prestamoid]);
    return (
        <>
            <div>
                <PrestamoComponent prestamo={state.prestamo} />
            </div>
        </>
    );
}