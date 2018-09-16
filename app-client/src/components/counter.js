import React, {Component} from 'react';

//This is a controlled component
//controlled components don't use state because they are controlled by another component
//they use props instead
class Counter extends Component {

    //props est une map contenant les attributs passés dans la balise de l'element
    //props.children donne le content mis a l'interieur de la balise de cet element

    styles = {
        fontSize: 10,
        fontWeight: 'bold'
    };

    render() {

        return (
            <React.Fragment>
                {this.props.children}
                <div className="row">
                    <div className="col-1">
                        <span className={this.getBadgeClasses()} style={this.styles}>{this.props.counter.value} </span>
                    </div>
                    <div className="col">
                        <button
                            onClick={() => this.props.onIncrement(this.props.counter)}
                            className="btn btn-secondary btn-sm"
                            disabled={this.props.counter.value >= 5}
                        >
                            +
                        </button>

                        <button
                            onClick={() => this.props.onDecrement(this.props.counter)}
                            className="btn btn-secondary btn-sm m-2"
                            disabled={this.props.counter.value === 0}
                        >
                            -
                        </button>

                        <button onClick={() => this.props.onDelete(this.props.counter.id)}
                                className="btn btn-danger btn-sm">
                            Delete
                        </button>
                    </div>
                </div>
            </React.Fragment>); //babel can only compile one element, hence the div
    } //to avoid create a div, we use a React.Fragment

    getBadgeClasses() {
        let classes = "badge m-2 badge-";
        classes += (this.props.counter.value === 0) ? "warning" : "primary";
        return classes;
    }
}

export default Counter;