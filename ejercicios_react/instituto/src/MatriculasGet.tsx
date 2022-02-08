import axios from 'axios';
import React from 'react';
import { Matricula } from './modelo/Matricula';
import { MatriculaComponent } from './MatriculaComponent';
import { useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    matricula: Matricula;
}
export const MatriculasGet = () => {
    const ip = "localhost";
    const puerto = 8081;
    
    const [state, setstate] = React.useState<IState>({matricula: new Matricula(0, 0, new Array(), "")});
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/matriculas/";
    const { idmatricula } = useParams();
    console.log(idmatricula);
    React.useEffect(() => {
        const getMatricula = async (id: string | undefined) =>{
            let { data } = await axios.get(rutaBase + id);
            let arrayMatr = data;
            setstate({matricula: arrayMatr[0]});
            }
        getMatricula(idmatricula);
    }, [idmatricula]);
    return (
        <>
            <h3>Un componente sencillo para matriculas de manera funcional</h3>
            <div>
                <MatriculaComponent matricula={state.matricula}/>
            </div>
        </>
    );
}