import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { AlumnosGet } from './AlumnoCRUD/AlumnosGet';
import { MatriculasGet } from './MatriculaCRUD/MatriculasGet';
import { AsignaturasGet } from './AsignaturaCRUD/AsignaturasGet';
import AlumnosGetAll from './AlumnoCRUD/AlumnosGetAll';
import AsignaturasGetAll from './AsignaturaCRUD/AsignaturasGetAll';
import { AlumnosPost } from './AlumnoCRUD/AlumnoAdd';
import { AlumnosPut } from './AlumnoCRUD/AlumnoPut';
import { AsignaturasPost } from './AsignaturaCRUD/AsignaturaAdd';
import { AsignaturasPut } from './AsignaturaCRUD/AsignaturaPut';
import { MatriculasPost } from './MatriculaCRUD/MatriculaAdd';
interface IProps { }
interface IState { alumnos: [] }
class App extends React.Component<IProps, IState>{
    ip: string;
    puerto: number;
    rutaBase: string;
    rutaAlumnos: string;
    constructor(props: IProps) {
        super(props);
        this.state = {
            alumnos: []
        };
        this.ip = "localhost";
        this.puerto = 8081  ;
        this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
        this.rutaAlumnos = this.rutaBase + "/alumnos";
    }

    public async componentDidMount() {
        let ruta = this.rutaAlumnos;
        let respuesta = await axios.get(ruta);
        this.setState({ alumnos: respuesta.data });
    }
    
    render() {
        return (
            <BrowserRouter>
                <h1>Instituto</h1>
                <nav>
                    <Link to={'alumnos/'}>Alumnos</Link>
                    <Link to={'asignaturas/'}>Asignaturas</Link>
                </nav>
                <Routes>
                    <Route path="/" />
                    <Route path="/alumnos" element={<AlumnosGetAll/>}/>
                    <Route path="/alumnos/add" element={<AlumnosPost/>}/>
                    <Route path="/alumnos/:id/put" element={<AlumnosPut/>}/>
                    <Route path="/asignaturas" element={<AsignaturasGetAll/>}/>
                    <Route path="alumnos/:dnialumno" element={<AlumnosGet/>} />
                    <Route path="alumnos/:dnialumno/matriculas/:idmatricula" element={<MatriculasGet/>}/>
                    <Route path="alumnos/:dnialumno/matriculas/add" element={<MatriculasPost/>}/>
                    <Route path="alumnos/:dnialumno/matriculas/:idmatricula/asignaturas/:id" element={<AsignaturasGet/>}/>
                    <Route path="/asignaturas/:id" element={<AsignaturasGet/>}/>
                    <Route path="/asignaturas/add" element={<AsignaturasPost/>}/>
                    <Route path="/asignaturas/:id/put" element={<AsignaturasPut/>}/>
                </Routes>
            </BrowserRouter>
        );
    }
}
export default App;