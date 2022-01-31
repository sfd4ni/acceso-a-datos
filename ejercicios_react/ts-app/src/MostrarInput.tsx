import React from 'react';

interface IProps { }
interface IState { palabra: string }

class MostrarInput extends React.Component<IProps, IState> {
  constructor(props: IProps) {
    super(props);
    this.state = { palabra: "" };
    this.introducirLetra = this.introducirLetra.bind(this);
}
introducirLetra(event: React.ChangeEvent<HTMLInputElement>) {
  event.preventDefault();
  console.log(event.currentTarget.value);
  if (event.currentTarget.value === "") {
    this.setState({ palabra: "" });
  } else {
    this.setState({ palabra: "Has escrito:" + event.currentTarget.value });
  }
}
  render() {
    
    return (
      <div>
        <span>Introduce texto: </span><input type="text" onChange={this.introducirLetra}/>
        <h6>{this.state.palabra}</h6>
      </div>
    )
  }
}
export default MostrarInput;