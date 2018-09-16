import React from 'react';

const Input = ({name, label, value, onChange, type, autoFocus, error}) => {

    return (
    <div className="form-group">
        <label htmlFor={name}>{label}</label>
        <input
            autoFocus={autoFocus}
            onChange={onChange}
            value={value}
            type={type}
            name={name}
            className="form-control"
            id={name}/>
        {
          error &&
          <div className="alert alert-danger">{error}</div>
        }
    </div>
    );
};

export default Input;