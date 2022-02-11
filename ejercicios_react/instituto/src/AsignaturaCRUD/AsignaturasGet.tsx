import axios from 'axios';
import React from 'react';
import { Asignatura } from '../modelo/Asignatura';
import { AsignaturaComponent } from './AsignaturaComponent';
import { useNavigate, useParams } from 'react-router-dom';
import { AsignaturaMatriculaComponent } from './AsignaturaMatriculaComp';
interface IProps { }
interface IState {
    asignatura: Asignatura;
}
export const AsignaturasGet = () => {
    const ip = "localhost";
    const puerto = 8081;
    
    const [state, setstate] = React.useState<IState>({asignatura: new Asignatura(0, "", "")});
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/asignaturas/";
    const { id, idmatricula } = useParams();
    
    const noHaymatricula = (typeof idmatricula === "undefined");
    
    const navigate = useNavigate();

    

    React.useEffect(() => {
        const getAsignatura = async (id: string | undefined) =>{
            let { data } = await axios.get(rutaBase + id);
            setstate({asignatura: data});
            }
        getAsignatura(id);
    }, [id]);
    return (
        <>
            <div>
            {noHaymatricula
                ? <AsignaturaComponent asignatura={state.asignatura} />
                : <AsignaturaMatriculaComponent asignatura={state.asignatura} />
            }
            </div>
        </>
    );
}