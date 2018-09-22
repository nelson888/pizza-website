import React from 'react';

const SearchBox = ({value, onChange}) => {
    //my = margin y => margin top and bottom
    return (
      <input
          type="text"
          name="query"
          className="form-control my-3"
          placeholder="Search..."
          value={value}
          onChange={e => onChange(e.currentTarget.value)}
      />

    );
};

export default SearchBox;