import React, {Component} from 'react';
import Input from '../common/input';
import Joi from 'joi-browser';

class LoginPage extends Component {

    state = {
        account : { //BIEN DEFINIR LES PROPERTY A UTILISER DANS LES FORMS
            email : '',
            password : ''
        },
        errors: {}
    };

    schema = {
        email: Joi.string().required().email().label('Email'),
        password: Joi.string().required().min(4).max(20).label('Password')
    };


    validate = () => {
        const {error} = Joi.validate(this.state.account, this.schema, {
            abortEarly: false
        });
        if (!error) return {};
        const  errors = {};
        for (let item of error.details) {
            errors[item.path[0]] = item.message;
        }

        //return Object.keys(errors).length === 0 ? null : errors;
        return errors;
    };

    handleSubmit = (e) => {
        e.preventDefault(); //prevent submitting form to server
        //call the server
        const errors = this.validate();
        this.setState({errors});
        if (errors) {
            return;
        }
        //save the changes
        //redirect user to different page
    };

    validateProperty = ({name, value}) => {
        const obj = {[name]: value}; // name is a dynamic property
        const schema = {[name]: this.schema[name]};
        const {error} = Joi.validate(obj, schema);

        return error ? error.details[0].message : null;
    };

    handleChange = ({currentTarget: input}) => { //object destructuring and renaming into input
        const errors = {...this.state.errors};
        const error = this.validateProperty(input);
        if (error) {
            errors[input.name] = error;
        } else {
            delete errors[input.name];
        }

        const account = {...this.state.account};
        account[input.name] = input.value;
        this.setState({ account, errors });
    };

    render() {
        const { account, errors} = this.state;
        return (
            <React.Fragment>
                <h1>Log In</h1>

                <form onSubmit={this.handleSubmit}>
                    <Input
                        value={account.email}
                        name="email"
                        label="Email"
                        type="email"
                        autoFocus={true}
                        onChange={this.handleChange}
                        error={errors.email}
                    />

                    <Input
                        value={account.password}
                        name="password"
                        label="Password"
                        type="password"
                        onChange={this.handleChange}
                        error={errors.password}
                    />

                    <button
                        disabled={Object.keys(this.validate()).length > 0 }
                        className="btn btn-primary">Log In</button>
                 </form>
            </React.Fragment>
        );
    }
}

export default LoginPage;