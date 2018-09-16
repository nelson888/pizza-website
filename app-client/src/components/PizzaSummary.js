import React, {Component} from 'react';
import {Link} from 'react-router-dom';


class PizzaSummary extends Component {

    render() {
        return (

            <React.Fragment>
                <Link to={`/pizzas/${this.props.pizza.id}`}>{this.props.pizza.title}</Link>
                <br/>
                <p>{this.props.pizza.ingredients.map(i => i.name ? i.name : i)}</p>
            </React.Fragment>
        );
    }
}

export default PizzaSummary;