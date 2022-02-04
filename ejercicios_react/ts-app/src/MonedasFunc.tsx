import axios from 'axios';
import React from 'react';
interface IProps { }
interface IState {
    monedas?: Array<any>;
}
export const MonedasFunc = () => {
    const ip = "localhost";
    const puerto = 3000;
    const rutaBase = "http://" + ip + ":" + puerto + "/api/v1";
    const rutaMonedas = rutaBase + "/monedas";
    const [state, setstate] = React.useState<IState>({});
    const [stmoneda, setStmoneda] = React.useState();
    React.useEffect(() => {
        async function fetchMonedas() {
            let ruta = rutaMonedas;
            let respuesta = await axios.get(ruta);
            setstate(respuesta.data);
        }
        //fetchMonedas();
        const getMoneda = async (monedaid: string|undefined) =>{
            let rutaDeMoneda = "http://localhost:8080/api/v1/monedas/";
            let { data } = await axios.get(rutaDeMoneda + monedaid);
            setStmoneda(data);
            }
        getMoneda("1");
    }, []);
    
    return (
        <>
            <h3>Un componente sencillo para monedas de manera funcional</h3>
            <div>
                Monedas: {JSON.stringify(stmoneda)}
            </div>
        </>
    );
}