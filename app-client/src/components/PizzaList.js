import React from 'react';
import PizzaSummary from "./PizzaSummary";
import List from "./List";

class PizzaList extends List {

    propertyName = "pizzas";

    contentFunc = (p) => {
        return<PizzaSummary
            id={p.id}
            key={p.id}
            pizza={p}/>;
    }
}

export default PizzaList;