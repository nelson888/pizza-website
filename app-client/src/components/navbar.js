import React, { Component } from 'react';
import {NavLink, Link} from 'react-router-dom';

class NavBar extends Component {

    render() {
        const isLogged = false;
        return (
            <div style={{marginBottom: 60}}>
                <nav className="navbar navbar-expand-md navbar-light bg-light fixed-top">
                    <Link className="navbar-brand" to="/">The Pizza Blog</Link>

                    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div className="navbar-nav">
                            <NavLink className="nav-item nav-link" to="/pizzas">Home</NavLink>
                            <NavLink className="nav-item nav-link" to="/authors" >Authors</NavLink>
                            {
                                !isLogged &&
                                <NavLink className="nav-item nav-link" to="/login" >Log In</NavLink>

                            }
                        </div>
                    </div>
                </nav>
            </div>
        );
    }
}

/**
 * input for search
 * <input className="form-control mr-sm-2" type="text" placeholder="Search"
 aria-label="Search">
 <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
 </input>
 */
export default NavBar;