import axios from 'axios';
import React from 'react';
import { Libro } from '../Modelo/libroGet';
import { LibroComponent } from './LibroComponent';
import { useNavigate, useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    libro: Libro;
}
export const LibroGet = () => {
    const ip = "localhost";
    const puerto = 8080;

    const [state, setstate] = React.useState<IState>({ libro: new Libro(0, "", "", [], []) });
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/libro/";
    const { libroid } = useParams();


    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
        headers: { Authorization: token }
    };

    React.useEffect(() => {
        const getLibro = async (id: string | undefined) => {
            let { data } = await axios.get(rutaBase + id, headers);
            setstate({ libro: data });
        }
        getLibro(libroid);
    }, [libroid]);
    return (
        <>
            <div>
                <LibroComponent libro={state.libro} />
            </div>
        </>
    );
}