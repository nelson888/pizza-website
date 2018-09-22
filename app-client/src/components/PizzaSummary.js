import React from 'react';
import {Link} from 'react-router-dom';


const PizzaSummary = ({ pizza }) => {

    return (
        <React.Fragment>
            <Link to={`/pizzas/${pizza.id}`}>{pizza.title}</Link>
            <br/>
            <p>{pizza.ingredients.map(i => i.name)}</p>
        </React.Fragment>
    );

};

export default PizzaSummary;