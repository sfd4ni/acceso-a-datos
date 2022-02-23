import axios from 'axios';
import React from 'react';
import { Ejemplar } from '../Modelo/ejemplar';
import { EjemplarComponent } from './EjemplarComponent';
import { useNavigate, useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    ejemplar: Ejemplar;
}
export const EjemplarGet = () => {
    const ip = "localhost";
    const puerto = 8080;

    const [state, setstate] = React.useState<IState>({ ejemplar: new Ejemplar(0, "") });

    const { libroid, ejemplarid } = useParams();
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/libro/" + libroid + "/ejemplar/";

    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    React.useEffect(() => {
        const getEjemplar = async (id: string | undefined) => {
            let { data } = await axios.get(rutaBase + id, headers);
            setstate({ ejemplar: data });
        }
        getEjemplar(ejemplarid);
    }, [ejemplarid]);
    return (
        <>
            <div>
                <EjemplarComponent ejemplar={state.ejemplar} />
            </div>
        </>
    );
}