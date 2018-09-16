import React, { Component } from 'react';
import './HomePage.css';
import PizzaList from "../PizzaList";
import Spinner from '../common/spinner';
import {getPizzasFake, getPizzaCount, getAllIngredients} from '../../utils/APIRequests';
import Pagination from '../Pagination';

class HomePage extends Component {

    constructor() {
        super();
        this.handleDelete = this.handleDelete.bind(this); //sert a acceder à this dans la fonction
        this.handleDecrement = this.handleDecrement.bind(this);
        this.handleIncrement = this.handleIncrement.bind(this);
        this.handleValueChange = this.handleValueChange.bind(this);
        this.reset = this.reset.bind(this);
        this.clear = this.clear.bind(this);
        let ingredients = [{name:"all"}, ...getAllIngredients()];

        this.state = {
            pizzas: getPizzasFake(0),
            currentPage: 0,
            nbTotalPizzas: getPizzaCount(),
            ingredients: ingredients,
            selectedIngredient: ingredients[0]

    };
    }

    handleDelete(id) {
        const counters = this.state.counters.filter(c => c.id !== id);
        this.setState({counters}); //set the property counter to the value of the const counter
    }



    handleValueChange(counter, addedValue) {
        const counters = [...this.state.counters]; //spread operator used to clone this array
        const index = counters.indexOf(counter);
        counters[index] = {...counter}; //copy object
        counters[index].value+= addedValue;
        this.setState({ counters })
    }

    handleIncrement(counter) {
        this.handleValueChange(counter, 1);
    }

    handleDecrement(counter) {
        this.handleValueChange(counter, -1);
    }

    clear() {
        const counters = [];
        this.setState({counters});
    }

    reset() {
        const counters = this.state.counters.map(c => {
            c.value = 0;
            return c;
        });
        this.setState({counters});
    }

    handlePageChange = page => {
        this.setState({
            currentPage: page,
            pizzas: getPizzasFake(page)
        });
    };

    handleSpinnerSelect = (ingredient) => {
        this.setState({selectedIngredient: ingredient});
        //TODO request to backend wanted pizzas
    };

    render() {
        const {currentPage} = this.state;

        return (
            <React.Fragment>
            <div className="row">
                <div className="col-3">
                    <Spinner
                        items={this.state.ingredients}
                        textProperty="name"
                        valueProperty="ingredient_id"
                        selectedItem={this.state.selectedIngredient}
                        onItemSelect={this.handleSpinnerSelect}
                    />
                </div>
                <div className="col">
                    <main className="container">
                        <PizzaList
                            pizzas={this.state.pizzas}
                        />
                    </main>
                    <Pagination
                        itemsCount={this.state.nbTotalPizzas}
                        currentPage={currentPage}
                        onPageChanged={this.handlePageChange}
                    />
                </div>
            </div>
            </React.Fragment>
        );
    }
}

export default HomePage;
