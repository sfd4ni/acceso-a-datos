import axios from 'axios';
import React from 'react';
import { Asignatura } from './modelo/Asignatura';
import { AsignaturaComponent } from './AsignaturaComponent';
import { useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    asignatura: Asignatura;
}
export const AsignaturasGet = () => {
    const ip = "localhost";
    const puerto = 8081;
    
    const [state, setstate] = React.useState<IState>({asignatura: new Asignatura(0, "", "")});
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/asignaturas/";
    const { idasignatura } = useParams();
    console.log(idasignatura);
    React.useEffect(() => {
        const getAsignatura = async (id: string | undefined) =>{
            let { data } = await axios.get(rutaBase + id);
            let arrayMatr = data;
            setstate({asignatura: arrayMatr[0]});
            }
        getAsignatura(idasignatura);
    }, [idasignatura]);
    return (
        <>
            <h3>Un componente sencillo para asignaturas de manera funcional</h3>
            <div>
                <AsignaturaComponent asignatura={state.asignatura}/>
            </div>
        </>
    );
}