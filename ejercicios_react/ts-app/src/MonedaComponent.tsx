import axios from 'axios';
import { stat } from 'fs';
import React from 'react';
import { MonedaId } from './MonedaId';
interface IProps {
  moneda: MonedaId
 }
 
export const MonedaComponent = (props: IProps) => {
  const { moneda } = props;
    return (
        <>
            <h3>{moneda.nombre}</h3>
            <div>
                <span>Id: {moneda.idmoneda}</span><br/>
                <span>País: {moneda.pais} </span><br/>
            </div>
        </>
    );
}