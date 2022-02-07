import axios from 'axios';
import React from 'react';
import { Alumno } from './modelo/Alumno';
import { AlumnoComponent } from './AlumnoComponent';
interface IProps { }
interface IState {
    alumno: Alumno;
}
export const AlumnosGet = () => {
    const ip = "localhost";
    const puerto = 8081;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/alumnos/";
    const [state, setstate] = React.useState<IState>({alumno: new Alumno("", "", "", 800000000000, new Array())});
    const idalumno = React.useRef<HTMLInputElement>(null);
    // {JSON.stringify(stalumno)}
    React.useEffect(() => {
        const getAlumno = async (dnialumno: string) =>{
            console.log(rutaBase + dnialumno);
            let { data } = await axios.get(rutaBase + dnialumno);
            setstate({alumno: data});
            console.log(state?.alumno);
            }
        getAlumno(state.alumno.dnialumno+"");
    }, [state?.alumno.dnialumno]);
    const buscarAlumno = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        let alumnoMod = state.alumno;
        alumnoMod.dnialumno = idalumno.current?.value || "46262784D";
        setstate({alumno: alumnoMod});
    }
    return (
        <>
            <h3>Un componente sencillo para alumnos de manera funcional</h3>
            <div>
                Alumno a buscar: <input type="text" ref={idalumno}/>
                <button onClick={buscarAlumno}>Buscar</button>
                    <AlumnoComponent alumno={state.alumno}/>
            </div>
        </>
    );
}