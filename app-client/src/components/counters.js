import React, {Component} from 'react';
import Counter from './counter';

class Counters extends Component {



    //the key atttibute is used internally by react. It is not passed in props that's why we have to pass id as a prop
    render() {
        //object destructuring (extract properties from object
        const {onClear, onReset, onIncrement, onDecrement, onDelete, counters} = this.props;
        return (
            <div>
                <button
                    onClick={onClear}
                    className="btn btn-danger btn-sm m-2">
                    Clear
                </button>

                <button
                    onClick={onReset}
                    className="btn btn-primary btn-sm m-2">
                    Reset
                </button>

                {counters.map(counter => (
                    <Counter
                        key={counter.id}
                        counter={counter}
                        onIncrement={onIncrement}
                        onDecrement={onDecrement}
                        onDelete={onDelete}>
                        <h4>{counter.title}</h4>
                    </Counter>))}
            </div>
        );
    }

}

export default Counters;