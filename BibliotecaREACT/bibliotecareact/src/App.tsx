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
import { AutorAdd } from './AutorCRUD/AutorAdd';
import { RequireAuth } from './RequireAuth';
import { Logout } from './logout';
import { AutorPut } from './AutorCRUD/AutorPut';
import { EjemplarPut } from './EjemplarCRUD/EjemplarPut';
import { LibroPut } from './LibroCRUD/LibroPut';
import { PrestamoPut } from './PrestamoCRUD/PrestamoPut';
import { ClientePut } from './ClienteCRUD/ClientePut';
interface IProps { }
interface IState { alumnos: [] }
class App extends React.Component<IProps, IState>{
    constructor(props: IProps) {
        super(props);
    }
    render() {
        return (
            <BrowserRouter>
                <h1>Biblioteca</h1>
                <nav>
                    <Link to={'autor/'}>Autores</Link><br/>
                    <Link to={'libro/'}>Libros</Link><br/>
                    <Link to={'cliente/'}>Clientes</Link><br/>
                    <Link to={'logout/'}>Logout</Link><br/>
                </nav>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/logout" element={<RequireAuth><Logout/></RequireAuth>}/>
                    <Route path="/cliente" element={<RequireAuth><ClienteGetAll/></RequireAuth>}/>
                    <Route path="/cliente/add" element={<RequireAuth><ClienteAdd/></RequireAuth>}/>
                    <Route path="/cliente/:clienteid" element={<RequireAuth><ClienteGet/></RequireAuth>}/>
                    <Route path="/cliente/:clienteid/put" element={<RequireAuth><ClientePut/></RequireAuth>}/>
                    <Route path="/cliente/:clienteid/prestamo/:prestamoid" element={<RequireAuth><PrestamoGet/></RequireAuth>}/>
                    <Route path="/cliente/:clienteid/prestamo/:prestamoid/put" element={<RequireAuth><PrestamoPut/></RequireAuth>}/>
                    <Route path="/cliente/:clienteid/prestamo/add" element={<RequireAuth><PrestamoAdd/></RequireAuth>}/>
                    <Route path="/libro" element={<RequireAuth><LibroGetAll/></RequireAuth>}/>
                    <Route path="/libro/add" element={<RequireAuth><LibroAdd/></RequireAuth>}/>
                    <Route path="/libro/:libroid" element={<RequireAuth><LibroGet/></RequireAuth>}/>
                    <Route path="/libro/:libroid/put" element={<RequireAuth><LibroPut/></RequireAuth>}/>
                    <Route path="/libro/:libroid/ejemplar/:ejemplarid" element={<RequireAuth><EjemplarGet/></RequireAuth>}/>
                    <Route path="/libro/:libroid/ejemplar/:ejemplarid/put" element={<RequireAuth><EjemplarPut/></RequireAuth>}/>
                    <Route path="/libro/:libroid/ejemplar/add" element={<RequireAuth><EjemplarAdd/></RequireAuth>}/>
                    <Route path="/autor" element={<RequireAuth><AutorGetAll/></RequireAuth>}/>
                    <Route path="/autor/add" element={<RequireAuth><AutorAdd/></RequireAuth>}/>
                    <Route path="/autor/:autorid/put" element={<RequireAuth><AutorPut/></RequireAuth>}/>
                    <Route path="/autor/:autorid" element={<RequireAuth><AutorGet/></RequireAuth>}/>

                </Routes>
            </BrowserRouter>
        );
    }
}
export default App;