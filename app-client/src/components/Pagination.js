import React from "react";
import {PAGE_SIZE} from '../utils/constants';
import  PropTypes from 'prop-types'; //useful for type validation (only works in developer mode) $npm i prop-types@15.6.2


//stateless component

const Pagination = props => {
    const  { itemsCount, onPageChanged, currentPage} = props;

    const pagesCount = Math.ceil(itemsCount / PAGE_SIZE);
    if (pagesCount === 1) return null;
    let pages = [];
    for (let i = 0; i < pagesCount; i++) {
        pages.push(i);
    }
    const className = "page-item";
    return (
    <nav>
        <ul className="pagination">
            {
                pages.map(page => (
                    <li key={page} className={page === currentPage ? className + " active" : className}>
                        <a className="page-link"
                        onClick={() => onPageChanged(page)}
                        >{page+1}</a>
                    </li>
                ))
            }
        </ul>

    </nav>);
};
Pagination.propTypes = { //good practise to have a warning message when passing wrong type of props
    itemsCount: PropTypes.number.isRequired,
    onPageChanged: PropTypes.func.isRequired,
    currentPage: PropTypes.number.isRequired
};

export default Pagination;