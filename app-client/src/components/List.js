import React, {Component} from 'react';

class List extends Component { //abstract class

 render() {
        const objects = this.props[this.propertyName];
        return (

            <div>
                {
                    objects.map(o =>
                        this.contentFunc(o))
                }
            </div>
        );
    }

}

export default List;