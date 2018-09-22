export const PAGE_SIZE = 2;

const ingredients = [{
    name:"poivre",
    ingredient_id:0
}, {
    name: "sel",
    ingredient_id:1
}, {name:"tomate",
    ingredient_id:2
}];

const allPizzas = [
    {id: 0, title: "pizza 1", ingredients: [ingredients[0], ingredients[1]]},
    {id: 1, title: "pizza 2", ingredients: [ingredients[0], ingredients[2]]},
    {id: 2, title: "pizza 3", ingredients: [ingredients[1], ingredients[3]]},
    {id: 3, title: "pizza 4", ingredients: ingredients},
];

export function getPizzas(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    let sliced = [];
    const startIndex = page * size;
    for (let i = startIndex; i < startIndex + size; i++) {
        sliced.push(allPizzas[i])
    }
    return sliced;
}


export function getPizzaCount() {
    return allPizzas.length;
}

export function getAllIngredients() {
    return ingredients;
}

export function getPizzaDoughs() {
    return [{id:0, name:'thin'},
        {id:1, name:'normal'},
        {id:2, name:'thick'}]
}

export function savePizza(pizza) {
    //TODO handle ingredients
    pizza.id = allPizzas.length;
    allPizzas.push(pizza);
}