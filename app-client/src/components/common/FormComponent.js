import React, {Component} from 'react';
import Joi from 'joi-browser';
import Input from "./input";
import Select from "./select";

/**
 * You must define the state.data and schema objects,
 * doSubmit() method
 * and render method when inheriting this class
 */
class FormComponent extends Component {

    state = {
        data : {}, //BIEN DEFINIR LES PROPERTY A UTILISER DANS LES FORMS
        errors: {}
    };

    validate = () => {
        const {error} = Joi.validate(this.state.data, this.schema, {
            abortEarly: false
        });
        if (!error) return null;
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
        this.setState({errors: errors ? errors : {}});
        if (errors) {
            return;
        }
        //save the changes
        this.doSubmit(); // a definir dans les classes filles
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

        const data = {...this.state.data};
        data[input.name] = input.value;
        this.setState({ data, errors });
    };

    renderButton(label) {
        return (
            <button
                disabled={this.validate()}
                className="btn btn-primary">{label}</button>
        );
    }

    renderInput(name, label, type, autoFocus = false) {
        const { data, errors} = this.state;
        if (!type) type = "";
        return(
            <Input
                value={data[name]}
                name={name}
                label={label}
                type={type}
                onChange={this.handleChange}
                error={errors[name]}
                autoFocus={autoFocus}
            />
        );
    }

    renderSelect = (name, label, options) => {
        const {data, errors} = this.state;
        return (
          <Select
          name={name}
          value={data[name]}
          label={label}
          options={options}
          onChange={this.handleChange}
          error={errors[name]}
          />
        );
    };
}

export default FormComponent;