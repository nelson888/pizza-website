import React, { Component } from 'react';
import NavBar from './components/navbar';
import {Route, Switch, Redirect} from 'react-router-dom'; //to add routing first run npm i react-router-dom and then see index.js
import HomePage from './components/page/HomePage';
import PizzaPage from './components/page/PizzaPage';
import NotFoundPage from './components/page/NotFoundPage';
import AuthorsPage from "./components/page/AuthorsPage";
import LoginPage from "./components/page/LoginPage";
import './App.css';
//bootstrap website: https://getbootstrap.com/docs/
/**
 * par default si un url commence par le path definit par un route, il va dedans
 * par exemple /sqqsfsdf renverra le route pour '/' car il commence par '/'
 * la prop 'exact' sur un Route permet d'enlever ce comportement
 * ou sinon on met tout les Route dans un Switch
 * ATTENTION dans un Switch il faut mettre les Routes du plus specifique au moins specifique
 *
 * The props argument in the render function contains history, location and match
 * Use the 'render' prop instead of 'component' when you want to pass custom props
 * programmatic navigation: this.props.history.push('/path')
 **/
class App extends Component {

    render() {
        return (
            <React.Fragment>
                <NavBar nbPizzas={88}/>
                <div className="content">
                    <Switch>
                        <Route path="/pizzas/:id" component={PizzaPage}/>
                        <Route path="/withProps" render={(props) => <HomePage prop="prop" {...props}/>} />
                        <Route path="/not-found" exact component={NotFoundPage} />
                        <Route path="/authors" exact component={AuthorsPage} />
                        <Route path="/pizzas" exact component={HomePage} />
                        <Route path="/login" exact component={LoginPage} />
                        <Redirect from="/" exact to="/pizzas"/>
                        <Redirect to="/not-found"/>
                    </Switch>
                </div>
            </React.Fragment>
        );
    }
}

export default App;
