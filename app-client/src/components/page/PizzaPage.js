import React,{Component} from "react";
import queryString from 'query-string';

//to add query parameters run npm i query-string

class PizzaPage extends Component {

    constructor(props) {
        super(props);
        const {param1, param2} = queryString.parse(props.location.search); //type of parameters is always string
        console.log(param1);
        console.log(param2);
    }

    render() {
        const {history} = this.props;
        return (
            <div>
                <h1>
                    Pizza {this.props.match.params.id}
                </h1>

                <button className="btn btn-primary" onClick={() => history.push('/pizzas')}>
                    Comment todo add text input form
                </button>
            </div>
        );
    }
}

export default PizzaPage;