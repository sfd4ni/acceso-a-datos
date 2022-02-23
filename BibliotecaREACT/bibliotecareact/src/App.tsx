import React from 'react';
import axios from 'axios';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { Login } from './login';
import { ClienteGet } from './ClienteCRUD/ClienteGet';
import ClienteGetAll from './ClienteCRUD/ClienteGetAll';
import { PrestamoGet } from './PrestamoCRUD/PrestamoGet';
import { ClienteAdd } from './ClienteCRUD/ClienteAdd';
import LibroGetAll from './LibroCRUD/LibroGetAll';
import { LibroGet } from './LibroCRUD/LibroGet';
import { EjemplarGet } from './EjemplarCRUD/EjemplarGet';
import { EjemplarAdd } from './EjemplarCRUD/EjemplarAdd';
import { PrestamoAdd } from './PrestamoCRUD/PrestamoAdd';
import { LibroAdd } from './LibroCRUD/LibroAdd';
import AutorGetAll from './AutorCRUD/AutorGetAll';
import { AutorGet } from './AutorCRUD/AutorGet';
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
                    <Link to={'autor/'}>Autores</Link><br/>
                    <Link to={'libro/'}>Libros</Link><br/>
                    <Link to={'cliente/'}>Clientes</Link><br/>
                </nav>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/cliente" element={<ClienteGetAll/>}/>
                    <Route path="/cliente/add" element={<ClienteAdd/>}/>
                    <Route path="/cliente/:clienteid" element={<ClienteGet/>}/>
                    <Route path="/cliente/:clienteid/prestamo/:prestamoid" element={<PrestamoGet/>}/>
                    <Route path="/cliente/:clienteid/prestamo/add" element={<PrestamoAdd/>}/>
                    <Route path="/libro" element={<LibroGetAll/>}/>
                    <Route path="/libro/add" element={<LibroAdd/>}/>
                    <Route path="/libro/:libroid" element={<LibroGet/>}/>
                    <Route path="/libro/:libroid/ejemplar/:ejemplarid" element={<EjemplarGet/>}/>
                    <Route path="/libro/:libroid/ejemplar/add" element={<EjemplarAdd/>}/>
                    <Route path="/autor" element={<AutorGetAll/>}/>
                    <Route path="/autor/:autorid" element={<AutorGet/>}/>

                </Routes>
            </BrowserRouter>
        );
    }
}
export default App;