import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { AlumnosGet } from './AlumnosGet';
import { Alumno } from './modelo/Alumno';
import { AlumnoComponent } from './AlumnoComponent';
import { MatriculasGet } from './MatriculasGet';
interface IProps { }
interface IState { alumnos: [] }
class App extends React.Component<IProps, IState>{
    ip: string;
    puerto: number;
    rutaBase: string;
    rutaMonedas: string;
    constructor(props: IProps) {
        super(props);
        this.state = {
            alumnos: []
        };
        this.ip = "localhost";
        this.puerto = 8081  ;
        this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
        this.rutaMonedas = this.rutaBase + "/alumnos";
    }

    public async componentDidMount() {
        let ruta = this.rutaMonedas;
        let respuesta = await axios.get(ruta);
        this.setState({ alumnos: respuesta.data });
    }
    
    render() {
        return (
            <BrowserRouter>
                <h1>Instituto</h1>
                <ul>
            {this.state.alumnos?.map((alumno: Alumno) => {
                    return (
                        <li><Link to={'alumnos/' + alumno.dnialumno}>{alumno.dnialumno}</Link></li>
                    );
                })
                }
                </ul>
                <Routes>
                    <Route path="/" />
                    <Route path="alumnos/:dnialumno" element={<AlumnosGet/>} />
                    <Route path="alumnos/:dnialumno/matriculas/:idmatricula" element={<MatriculasGet/>}/>
                </Routes>
            </BrowserRouter>
        );
    }
}
export default App;