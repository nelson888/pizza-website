import React, { Component } from 'react';
import { PIZZA_LIST_SIZE } from '../constants';

import { getAllPizzas } from '../utils/APIRequests';
import { withRouter } from 'react-router-dom';
import './Pizza.css';

class PizzaList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pizzas: [],
            page: 0,
            size: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            currentVotes: [],
            isLoading: false
        };
        this.loadPizzaList = this.loadPizzaList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    loadPizzaList(page = 0, size = PIZZA_LIST_SIZE) {
        let promise;
        promise = getAllPizzas(page, size);

        if(!promise) {
            return;
        }

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {
                const pizzas = this.state.pizzas.slice();
                const currentVotes = this.state.currentVotes.slice();

                this.setState({
                    pizzas: pizzas.concat(response.content),
                    page: response.page,
                    size: response.size,
                    totalElements: response.totalElements,
                    totalPages: response.totalPages,
                    last: response.last,
                    currentVotes: currentVotes.concat(Array(response.content.length).fill(null)),
                    isLoading: false
                })
            }).catch(error => {
            this.setState({
                isLoading: false
            })
        });

    }

    render() {
        const pizzaViews = [];
        this.state.pizzas.forEach((poll, pizzaIndex) => {
            pizzaViews.push(<Poll
                key={poll.id}
                poll={poll}
                currentVote={this.state.currentVotes[pizzaIndex]} />)
        });

        return (
            <div className="pizzas-container">
                {pizzaViews}
                {
                    !this.state.isLoading && this.state.polls.length === 0 ? (
                        <div className="no-pizzas-found">
                            <span>No Pizzas Found.</span>
                        </div>
                    ): null
                }
                {
                    !this.state.isLoading && !this.state.last ? (
                        <div className="load-more-polls">
                            <Button type="dashed" onClick={this.handleLoadMore} disabled={this.state.isLoading}>
                                <Icon type="plus" /> Load more
                            </Button>
                        </div>): null
                }
                {
                    this.state.isLoading ?
                        <LoadingIndicator />: null
                }
            </div>
        );
    }
}

export default withRouter(PizzaList);