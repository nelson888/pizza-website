import React, { Component } from 'react';
import './HomePage.css';
import PizzaList from "../PizzaList";
import Spinner from '../common/spinner';
import {getPizzas, getPizzasSearch, getPizzaCount, getAllIngredients} from '../../utils/FakeAPIRequests';
import Pagination from '../Pagination';
import { Link } from 'react-router-dom';
import SearchBox from '../searchBox';

const ALL_INGREDIENTS = {name: "all"};

class HomePage extends Component {

    constructor() {
        super();
        this.handleDelete = this.handleDelete.bind(this); //sert a acceder à this dans la fonction
        this.handleDecrement = this.handleDecrement.bind(this);
        this.handleIncrement = this.handleIncrement.bind(this);
        this.handleValueChange = this.handleValueChange.bind(this);
        this.reset = this.reset.bind(this);
        this.clear = this.clear.bind(this);
        let ingredients = [ALL_INGREDIENTS, ...getAllIngredients()];

        this.state = {
            pizzas: getPizzas(0),
            currentPage: 0,
            nbTotalPizzas: getPizzaCount(),
            ingredients: ingredients,
            selectedIngredient: ALL_INGREDIENTS,
            searchQuery: ""

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

    getPizzaPage = (page, searchQuery) => {
        return searchQuery ? getPizzasSearch(page, searchQuery) : getPizzas(page);
    };

    handlePageChange = page => {
        this.setState({
            currentPage: page,
            pizzas: this.getPizzaPage(page, this.state.searchQuery)
        });
    };

    handleSearch = query => {
        this.setState({ searchQuery: query, currentPage: 0, selectedIngredient: ALL_INGREDIENTS, pizzas: this.getPizzaPage(0, query), nbTotalPizzas: getPizzaCount(query) });
    };

    handleSpinnerSelect = (ingredient) => {
        this.setState({selectedIngredient: ingredient, searchQuery: "", currentPage: 0 });
        //TODO request to backend wanted pizzas
    };

    render() {
        const {currentPage, searchQuery, ingredients, selectedIngredient, pizzas, nbTotalPizzas} = this.state;

        return (
            <React.Fragment>
            <div className="row">
                <div className="col-3">
                    <Spinner
                        items={ingredients}
                        textProperty="name"
                        valueProperty="ingredient_id"
                        selectedItem={selectedIngredient}
                        onItemSelect={this.handleSpinnerSelect}
                    />
                </div>
                <div className="col">
                    <Link
                        to="/pizzas/new"
                        className="btn btn-primary"
                        style={{marginBottom : 20}}
                    >
                        New Pizza
                    </Link>
                    <SearchBox value={searchQuery} onChange={this.handleSearch}/>
                    <main className="container">
                        <PizzaList
                            pizzas={pizzas}
                        />
                    </main>
                    <Pagination
                        itemsCount={nbTotalPizzas}
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
