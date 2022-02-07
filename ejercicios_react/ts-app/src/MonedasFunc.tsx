import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { Moneda } from './Moneda';
import { MonedaComponent } from './MonedaComponent';
import { MonedaId } from './MonedaId';
interface IProps { }
interface IState {
    moneda: MonedaId;
}
export const MonedasFunc = () => {
    const ip = "localhost";
    const puerto = 8080;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1/monedas/";
    const [state, setstate] = React.useState<IState>({moneda: new MonedaId(1, "", "", new Array())});
    const idmoneda = React.useRef<HTMLInputElement>(null);
    // {JSON.stringify(stmoneda)}
    React.useEffect(() => {
        const getMoneda = async (monedaid: string) =>{
            console.log(rutaBase + monedaid);
            let { data } = await axios.get(rutaBase + monedaid);
            setstate({moneda: data});
            console.log(state?.moneda);
            }
        getMoneda(state.moneda.idmoneda+"");
    }, [state?.moneda.idmoneda]);
    const buscarMoneda = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        let monedaMod = state.moneda;
        let idMoneda = idmoneda.current?.value || "1";
        monedaMod.idmoneda = parseInt(idMoneda);
        setstate({moneda: monedaMod});
    }
    return (
        <>
            <h3>Un componente sencillo para monedas de manera funcional</h3>
            <div>
                Moneda a buscar: <input type="text" ref={idmoneda}/>
                <button onClick={buscarMoneda}>Buscar</button>
                    <MonedaComponent moneda={state.moneda}/>
            </div>
        </>
    );
}