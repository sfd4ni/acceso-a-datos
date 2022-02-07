import React from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import { AlumnosGet } from './AlumnosGet';
interface IProps { }
interface IState { }
class App extends React.Component<IProps, IState>{
    constructor(props: IProps) {
        super(props);
        this.state = {};
    }
    render() {
        return (
            <BrowserRouter>
                <h1>Aplicación Monedas</h1>
                <Navbar />
                <Routes>
                    <Route path="/" element={<AlumnosGet/>} />
                    <Route path="/post" element={<AlumnosGet />} />
                    <Route path="/delete" element={<AlumnosGet />} />
                    <Route path="/monedas" element={<AlumnosGet />} />
                    <Route path="/update" element={<AlumnosGet/>}/>
                </Routes>
            </BrowserRouter>
        );
    }
}
function Navbar() {
    // visible on every page
    return (
        <nav>
            <Link to="/"> Inicio </Link> &nbsp;
            <Link to="/post"> Crear moneda</Link> &nbsp;
            <Link to="/delete"> Eliminar Moneda </Link> &nbsp;
            <Link to="/monedas"> Buscar Monedas </Link>
            <Link to="/update"> Modificar Moneda </Link>
        </nav>
    );
}
export default App;