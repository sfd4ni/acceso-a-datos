import React from 'react';
import { Persona } from './Persona';

interface IProps {
    modificarstatepadre: Function;
    statepadre: Persona;
}
interface IState { persona: Persona }

export class PersonaCard extends React.Component<IProps, IState> {
    modificarstatepadre: any;
  constructor(props: IProps)  {
    super(props);
    
    const { modificarstatepadre, statepadre } = props;
    this.modificarstatepadre = modificarstatepadre;
    this.state = { persona: statepadre };
    console.log(statepadre);
    
    
    //modificarstatepadre(this.state.persona);
    //this.operarBoton = this.operarBoton.bind(this);
    }
    render() {
        return (
            <div>
              <h4>id: {this.state.persona.id}</h4>
              <span>nombre: </span><input type="text" value={this.state.persona.nombre} onChange={this.modificarstatepadre(this.state.persona)}></input>
              <span>apellido: </span><input type="text" value={this.state.persona.apellido} onChange={this.modificarstatepadre(this.state.persona)}></input>
              <span>altura: </span><input type="text" value={this.state.persona.altura > 0 ? this.state.persona.altura : ""} onChange={this.modificarstatepadre(this.state.persona)}></input>
              <span>edad: </span><input type="text" value={this.state.persona.edad > 0 ? this.state.persona.edad : ""} onChange={this.modificarstatepadre(this.state.persona)}></input>
              <span>peso: </span><input type="text" value={this.state.persona.peso > 0 ? this.state.persona.peso : ""} onChange={this.modificarstatepadre(this.state.persona)}></input>
              <span>imc: {this.state.persona.getIMC}</span>
            </div>
          )
    }
}