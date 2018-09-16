import React from 'react';

const Spinner = ({items, textProperty, valueProperty, selectedItem, onItemSelect}) => {

    return(
    <ul className="list-group">
        {
            items.map(item =>
                <li
                    className={ selectedItem === item ? "list-group-item active" : "list-group-item"}
                    key={item[valueProperty]}
                    onClick={() => onItemSelect(item)}

                >
                    {item[textProperty]}
                </li>
            )
        }

    </ul>);
};

export default Spinner;