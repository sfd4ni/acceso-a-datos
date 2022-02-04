import React from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import Monedas from './Monedas';
import OperarBotones from './OperarBotones';
import MostrarInput from './MostrarInput';
import { MonedasFunc } from './MonedasFunc';
import { PersonasRaiz } from './PersonasRaiz';
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
                    <Route path="/" element={<Monedas />} />
                    <Route path="/operar" element={<OperarBotones />} />
                    <Route path="/mostrar" element={<MostrarInput />} />
                    <Route path="/monedas" element={<MonedasFunc />} />
                    <Route path="/personas" element={<PersonasRaiz/>}/>
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
            <Link to="/operar"> Operar Botones </Link> &nbsp;
            <Link to="/mostrar"> Mostrar Input </Link> &nbsp;
            <Link to="/monedas"> Monedas </Link>
            <Link to="/personas"> PersonasRaiz </Link>
        </nav>
    );
}
export default App;