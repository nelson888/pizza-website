import React, { Component } from 'react';
import './Pizza.css';
import { Avatar, Icon } from 'antd';
import { Link } from 'react-router-dom';
import { getAvatarColor } from '../utils/Colors';

class Pizza extends Component {
    //TODO
    render() {
        return (
            <div className="pizza-content">
                <div className="pizza-header">
                    <div className="pizza-creator-info">
                        <Link className="creator-link" to={`/users/${this.props.pizza.author}`}>
                            <Avatar className="pizza-creator-avatar"
                                    style={{ backgroundColor: getAvatarColor(this.props.pizza.author.name)}} >
                                {this.props.pizza.author.name[0].toUpperCase()}
                            </Avatar>
                            <span className="pizza-creator-name">
                                {this.props.pizza.author.name}
                            </span>
                            <span className="pizza-creator-username">
                                @{this.props.pizza.author.lastName}
                            </span>
                            <span className="pizza-creation-date">

                            </span>
                        </Link>
                    </div>
                    <div className="pizza-description">
                        {this.props.pizza.description}
                    </div>
                </div>
                <div className="pizza-footer">

                </div>
            </div>
        );
    }
}

export default Pizza;