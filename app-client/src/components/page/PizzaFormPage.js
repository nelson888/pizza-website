import React from 'react';
import Joi from 'joi-browser';
import FormComponent from "../common/FormComponent";
import {getPizzaDoughs, savePizza } from '../../utils/FakeAPIRequests';

class PizzaFormPage extends FormComponent {

    state = {
        data : {
            title : '',
            ingredients : '',
            pizzaDough: '', //pate de pizza
            year: 0,
            description : ''
        },
        errors: {},
        pizzaDoughs: []

    };

    schema = {
        title: Joi.string().required().email().label('Email'),
        ingredients: Joi.string().required().label('Ingredients'),
        pizzaDough: Joi.string().required().label('Pizza dough'),
        year: Joi.number().integer().required().min(0).max(3000).label('Year'),
        description: Joi.string().required().min(4).max(500).label('Description')
    };

    componentDidMount() {
        const pizzaDoughs = getPizzaDoughs();
        this.setState({ pizzaDoughs });
    }

    doSubmit = () => {
        savePizza(this.state.data);
        const {ingredients} = this.state.data;
        this.state.data.ingredients = ingredients.split(',');
        this.props.history.push("/pizzas");
    };

    render() {

        return (
            <React.Fragment>
                <h1>Register</h1>

                <form onSubmit={this.handleSubmit}>

                    {this.renderInput('title', 'Title', 'text', true)}
                    {this.renderInput('ingredients', "Ingredients (separate them with a ',')", 'username')}
                    {this.renderSelect('pizzaDough', 'Pizza Dough', this.state.pizzaDoughs)}
                    {this.renderInput('price', 'Year', 'number')}
                    {this.renderInput('description', 'Description', 'text')}


                    {this.renderButton('Register')}
                </form>
            </React.Fragment>
        );
    }
}

export default PizzaFormPage;