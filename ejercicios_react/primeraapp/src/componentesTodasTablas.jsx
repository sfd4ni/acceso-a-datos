import React from "react";
import Tabla from './componenteTabla';

const Tablas = (props) => {
    let tablas = [2, 3, 4, 5, 6, 7, 8, 9, 10]

    return (
        <>
            <p>
            {tablas?.map( (numero) => {
                return (
                <Tabla tabla={numero}/>
                );
            })
    }
            </p>
        </>
    );
}
export default Tablas;