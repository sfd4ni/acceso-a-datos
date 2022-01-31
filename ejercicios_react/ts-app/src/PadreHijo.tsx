import React from 'react';
export const PadreModificadoPorHijo = () => {
    const [state, setstate] = React.useState(0);
    function modificarState(dato: number) { setstate(dato); }
    return (
        <div>
            <HijoModificaPadre statepadre={state} modificarstatepadre={modificarState} />
            <h4>dato recibido de hijo: {state}</h4>
        </div>
    )
}
interface Iprops {
    modificarstatepadre: Function;
    statepadre: number;
}
export const HijoModificaPadre = (props: Iprops) => {
    function enviarinfo() {
        const { modificarstatepadre, statepadre } = props;
        let num = Math.random();
        modificarstatepadre(num);
    }
    return (
        <div>
            <button onClick={enviarinfo}>modificar padre</button>
        </div>
    )
}