import React from 'react';
import Joi from 'joi-browser';
import FormComponent from "../common/FormComponent";

class LoginPage extends FormComponent {

    state = {
        data : { //BIEN DEFINIR LES PROPERTY A UTILISER DANS LES FORMS
            email : '',
            password : ''
        },
        errors: {}
    };

    schema = {
        email: Joi.string().required().email().label('Email'),
        password: Joi.string().required().min(4).max(20).label('Password')
    };


    doSubmit = () => {
        alert(JSON.stringify(this.state.data));
    };

    render() {

        return (
            <React.Fragment>
                <h1>Log In</h1>

                <form onSubmit={this.handleSubmit}>

                    {this.renderInput('email', 'Email', 'email', true)}
                    {this.renderInput('password', 'Password', 'password')}


                    {this.renderButton('Log In')}
                 </form>
            </React.Fragment>
        );
    }
}

export default LoginPage;