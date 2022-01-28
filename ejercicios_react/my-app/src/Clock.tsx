import React from "react";
interface IProps { }
interface IState { date: Date }
class Clock extends React.Component<IProps, IState> {
    timerID: any;
    constructor(props: IProps) {
        super(props);
        this.state = { date: new Date() };
    }
    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            1000
        );
    }
    componentWillUnmount() {
        clearInterval(this.timerID);
    }
    tick() { this.setState({ date: new Date() }); }
    render() {
        return (
            <div>
                <h1>Hello, world!</h1>
                <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
            </div>
        );
    }
}
export default Clock;