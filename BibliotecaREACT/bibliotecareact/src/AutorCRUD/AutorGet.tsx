import axios from 'axios';
import React from 'react';
import { Autor } from '../Modelo/autor';
import { AutorComponent } from './AutorComponent';
import { useNavigate, useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    autor: Autor;
}
export const AutorGet = () => {
    const ip = "localhost";
    const puerto = 8080;
    
    const [state, setstate] = React.useState<IState>({autor: new Autor(0, "", "", "")});
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/autor/";
    const { autorid } = useParams();
    
    
    const navigate = useNavigate();

    const token = localStorage.getItem("token") as string;
    const headers = {
      headers: { Authorization: token }
    };

    React.useEffect(() => {
        const getAutor = async (id: string | undefined) =>{
            let { data } = await axios.get(rutaBase + id, headers);
            setstate({autor: data});
            }
        getAutor(autorid);
    }, [autorid]);
    return (
        <>
            <div>
              <AutorComponent autor={state.autor}/>
            </div>
        </>
    );
}