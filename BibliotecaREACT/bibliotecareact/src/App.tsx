import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
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
        this.puerto = 8080;
        this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
        this.rutaAlumnos = this.rutaBase + "/cliente";
    }

    public async componentDidMount() {
        let ruta = this.rutaAlumnos;
        let respuesta = await axios.get(ruta);
        this.setState({ alumnos: respuesta.data });
    }
    
    render() {
        return (
            <BrowserRouter>
                <h1>Biblioteca</h1>
                <nav>
                    <Link to={'autores/'}>Autores</Link>
                    <Link to={'libros/'}>Libros</Link>
                    <Link to={'clientes/'}>Clientes</Link>
                </nav>
                <Routes>
                    <Route path="/" />
                    {/*<Route path="/alumnos" element={<AlumnosGetAll/>}/>
                    <Route path="/alumnos/add" element={<AlumnosPost/>}/>
                    <Route path="/alumnos/:id/put" element={<AlumnosPut/>}/>
                    <Route path="/asignaturas" element={<AsignaturasGetAll/>}/>
                    <Route path="alumnos/:dnialumno" element={<AlumnosGet/>} />
                    <Route path="alumnos/:dnialumno/matriculas/:idmatricula" element={<MatriculasGet/>}/>
                    <Route path="alumnos/:dnialumno/matriculas/add" element={<MatriculasPost/>}/>
                    <Route path="alumnos/:dnialumno/matriculas/:idmatricula/asignaturas/:id" element={<AsignaturasGet/>}/>
                    <Route path="/asignaturas/:id" element={<AsignaturasGet/>}/>
                    <Route path="/asignaturas/add" element={<AsignaturasPost/>}/>
        <Route path="/asignaturas/:id/put" element={<AsignaturasPut/>}/>*/}
                </Routes>
            </BrowserRouter>
        );
    }
}
export default App;