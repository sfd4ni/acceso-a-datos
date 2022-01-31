import React from 'react';
import { Persona } from './Persona';
import { PersonaCard } from './PersonaCard';
interface IProps { }
interface IState { arrayPersonas: Array<Persona> }

class PersonaRaiz extends React.Component<IProps, IState> {
    state: any;
    setstate: any;
  constructor(props: IProps)  {
    super(props);
    const [state, setstate] = React.useState(new Array<Persona>());
    
    this.modificarState = this.modificarState.bind(this);
}
    modificarState(persona: Persona) { 
        let newarray = [...this.state];
        let index = newarray.findIndex((el)=>el.nombre="paco");
        newarray[persona.id] = persona;
        this.setstate(newarray); 
    }
    render() {
        return (
            <div>
                <PersonaCard statepadre={this.state} modificarstatepadre={modificarState} />
                <h4>dato recibido de hijo: {this.state}</h4>
            </div>
        )
    }
    
}