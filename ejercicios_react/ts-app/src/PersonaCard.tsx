import React from 'react';
import { Persona } from './Persona';

interface IProps {
    modificarstatepadre: Function;
    statepadre: Array<Persona>;
}
interface IState { persona: Persona }

export class PersonaCard extends React.Component<IProps, IState> {
  constructor(props: IProps)  {
    super(props);
    this.state = { persona: new Persona(0, "", "", 0, 0, 0) };
    const { modificarstatepadre, statepadre } = props;
    modificarstatepadre(this.state.persona);
    //this.operarBoton = this.operarBoton.bind(this);
    }
    render() {
        return (
            <div>
              <h4>id:</h4>
              <span>nombre: </span><input type="text" value={this.state.persona.nombre}></input>
              <span>apellido: </span><input type="text" value={this.state.persona.apellido}></input>
              <span>altura: </span><input type="text" value={this.state.persona.altura}></input>
              <span>edad: </span><input type="text" value={this.state.persona.edad}></input>
              <span>peso: </span><input type="text" value={this.state.persona.peso}></input>
              <span>imc: {this.state.persona.getIMC}</span>
            </div>
          )
    }
}