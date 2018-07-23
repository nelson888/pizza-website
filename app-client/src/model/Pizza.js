import React, { Component } from 'react';
import './Pizza.css';

class Pizza extends Component {

    render() {
        return (
            <div className="pizza-content">
                <div className="pizza-header">
                    <div className="pizza-description">
                        {this.props.pizza.description}
                    </div>
                </div>
                <div className="poll-choices">
                    <RadioGroup
                        className="pizza-ingredients">
                        { ingredients }
                    </RadioGroup>
                </div>
            </div>
        );
    }
}