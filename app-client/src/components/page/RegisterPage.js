import React from 'react';
import Joi from 'joi-browser';
import FormComponent from "../common/FormComponent";

class RegisterPage extends FormComponent {

    state = {
        data : { //BIEN DEFINIR LES PROPERTY A UTILISER DANS LES FORMS
            email : '',
            username : '',
            password : '',
            password2 : ''
        },
        errors: {}
    };

    schema = {
        email: Joi.string().required().email({ minDomainAtoms: 2 }).label('Email'),
        username: Joi.string().alphanum().required().min(3).max(20).label('Username'),
        password: Joi.string().required().min(4).max(20).label('Password'),
        password2: Joi.string().required().min(4).max(20).label('Password')
    };


    doSubmit = () => {
        if (this.state.password !== this.state.password2) {
            let errors = {...this.state.errors};
            errors.password = 'The two passwords entered are different';
            errors.password2 = errors.password;
            this.setState({ errors });
            return
        }
        alert(JSON.stringify(this.state.data));
    };

    render() {

        return (
            <React.Fragment>
                <h1>Register</h1>

                <form onSubmit={this.handleSubmit}>

                    {this.renderInput('email', 'Email', 'email', true)}
                    {this.renderInput('username', 'Username', 'username')}
                    {this.renderInput('password', 'Password', 'password')}
                    {this.renderInput('password2', 'Validate password', 'password')}


                    {this.renderButton('Register')}
                </form>
            </React.Fragment>
        );
    }
}

export default RegisterPage;