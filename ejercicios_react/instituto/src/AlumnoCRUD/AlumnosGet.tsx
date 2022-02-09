import axios from 'axios';
import React from 'react';
import { Alumno } from '../modelo/Alumno';
import { AlumnoComponent } from './AlumnoComponent';
import { matchPath, Location, useParams } from 'react-router-dom';
interface IProps { }
interface IState {
    alumno: Alumno;
}
export const AlumnosGet = () => {
    const ip = "localhost";
    const puerto = 8081;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/alumnos/";
    const [state, setstate] = React.useState<IState>({alumno: new Alumno("", "", "", 800000000000, new Array())});
    const { dnialumno } = useParams();
    React.useEffect(() => {
        const getAlumno = async (dnialumno: string | undefined) =>{
            console.log(rutaBase + dnialumno);
            let { data } = await axios.get(rutaBase + dnialumno);
            setstate({alumno: data});
            console.log(state?.alumno);
            }
        getAlumno(dnialumno);
    }, [dnialumno]);
    return (
        <>
            <h3>Un componente sencillo para alumnos de manera funcional</h3>
            <div>
                <AlumnoComponent alumno={state.alumno}/>
            </div>
        </>
    );
}