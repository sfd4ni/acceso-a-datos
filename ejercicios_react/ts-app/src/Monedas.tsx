import axios from 'axios';
import React from 'react';
import { MonedaComponent } from './MonedaComponent';
import { MonedaId } from './MonedaId';
interface IProps { }
interface IState {
    monedas?: Array<any>;
}
class Monedas extends React.Component<IProps, IState>{
    ip: string;
    puerto: number;
    rutaBase: string;
    rutaMonedas: string;
    constructor(props: IProps) {
        super(props);
        this.state = {
            monedas: []
        };
        this.ip = "localhost";
        this.puerto = 8080;
        this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
        this.rutaMonedas = this.rutaBase + "/monedas";
    }
    render() {
        const { monedas } = this.state;
        return (
            <>
                <h3>Un componente sencillo para monedas</h3>
                <div>
                    Monedas:
                    {this.state.monedas?.map((monedaId: MonedaId) => {
                    return (
                        <MonedaComponent moneda={monedaId}/>
                    );
                })
                }
                </div>
            </>
        );
    }
    public async componentDidMount() {
        let ruta = this.rutaMonedas;
        let respuesta = await axios.get(ruta);
        this.setState({ monedas: respuesta.data });
    }
}
export default Monedas;